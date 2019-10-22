package com.jamie.service.c.biz;

import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.codingapi.txlcn.tracing.TracingContext;
import com.google.common.collect.Sets;
import com.jamie.api.c.vo.Cvo;
import com.jamie.redis.api.RedisApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CRedisBiz {

    @Autowired
    private RedisApi redisApi;

    private ConcurrentHashMap<String, Set<String>> ids = new ConcurrentHashMap<>();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Tx-LCN分布式事务， TCC事务需要开发者给出执行事务的方法，事务提交方法，事务回滚方法
    @TccTransaction(propagation = DTXPropagation.SUPPORTS, confirmMethod = "confirmTccAdd", cancelMethod = "cancelTccAdd")
    // 本地事务
    @Transactional(rollbackFor = Exception.class)
    public void insertC(Cvo cvo){
        // Redis设值
        redisApi.set(cvo.getKey(), cvo.getVal());

        // Sets.newHashSet: com.google.common.collect
        ids.putIfAbsent(TracingContext.tracing().groupId(), Sets.newHashSet(cvo.getKey()));
        ids.get(TracingContext.tracing().groupId()).add(cvo.getKey());
    }

    // 事务提交 (注意：方法参数要和执行事务方法【insertC】的参数一致)
    public void confirmTccAdd(Cvo cvo){
        ids.get(TracingContext.tracing().groupId()).forEach(id -> {
            ids.get(TracingContext.tracing().groupId()).remove(id);
        });
    }

    // 事务回滚 (注意：方法参数要和执行事务方法【insertC】的参数一致)
    public void cancelTccAdd(Cvo cvo){
        ids.get(TracingContext.tracing().groupId()).forEach(id -> {
            redisApi.set(id, "", 1);
        });
    }
}
