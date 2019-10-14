package com.jamie.web.b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.jamie")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.jamie")
public class WebBApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBApplication.class, args);
    }

}
