package com.jamie.rabbitmq.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * RabbitMQ消息封装类
 */
@Getter
@Setter
public class NotifyVo implements Serializable {
    private static final long serialVersionUID = 4697093510783051236L;

    private String notifyId;    //消息唯一标识
    private String notifyTag;   //RabbitMQ的routingKey
    private String body;        //要传送的Json字符串（使用gson将对象转换为字符串）
}
