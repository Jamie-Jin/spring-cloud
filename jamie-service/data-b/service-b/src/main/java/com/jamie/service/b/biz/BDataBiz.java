package com.jamie.service.b.biz;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.jamie.api.b.entity.TestEntity;
import com.jamie.service.b.dao.BDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BDataBiz {

    @Autowired
    private BDataDao bDataDao;

    public TestEntity getTest(){
        return bDataDao.getTest();
    }


    @LcnTransaction // TX-LCN分布式事务注解
    @Transactional  //本地事务
    public void insertB(String msg){
        TestEntity testEntity = new TestEntity();
        testEntity.setMsg(msg);

        bDataDao.singleInsert(testEntity);

        //throw new NullPointerException();
    }

}
