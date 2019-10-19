package com.jamie.service.b.consume;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Service;

import java.util.Map;

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
        logger.info("数据模块B接收到数据：" + message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(deliveryTag, false);
    }

}
