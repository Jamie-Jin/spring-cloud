package com.jamie.weba.controller;

import com.jamie.api.a.entity.TestEntity;
import com.jamie.api.a.service.ADataApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/a")
public class AController {

    @Autowired
    private ADataApi aDataApi;

    @RequestMapping("/getTestByA")
    public TestEntity getTestByA(){
        return aDataApi.getTestByA();
    }

    @RequestMapping("/getTest")
    public List<String> getTest(){
        return aDataApi.getTest();
    }

}
