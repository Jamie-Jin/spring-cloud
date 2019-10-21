package com.jamie.api.c.service;

import com.jamie.api.c.vo.Cvo;
import com.jamie.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-c", url = "${service.c}", configuration = FeignConfig.class)
public interface CRedisApi {

    @PostMapping(Urls.insertC)
    void insertC(@RequestBody Cvo cvo);

}
