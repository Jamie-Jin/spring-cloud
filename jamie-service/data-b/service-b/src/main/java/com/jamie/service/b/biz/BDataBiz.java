package com.jamie.service.b.biz;

import com.jamie.api.b.entity.TestEntity;
import com.jamie.service.b.dao.BDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BDataBiz {

    @Autowired
    private BDataDao bDataDao;

    public TestEntity getTest(){
        return bDataDao.getTest();
    }

}
