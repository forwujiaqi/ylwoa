package com.ylwoa.persistence.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;


public abstract class AbstractMySQLDao {
    @Resource
    protected SqlSessionTemplate sqlSessionTemplate;
}
