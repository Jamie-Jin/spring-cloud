package com.jamie.service.zuul.pojo.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -142394481552496672L;

    private Integer id;
    private String userName;
    private String password;
    private int enable;
}
