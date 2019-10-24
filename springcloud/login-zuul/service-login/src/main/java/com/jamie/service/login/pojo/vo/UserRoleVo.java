package com.jamie.service.login.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserRoleVo implements Serializable {
    private static final long serialVersionUID = 7666933052932106975L;

    private String userName;
    private Integer userId;
    private String role;
    private Integer roleId;
}
