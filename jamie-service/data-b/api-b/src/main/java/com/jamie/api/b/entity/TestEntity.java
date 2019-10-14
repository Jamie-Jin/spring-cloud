package com.jamie.api.b.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TestEntity implements Serializable {
    private static final long serialVersionUID = -7452732409855191153L;

    private Integer id;
    private String msg;
}
