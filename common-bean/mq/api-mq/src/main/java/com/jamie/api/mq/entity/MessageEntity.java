package com.jamie.api.mq.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class MessageEntity implements Serializable {
    private static final long serialVersionUID = 7104521603723636500L;

    private Integer id;
    private String routingKey;  //消息路由key
    private String notifyId;    //消息唯一标识（UUID）
    private String body;        //消息内容（JSON字符串）
    private int status;         //消息消费状态 0未消费 1消费成功 -1消费失败
    private Date createTime;
    private Date updateTime;
}
