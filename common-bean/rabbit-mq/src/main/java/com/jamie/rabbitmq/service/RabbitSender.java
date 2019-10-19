package com.jamie.rabbitmq.service;

import com.google.gson.Gson;
import com.jamie.rabbitmq.vo.NotifyVo;
import com.jamie.utilbase.util.GUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * RabbitMQ发送者
 */

@Service
public class RabbitSender {
    private static final Logger logger = LoggerFactory.getLogger(RabbitSender.class);

    // 定义在config-base模块bootstrap.yml，RocketMQ交换器
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 消费者confirm确认
    private final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String s) {
            logger.info("消费者确认，correlationData: " + correlationData);
            logger.info("消费者消费确认，ack：" + ack);
        }
    };

    // 消费者return返回
    private final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

        }
    };

    // routingKey：消息路由key
    public void send(Object message, String routingKey) throws Exception {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);

        String uuid = GUID.getUUID();
        NotifyVo notifyVo = new NotifyVo();
        notifyVo.setNotifyId(uuid);
        notifyVo.setNotifyTag(routingKey);
        notifyVo.setBody(new Gson().toJson(message)); //将对象转换成Json字符串

        // 消息唯一标识
        CorrelationData correlationData = new CorrelationData(uuid);

        logger.info("发送方发送消息：" + notifyVo);

        // routingKey：消费者唯一标识
        rabbitTemplate.convertAndSend(exchange, routingKey, notifyVo, correlationData);
    }
}
