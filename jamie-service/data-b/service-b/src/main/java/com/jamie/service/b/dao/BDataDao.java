package com.jamie.service.b.dao;

import com.jamie.api.b.entity.TestEntity;
import com.jamie.mybatis.base.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class BDataDao extends BaseDao<TestEntity> {

    public TestEntity getTest(){
        return getSqlSessionTemplate().selectOne(getStatement("getTest"));
    }

}
