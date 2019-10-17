package com.jamie.service.zuul.config;

import com.jamie.service.zuul.biz.LoginUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// 启用Spring Security
@EnableWebSecurity
public class LoginConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginUserDetailsService loginUserDetailsService;

    /**
     * 配置路径访问权限
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/a/**").hasRole("ADMIN")
            .antMatchers("/b/**").hasRole("USER")
            // 其余路径无需验证
            .anyRequest().permitAll()

            // 允许匿名访问无限制的路径
            .and().anonymous()
            // 使用默认登录页
            .and().formLogin()
            .and().httpBasic();
    }

    /**
     * 获取用户登录信息，用户信息存在数据库中
     * @param builder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        builder
            .userDetailsService(loginUserDetailsService)
            .passwordEncoder(passwordEncoder);
    }

}
