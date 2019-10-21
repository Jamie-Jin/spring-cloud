package com.jamie.api.a.service;

import com.jamie.api.a.entity.TestEntity;
import com.jamie.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "service-a", url = "${service.a}", configuration = FeignConfig.class)
public interface ADataApi {

    @PostMapping(Urls.getTestByA)
    TestEntity getTestByA();

    @RequestMapping(Urls.getTest)
    List<String> getTest();

    @PostMapping(Urls.insertA)
    void insertA(@RequestBody String msg);
}
