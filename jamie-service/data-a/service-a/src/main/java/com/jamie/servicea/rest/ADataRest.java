package com.jamie.servicea.rest;

import com.jamie.api.a.entity.TestEntity;
import com.jamie.api.a.service.ADataApi;
import com.jamie.api.a.service.Urls;
import com.jamie.api.b.service.BDataApi;
import com.jamie.servicea.biz.ADataBiz;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ADataRest implements ADataApi {
    private static final Logger logger = LoggerFactory.getLogger(ADataRest.class);

    @Autowired
    private ADataBiz aDataBiz;

    @Autowired
    private BDataApi bDataApi;

    // 访问Service-A数据
    @Override
    @PostMapping(Urls.getTestByA)
    @HystrixCommand(fallbackMethod = "getTestByAFallback")
    public TestEntity getTestByA() {
        return aDataBiz.getTest();
    }

    // getTestByA 服务降级方法
    private TestEntity getTestByAFallback(){
        TestEntity testEntity = new TestEntity();
        testEntity.setMsg("数据模块A不可用");
        return testEntity;
    }


    // 访问service-a以及service-b数据
    @Override
    @GetMapping(Urls.getTest)
    @HystrixCommand(fallbackMethod = "getBTestFallback")
    public List<String> getTest() {
        TestEntity aTest = aDataBiz.getTest();
        com.jamie.api.b.entity.TestEntity bTest = bDataApi.getTestByB();

        List<String> tests = new ArrayList<>();
        tests.add(aTest.getMsg());
        tests.add(bTest.getMsg());

        return tests;
    }

    // getTest 服务降级方法
    private List<String> getBTestFallback(){
        logger.error("数据模块B不可用");

        TestEntity aTest = aDataBiz.getTest();
        List<String> tests = new ArrayList<>();
        tests.add(aTest.getMsg());
        tests.add("数据模块B不可用");
        return tests;
    }

}
