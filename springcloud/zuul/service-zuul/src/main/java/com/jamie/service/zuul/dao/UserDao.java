package com.jamie.service.zuul.dao;

import com.jamie.db_mybatis_base.dao.BaseDao;
import com.jamie.service.zuul.pojo.entity.UserEntity;
import com.jamie.service.zuul.pojo.vo.UserRoleVo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class UserDao extends BaseDao<UserEntity> {

    public UserEntity getUserByName(String name){
        HashMap<String, Object> param = new HashMap<>();
        param.put("name", name);
        return getSqlSessionTemplate().selectOne(getStatement("getUserByName"), param);
    }

    public List<UserRoleVo> getUserRoleByName(String name){
        HashMap<String, Object> param = new HashMap<>();
        param.put("name", name);
        return getSqlSessionTemplate().selectList(getStatement("getUserRoleByName"), param);

    }

}
