package com.jamie.tx.manager;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableTransactionManagerServer //开启Tx-Manager分布式事务管理
@MapperScan("com.jamie.tx.manager.dao")
public class TxManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TxManagerApplication.class, args);
    }

}
