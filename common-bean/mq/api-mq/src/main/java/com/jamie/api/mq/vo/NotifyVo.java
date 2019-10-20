package com.jamie.api.mq.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NotifyVo implements Serializable {
    private static final long serialVersionUID = 5826140797529920749L;

    private String notifyId;    //消息唯一标识
    private String routingKey;   //RabbitMQ的routingKey
    private String body;        //要传送的Json字符串（使用gson将对象转换为字符串）
}
