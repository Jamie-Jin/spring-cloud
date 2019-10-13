package com.jamie.servicea.biz;

import com.jamie.api.a.entity.TestEntity;
import com.jamie.servicea.dao.ADataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ADataBiz {
    @Autowired
    private ADataDao aDataDao;

    public TestEntity getTest(){
        return aDataDao.getTest();
    }

}
