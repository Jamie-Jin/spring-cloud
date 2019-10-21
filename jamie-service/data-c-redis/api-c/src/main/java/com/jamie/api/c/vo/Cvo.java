package com.jamie.api.c.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Cvo implements Serializable {
    private static final long serialVersionUID = 8477570517183591377L;

    private String key;
    private Object val;
}
