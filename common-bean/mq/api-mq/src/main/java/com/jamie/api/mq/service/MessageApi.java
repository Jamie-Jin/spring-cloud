package com.jamie.api.mq.service;

import com.jamie.api.mq.vo.NotifyVo;
import com.jamie.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "service-rabbitmq", url = "${service.mq}", configuration = FeignConfig.class)
public interface MessageApi {

    /**
     * 发送MQ
     * @param notifyVo
     * @return
     */
    @PostMapping(Urls.sendMessage)
    void sendMessage(@RequestBody NotifyVo notifyVo);

    @PostMapping(Urls.successMessage)
    int successMessage(@RequestBody NotifyVo notifyVo);

    @PostMapping(Urls.failMessage)
    int failMessage(@RequestBody NotifyVo notifyVo);
}
