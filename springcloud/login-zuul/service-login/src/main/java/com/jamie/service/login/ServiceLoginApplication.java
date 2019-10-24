package com.jamie.service.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication(scanBasePackages = "com.jamie")
@EnableDiscoveryClient
// 开启Zuul网关
@EnableZuulProxy
// 使用Redis管理Session，扑街，最小过期时间系60秒！！！设置30s无效！！！
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60)
public class ServiceLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceLoginApplication.class, args);
    }

}
