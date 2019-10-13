package com.jamie.weba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.jamie")
@EnableFeignClients(basePackages = "com.jamie")
@EnableDiscoveryClient
public class WebAApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAApplication.class, args);
    }

}
