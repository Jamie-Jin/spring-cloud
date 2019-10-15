package com.jamie.servicea.dao;

import com.jamie.api.a.entity.TestEntity;
import com.jamie.db_mybatis_base.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class ADataDao extends BaseDao<TestEntity> {

    public TestEntity getTest(){
        return getSqlSessionTemplate().selectOne(getStatement("getTest"));
    }

}
