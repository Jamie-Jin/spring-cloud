package com.jamie.servicea.biz;

import com.jamie.api.a.entity.TestEntity;
import com.jamie.rabbitmq.service.RabbitSender;
import com.jamie.redis.api.RedisApi;
import com.jamie.servicea.dao.ADataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ADataBiz {
    @Autowired
    private ADataDao aDataDao;

    @Autowired
    private RabbitSender rabbitSender;

    public TestEntity getTest() {
        try {
            rabbitSender.send("Hello world", new HashMap<>());
        } catch (Exception e){
            e.printStackTrace();
        }

        return aDataDao.getTest();
    }

}
