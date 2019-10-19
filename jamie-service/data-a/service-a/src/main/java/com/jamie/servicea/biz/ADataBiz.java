package com.jamie.servicea.biz;

import com.jamie.api.a.entity.TestEntity;
import com.jamie.api.a.vo.AVo;
import com.jamie.rabbitmq.service.RabbitSender;
import com.jamie.servicea.dao.ADataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ADataBiz {
    @Autowired
    private ADataDao aDataDao;

    @Autowired
    private RabbitSender rabbitSender;

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

            rabbitSender.send(messageVo, "mq.A_TO_B");
        } catch (Exception e){
            e.printStackTrace();
        }

        return aDataDao.getTest();
    }

}
