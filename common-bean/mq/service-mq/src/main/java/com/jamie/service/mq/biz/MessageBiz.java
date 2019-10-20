package com.jamie.service.mq.biz;

import com.jamie.api.mq.vo.NotifyVo;
import com.jamie.service.mq.dao.MessageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageBiz {
    private static final Logger logger = LoggerFactory.getLogger(MessageBiz.class);

    // 定义在配置中心的配置中，config-repo/rabbitmq.yml
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageDao messageDao;

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

    /**
     * 发送MQ
     * @param notifyVo
     */
    public void send(NotifyVo notifyVo) {
        logger.info("发送方发送消息：" + notifyVo);

        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);

        // 消息唯一标识
        CorrelationData correlationData = new CorrelationData(notifyVo.getNotifyId());

        // 插入预消息
        messageDao.insertPreNotify(notifyVo);

        // routingKey：消息路由key
        rabbitTemplate.convertAndSend(exchange, notifyVo.getRoutingKey(), notifyVo, correlationData);
    }

    /**
     * 更新消息消费状态为1（成功消费）
     * @param notifyVo
     * @return
     */
    public int successMessage(NotifyVo notifyVo){
        return messageDao.successMessage(notifyVo);
    }

    /**
     * 更新消息消费状态为-1（成功失败）
     * @param notifyVo
     * @return
     */
    public int failMessage(NotifyVo notifyVo){
        return messageDao.failMessage(notifyVo);
    }
}
