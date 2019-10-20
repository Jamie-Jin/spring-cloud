package com.jamie.api.mq.entity;

/**
 * RabbitMQ 属性
 * TODO 硬编码问题！！！
 */
public class MqConfigEntity {
    public static final String queue = "rabbit_queue";
    public static final String exchange = "rabbit_exchange";
    public static final String exchange_type = "topic";
    public static final String exchange_durable = "true";
    public static final String queue_durable = "true";
    public static final String exchange_ignoreDeclarationExceptions = "true";
}
