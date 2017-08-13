package com.ylwoa.persistence.dao.impl;

import com.ylwoa.model.User;
import com.ylwoa.persistence.dao.UserDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends AbstractMySQLDao implements UserDao {

    public static final String NAMESPACE = "com.ylwoa.dao.UserMapper.";

    @Override
    public List<User> select(Map<String, Object> params) {
        return sqlSessionTemplate.selectList(NAMESPACE+"select", params);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(User user) {
        return sqlSessionTemplate.insert(NAMESPACE + "insert", user);
    }

    @Override
    public int insertSelective(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return sqlSessionTemplate.selectOne(NAMESPACE+"selectByPrimaryKey",id);
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return sqlSessionTemplate.update(NAMESPACE + "updateByPrimaryKeySelective", user);
    }

    @Override
    public int updateByPrimaryKey(User user) {
        return 0;
    }
}
