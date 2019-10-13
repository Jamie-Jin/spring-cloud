package com.jamie.weba.controller;

import com.jamie.api.a.entity.TestEntity;
import com.jamie.api.a.service.ADataApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a")
public class AController {

    @Autowired
    private ADataApi aDataApi;

    @RequestMapping("/test")
    public TestEntity getTest(){
        return aDataApi.getTest();
    }

}
