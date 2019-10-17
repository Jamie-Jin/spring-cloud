package com.jamie.service.zuul.biz;

import com.jamie.service.zuul.pojo.entity.UserEntity;
import com.jamie.service.zuul.pojo.vo.UserRoleVo;
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

/**
 * 实现Oauth2 UserDetailsService接口，用于为Spring Security从数据库获取用户信息
 */
@Service
public class LoginUserDetailsService implements UserDetailsService {
    @Autowired
    private LoginBiz loginBiz;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 根据名字获取用户信息
        UserEntity user = loginBiz.getUserByName(userName);
        // 根据名字获取用户信息和角色信息
        List<UserRoleVo> userRoleVos = loginBiz.getUserRoleByName(userName);

        // 存放角色的列表
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRoleVo userRoleVo: userRoleVos){
            // 用户角色
            GrantedAuthority authority = new SimpleGrantedAuthority(userRoleVo.getRole());
            authorities.add(authority);
        }

        // User: org.springframework.security.core.userdetails.User
        return new User(user.getUserName(), user.getPassword(), authorities);
    }
}
