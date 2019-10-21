package com.jamie.service.c.rest;

import com.jamie.api.c.service.CRedisApi;
import com.jamie.api.c.service.Urls;
import com.jamie.api.c.vo.Cvo;
import com.jamie.service.c.biz.CRedisBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CRedisRest implements CRedisApi {
    @Autowired
    private CRedisBiz cRedisBiz;

    @Override
    @PostMapping(Urls.insertC)
    public void insertC(@RequestBody Cvo cvo) {
        cRedisBiz.insertC(cvo);
    }
}
