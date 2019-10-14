package com.jamie.mysql_mybatis_base.datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@RefreshScope
public class MyBatisProperties {

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis.configuration.use-generated-keys}")
    private Boolean useGeneratedKeys;

    @Value("${mybatis.configuration.map-underscore-to-camel-case}")
    private Boolean mapUnderscoreToCamelCase;

    @Value("${mybatis.configuration.log-impl}")
    private String logImpl;

}
