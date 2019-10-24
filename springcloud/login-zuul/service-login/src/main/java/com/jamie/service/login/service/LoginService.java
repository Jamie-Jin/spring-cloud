package com.jamie.service.login.service;

import com.jamie.service.login.dao.UserDao;
import com.jamie.service.login.pojo.entity.UserEntity;
import com.jamie.service.login.pojo.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private UserDao userDao;

    public UserEntity getUserByName(String name){
        return userDao.getUserByName(name);
    }

    public List<UserRoleVo> getUserRoleByName(String name){
        return userDao.getUserRoleByName(name);
    }

}
