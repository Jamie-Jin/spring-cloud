package com.jamie.service.b.rest;

import com.jamie.api.b.entity.TestEntity;
import com.jamie.api.b.service.BDataApi;
import com.jamie.api.b.service.Urls;
import com.jamie.service.b.biz.BDataBiz;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BDataRest implements BDataApi {
    @Autowired
    private BDataBiz bDataBiz;

    @Override
    @PostMapping(Urls.getTestByB)
    @HystrixCommand(fallbackMethod = "getTestByBFallback")
    public TestEntity getTestByB() {
        return bDataBiz.getTest();
    }

    @Override
    @PostMapping(Urls.insertB)
    public void insertB(@RequestBody String msg) {
        bDataBiz.insertB(msg);
    }

    // getTestByB 服务降级
    private TestEntity getTestByBFallback(){
        TestEntity testEntity = new TestEntity();
        testEntity.setMsg("数据模块B不可用");
        return testEntity;
    }

}
