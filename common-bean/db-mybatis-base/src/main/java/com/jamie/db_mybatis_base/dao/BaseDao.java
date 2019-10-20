package com.jamie.db_mybatis_base.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公用Dao类
 * @param <T>
 */
public class BaseDao<T> extends SqlSessionDaoSupport {

    private static final String SINGLE_INSERT = "insert";

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public String getStatement(String sqlId){
        String name = this.getClass().getName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(".").append(sqlId);
        return stringBuilder.toString();
    }

    /**
     * 插入单条记录
     * @param entity
     * @return
     */
    public int singleInsert(T entity){
        return sqlSessionTemplate.insert(getStatement(SINGLE_INSERT), entity);
    }

}
