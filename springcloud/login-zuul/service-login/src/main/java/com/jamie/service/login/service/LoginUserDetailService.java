package com.jamie.service.login.service;

import com.jamie.service.login.pojo.entity.UserEntity;
import com.jamie.service.login.pojo.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginUserDetailService implements UserDetailsService {
    @Autowired
    private LoginService loginService;

    @Override
    @Transactional //为什么此处要加事务控制？？？TODO
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 根据名字获取用户信息
        UserEntity userEntity = loginService.getUserByName(userName);
        // 根据名字获取用户信息和角色信息
        List<UserRoleVo> userRoleVos = loginService.getUserRoleByName(userName);

        // 存放角色的列表
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRoleVo userRoleVo: userRoleVos){
            // 用户角色信息
            GrantedAuthority authority = new SimpleGrantedAuthority(userRoleVo.getRole());
            authorities.add(authority);
        }

        // User: org.springframework.security.core.userdetails.User
        return new User(userEntity.getUserName(), userEntity.getPassword(), authorities);
    }
}
