package com.jamie.service.mq.rest;

import com.jamie.api.mq.service.MessageApi;
import com.jamie.api.mq.service.Urls;
import com.jamie.api.mq.vo.NotifyVo;
import com.jamie.service.mq.biz.MessageBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRest implements MessageApi {
    @Autowired
    private MessageBiz messageBiz;

    @Override
    @PostMapping(Urls.sendMessage)
    public void sendMessage(@RequestBody NotifyVo notifyVo) {
        messageBiz.send(notifyVo);
    }

    @Override
    @PostMapping(Urls.successMessage)
    public int successMessage(@RequestBody NotifyVo notifyVo) {
        return messageBiz.successMessage(notifyVo);
    }

    @Override
    @PostMapping(Urls.failMessage)
    public int failMessage(@RequestBody NotifyVo notifyVo) {
        return messageBiz.failMessage(notifyVo);
    }

}
