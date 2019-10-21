package com.jamie.api.b.service;

import com.jamie.api.b.entity.TestEntity;
import com.jamie.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-b", url = "${service.b}", configuration = FeignConfig.class)
public interface BDataApi {

    @PostMapping(Urls.getTestByB)
    TestEntity getTestByB();

    @PostMapping(Urls.insertB)
    void insertB(@RequestBody String msg);
}
