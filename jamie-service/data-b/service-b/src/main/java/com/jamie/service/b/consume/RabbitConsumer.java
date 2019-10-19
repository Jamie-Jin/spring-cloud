package com.jamie.service.b.consume;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jamie.api.a.vo.AVo;
import com.jamie.rabbitmq.vo.NotifyVo;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
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

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "rabbit_queue", durable = "true"),
                    exchange = @Exchange(value = "rabbit_exchange",
                                         durable = "true",
                                         type = "topic",
                                         ignoreDeclarationExceptions = "true"),
                    key = "mq.A_TO_B"))
    @RabbitHandler
    public void onMessage(Message message, Channel channel, @Headers Map<String, Object> headers) throws Exception {

        NotifyVo notifyVo;
        try {
            // 接收到的消息
            notifyVo = (NotifyVo) message.getPayload();
        } catch (Exception e){
            logger.error("接收方消息类型转换失败");
            throw new RuntimeException(e.getMessage());
        }

        // 将消息体中的Json字符串转换为对象
        AVo aVo = new Gson().fromJson(notifyVo.getBody(), new TypeToken<AVo>() {}.getType());

        logger.info("数据模块B接收到数据：" + aVo.toString());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

}
