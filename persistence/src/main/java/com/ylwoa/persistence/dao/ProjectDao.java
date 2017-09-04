package com.ylwoa.persistence.dao;

import com.ylwoa.model.Project;

import java.util.List;
import java.util.Map;

public interface ProjectDao {
    List<Project> select(Map<String, Object> params);

    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
}