package com.jamie.redis.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis操作接口，供其他模块使用
 */
public interface RedisApi {

    /** Redis基础函数 start **/
    // 设置val的过期时间（单位为秒），返回true/false
    boolean expire(String key, long seconds);

    // 设置val的过期时间(可指定时间单位)，返回影响的记录数
    boolean expire(String key, long time, TimeUnit timeUnit);

    // 查询key的过期时间
    long ttl(String key);

    // 判断key是否存在
    boolean exist(String key);

    /** Redis基础函数 end **/

    /** String类型操作 start **/
    // 根据key设置val
    void set(String key, Object val);

    // 根据Key设置val，并设置过期时间
    void set(String key, Object val, long time, TimeUnit timeUnit);

    void set(String key, Object val, long time);

    // 根据Key设置Val，并返回val
    Object getAndSet(String key, Object val);

    // 设值时先判断该key是否存在，存在返回false
    boolean setIfAbsent(String key, Object val);

    // 获取val
    Object get(String key);

    // 自增1
    Long increment(String key, long num);

    // 自增浮点数
    Double increment(String key, Double incr);

    // 获取Val长度
    Long valSize(String key);

    /** String类型操作 end **/

    /** List类型操作 start **/
    // 获取列表长度
    Long listSize(String key);

    // 元素从左侧插入列表（倒序插入）
    Long leftPush(String key, List<Object> list);

    // 元素从右侧插入列表（正序插入）
    Long rightPush(String key, List<Object> list);

    // 根据索引获取列表的值
    Object getListValByIndex(String key, long index);
    /** List类型操作 end **/

    /** Hash类型操作 start **/
    // 设置Hash值
    void hashPut(String key, String hashKey, Object val);

    // 设置val前先判断key和hashKey是否存在，存在返回false
    boolean hashPutIfAbsent(String key, String hashKey, Object val);

    // 根据key和hashKey获取val
    Object getHashByKeyHashKey(String key, String hashKey);
    /** Hash类型操作 end **/
}
