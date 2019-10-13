package com.jamie.mybatis.base;

import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 公用Dao类，T为数据库实体类
 * @param <T>
 */
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

    // 拼接Sql完整路径
    public String getStatement(String sqlId){
        String name = this.getClass().getName();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name).append(".").append(sqlId);
        return stringBuilder.toString();
    }

    // 插入单条数据公用方法
    public int singleInsert(T entity){
        return sqlSessionTemplate.insert(getStatement(SINGLE_INSERT), entity);
    }

}
