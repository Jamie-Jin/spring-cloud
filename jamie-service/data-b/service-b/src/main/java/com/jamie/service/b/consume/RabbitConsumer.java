package com.jamie.service.b.consume;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jamie.api.a.vo.AVo;
import com.jamie.api.mq.entity.MqConfigEntity;
import com.jamie.api.mq.service.MessageApi;
import com.jamie.api.mq.vo.NotifyVo;
import com.rabbitmq.client.Channel;
import org.omg.SendingContext.RunTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * RabbitMQ消费者
 */
@Service
public class RabbitConsumer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitConsumer.class);

    @Autowired
    private MessageApi messageApi;

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = MqConfigEntity.queue,
                                             durable = MqConfigEntity.queue_durable),
                    exchange = @Exchange(value = MqConfigEntity.exchange,
                                         durable = MqConfigEntity.exchange_durable,
                                         type = MqConfigEntity.exchange_type,
                                         ignoreDeclarationExceptions = MqConfigEntity.exchange_ignoreDeclarationExceptions),
                    key = "mq.A_TO_B"))
    @RabbitHandler
    public void onMessage(Message message, Channel channel, @Headers Map<String, Object> headers) throws Exception {
        NotifyVo notifyVo;
        try {
            // 接收到的消息
            notifyVo = (NotifyVo) message.getPayload();
        } catch (Exception e){
            logger.error("消息接收方：NotifyVo类型转换失败");
            throw new RuntimeException(e.getMessage());
        }

        try {
            // 将消息体中的Json字符串转换为对象
            AVo aVo = new Gson().fromJson(notifyVo.getBody(), new TypeToken<AVo>() {}.getType());

            // 更新预消息状态， 更新为成功消费
            messageApi.successMessage(notifyVo);

            logger.info("数据模块B接收到数据：" + aVo.toString());
            Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(deliveryTag, false);

        } catch (Exception e){
            logger.error("消息接收方：消息接收异常");
            e.printStackTrace();

            // 更新预消息状态， 更新为消费失败
            messageApi.failMessage(notifyVo);
        }

    }

}
