package com.jamie.service.zuul.config;

import com.jamie.service.zuul.biz.LoginUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;

/**
 * Spring Security使用Redis保存Session时，超时时间的设置在@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)，最小超时时间为60s，好像超时时间需要设置为60的倍数
 * Spring Security Remember me的超时时间在.and().rememberMe().tokenValiditySeconds(120)中设置，好像也要设置为60的倍数,
 * 当Remember me的超时时间大于Session的超时时间时，以Remember me的超时时间为准
 */
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
            .and().httpBasic()
            .and().rememberMe().tokenValiditySeconds(120) //此处是设置remember me的超时时间
            .and().sessionManagement().invalidSessionUrl("/login");//Session失效后的跳转地址,此处为跳到Spring Security默认的登录页
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

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        super.configure(webSecurity);
    }

}
