package com.jamie.web.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.jamie")
@EnableDiscoveryClient
public class WebLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebLoginApplication.class, args);
    }

}
