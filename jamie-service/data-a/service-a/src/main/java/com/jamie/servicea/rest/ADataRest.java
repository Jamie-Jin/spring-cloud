package com.jamie.servicea.rest;

import com.jamie.api.a.entity.TestEntity;
import com.jamie.api.a.service.ADataApi;
import com.jamie.api.a.service.Urls;
import com.jamie.servicea.biz.ADataBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ADataRest implements ADataApi {
    @Autowired
    private ADataBiz dataBiz;

    @Override
    @PostMapping(Urls.test)
    public TestEntity getTest() {
        return dataBiz.getTest();
    }

}
