package com.jamie.api.a.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AVo implements Serializable {
    private static final long serialVersionUID = -3917987989895726740L;

    private String name;
    private Integer age;
    private Double height;
    private Long id;
    private List<String> hobbies;
    private Date today;
    private BigDecimal money;

    public String toString(){
        return "名字：" + name + ", 年龄：" + age + ", 身高：" + height +
               ", 日期：" + today + ", 身家：" + money + ", 爱好：" + hobbies + ", 身份证号：" + id;
    }
}
