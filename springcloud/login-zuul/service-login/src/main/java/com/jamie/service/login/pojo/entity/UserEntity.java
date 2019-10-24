package com.jamie.service.login.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1168355119275221872L;

    private Integer id;
    private String userName;
    private String password;
    private int enable;
}
