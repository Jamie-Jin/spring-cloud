package com.jamie.service.mq.dao;

import com.jamie.api.mq.entity.MessageEntity;
import com.jamie.api.mq.vo.NotifyVo;
import com.jamie.db_mybatis_base.dao.BaseDao;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class MessageDao extends BaseDao<MessageEntity> {

    /**
     * 插入预消息
     * @param notifyVo
     * @return
     */
    public int insertPreNotify(NotifyVo notifyVo){
        MessageEntity entity = new MessageEntity();
        BeanUtils.copyProperties(notifyVo, entity);

        return singleInsert(entity);
    }

    /**
     * 更新消息消费状态为1（成功消费）
     * @param notifyVo
     * @return
     */
    public int successMessage(NotifyVo notifyVo){
        HashMap<String, String> param = new HashMap<>();
        param.put("notifyId", notifyVo.getNotifyId());

        return getSqlSessionTemplate().update(getStatement("successMessage"), param);
    }

    /**
     * 更新消息消费状态为-1（成功失败）
     * @param notifyVo
     * @return
     */
    public int failMessage(NotifyVo notifyVo){
        HashMap<String, String> param = new HashMap<>();
        param.put("notifyId", notifyVo.getNotifyId());

        return getSqlSessionTemplate().update(getStatement("failMessage"), param);
    }

}
