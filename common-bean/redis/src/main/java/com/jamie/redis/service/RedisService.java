package com.jamie.redis.service;

import com.jamie.redis.api.RedisApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService implements RedisApi {
    private static final Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean expire(String key, long seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    @Override
    public boolean expire(String key, long time, TimeUnit timeUnit) {
        return redisTemplate.expire(key, time, timeUnit);
    }

    @Override
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    @Override
    public boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    @Transactional
    public void set(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);
    }

    @Override
    public void set(String key, Object val, long time, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, val, time, timeUnit);
    }

    @Override
    public void set(String key, Object val, long time) {
        redisTemplate.opsForValue().set(key, val, time, TimeUnit.SECONDS);
    }

    @Override
    public Object getAndSet(String key, Object val) {
        return redisTemplate.opsForValue().getAndSet(key, val);
    }

    @Override
    public boolean setIfAbsent(String key, Object val) {
        return redisTemplate.opsForValue().setIfAbsent(key, val);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Long increment(String key, long num) {
        return redisTemplate.opsForValue().increment(key, num);
    }

    @Override
    public Double increment(String key, Double incr) {
        return redisTemplate.opsForValue().increment(key, incr);
    }

    @Override
    public Long valSize(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    @Override
    public Long listSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public Long leftPush(String key, List<Object> list) {
        return redisTemplate.opsForList().leftPushAll(key, list);
    }

    @Override
    public Long rightPush(String key, List<Object> list) {
        return redisTemplate.opsForList().rightPushAll(key, list);
    }

    @Override
    public Object getListValByIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    @Override
    public void hashPut(String key, String hashKey, Object val) {
        redisTemplate.opsForHash().put(key, hashKey, val);
    }

    @Override
    public boolean hashPutIfAbsent(String key, String hashKey, Object val) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, val);
    }

    @Override
    public Object getHashByKeyHashKey(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }
}
