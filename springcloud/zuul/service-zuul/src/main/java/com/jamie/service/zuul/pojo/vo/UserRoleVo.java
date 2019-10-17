package com.jamie.service.zuul.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserRoleVo implements Serializable {
    private static final long serialVersionUID = 744962308349152824L;

    private String userName;
    private Integer userId;
    private String role;
    private Integer roleId;
}
