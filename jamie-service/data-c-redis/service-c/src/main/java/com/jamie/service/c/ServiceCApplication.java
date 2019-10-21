package com.jamie.service.c;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = "com.jamie")
@EnableFeignClients(basePackages = "com.jamie") //Feign远程调用
@EnableDiscoveryClient        //开启微服务发现机制
@EnableDistributedTransaction //开启TX-LCN分布式事务
public class ServiceCApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCApplication.class, args);
    }

}
