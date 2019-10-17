package com.jamie.service.zuul.biz;

import com.jamie.service.zuul.dao.UserDao;
import com.jamie.service.zuul.pojo.entity.UserEntity;
import com.jamie.service.zuul.pojo.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginBiz {
    @Autowired
    private UserDao userDao;

    // 根据名字获取用户信息
    public UserEntity getUserByName(String name){
        return userDao.getUserByName(name);
    }

    // 根据名字获取用户角色信息
    public List<UserRoleVo> getUserRoleByName(String name){
        return userDao.getUserRoleByName(name);
    }

}
