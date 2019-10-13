package com.jamie.api.a.service;

import com.jamie.api.a.entity.TestEntity;
import com.jamie.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "service-a", url = "http://localhost:1001" ,configuration = FeignConfig.class)
public interface ADataApi {

    @PostMapping(Urls.test)
    TestEntity getTest();

}
