package com.jamie.mysql_mybatis_base.mybatis_dao;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
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

    public int singleInsert(T entity){
        return sqlSessionTemplate.insert(getStatement(SINGLE_INSERT), entity);
    }

}
