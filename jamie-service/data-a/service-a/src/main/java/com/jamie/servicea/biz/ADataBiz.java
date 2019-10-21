package com.jamie.servicea.biz;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.google.gson.Gson;
import com.jamie.api.a.entity.TestEntity;
import com.jamie.api.a.vo.AVo;
import com.jamie.api.mq.service.MessageApi;
import com.jamie.api.mq.vo.NotifyVo;
import com.jamie.servicea.dao.ADataDao;
import com.jamie.utilbase.util.GUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ADataBiz {
    @Autowired
    private ADataDao aDataDao;

    @Autowired
    private MessageApi messageApi;

    public TestEntity getTest() {
        try {
            AVo messageVo = new AVo();
            messageVo.setAge(25);
            messageVo.setName("杨嘉晋");
            messageVo.setHeight(170.50);
            messageVo.setId(440681199407250217L);
            messageVo.setMoney(new BigDecimal(92000.50));
            List<String> hobbies = new ArrayList<>();
            hobbies.add("笔记本");
            hobbies.add("HiFi");
            messageVo.setHobbies(hobbies);
            messageVo.setToday(new Date());

            NotifyVo notifyVo = new NotifyVo();
            notifyVo.setNotifyId(GUID.getUUID());   //消息唯一标识
            notifyVo.setRoutingKey("mq.A_TO_B");    //消息路由Key
            notifyVo.setBody(new Gson().toJson(messageVo));//消息内容

            messageApi.sendMessage(notifyVo);
        } catch (Exception e){
            e.printStackTrace();
        }

        return aDataDao.getTest();
    }

    @LcnTransaction   // TX-LCN分布式事务注解
    @Transactional    // 本地事务
    public void insertA(String msg){
        TestEntity testEntity = new TestEntity();
        testEntity.setMsg(msg);

        aDataDao.singleInsert(testEntity);

        //throw new NullPointerException();
    }

}
