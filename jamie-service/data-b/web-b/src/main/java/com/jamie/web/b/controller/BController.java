package com.jamie.web.b.controller;

import com.jamie.api.b.entity.TestEntity;
import com.jamie.api.b.service.BDataApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/b")
public class BController {

    @Autowired
    private BDataApi bDataApi;

    @RequestMapping("/getTestByB")
    public TestEntity getTest(){
        return bDataApi.getTestByB();
    }

}
