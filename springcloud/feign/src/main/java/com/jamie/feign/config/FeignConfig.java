package com.jamie.feign.config;

import feign.Feign;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class FeignConfig {

    @Bean
    @Primary
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }

    // 保证跨模块调用接口时传参不会丢失
    @Bean
    public ErrorDecoder errorDecoder(){
        return new ErrorDecoder.Default();
    }

}
