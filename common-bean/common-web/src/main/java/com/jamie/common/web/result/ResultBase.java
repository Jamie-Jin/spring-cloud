package com.jamie.common.web.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultBase {
    public static int SUCCESS = 1;  //成功
    public static int FAILURE = 0;  //失败

    private int status;      //返回的状态码
    private String message; //返回的信息
    private Object data;    //返回的数据
}
