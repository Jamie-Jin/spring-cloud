package com.jamie.api.a.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TestEntity implements Serializable {
    private static final long serialVersionUID = -1035210183170944346L;

    private Integer id;
    private String msg;
}
