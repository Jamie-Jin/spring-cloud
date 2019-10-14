package com.jamie.service.b.rest;

import com.jamie.api.b.entity.TestEntity;
import com.jamie.api.b.service.BDataApi;
import com.jamie.api.b.service.Urls;
import com.jamie.service.b.biz.BDataBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BDataRest implements BDataApi {
    @Autowired
    private BDataBiz bDataBiz;

    @Override
    @PostMapping(Urls.test)
    public TestEntity getTest() {
        return bDataBiz.getTest();
    }

}
