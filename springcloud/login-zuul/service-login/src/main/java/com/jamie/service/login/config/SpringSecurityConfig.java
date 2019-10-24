package com.jamie.service.login.config;

import com.jamie.service.login.service.LoginUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security使用Redis保存Session时，超时时间的设置在@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)，最小超时时间为60s，好像超时时间需要设置为60的倍数
 * Spring Security Remember me的超时时间在.and().rememberMe().tokenValiditySeconds(120)中设置，好像也要设置为60的倍数,
 * 当Remember me的超时时间大于Session的超时时间时，以Remember me的超时时间为准
 */
@EnableWebSecurity    // 启用Spring Security
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginUserDetailService loginUserDetailService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailHandler loginFailHandler;

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
            .antMatchers("/login-success").hasRole("ADMIN")
            // 其余路径无需验证
            .anyRequest().permitAll()

            // 允许匿名访问无限制的路径
            .and().anonymous()

            // 使用自定义登录页，见LoginController
            // loginProcessingUrl一定要配，一定要配，写上要怎么配
            // loginPage配上自定义登录页的controller路径(要加上zuul的前缀)，loginProcessingUrl配置自定义登录页Controller路径（不需夹zuul的前缀）
            .and().formLogin().loginPage("/user/login-page").loginProcessingUrl("/login-page")
                //.successForwardUrl("/user/login-success")
            .permitAll()
            .successHandler(loginSuccessHandler) //自定义登录成功处理器
            .failureHandler(loginFailHandler)   //自定义登录失败处理器

            .and().httpBasic()
            //.and().rememberMe().tokenValiditySeconds(120) //此处是设置remember me的超时时间
            .and().sessionManagement().invalidSessionUrl("/user/login-page")//Session失效后的跳转地址,此处为跳到Spring Security默认的登录页

            .and().csrf().disable();
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
                .userDetailsService(loginUserDetailService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        super.configure(webSecurity);
    }

}
