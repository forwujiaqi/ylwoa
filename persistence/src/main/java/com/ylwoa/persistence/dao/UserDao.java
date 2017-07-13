package com.ylwoa.persistence.dao;

import com.ylwoa.model.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> select(Map<String, Object> para);
}