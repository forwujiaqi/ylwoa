package com.ylwoa.persistence.dao.impl;

import com.ylwoa.model.Project;
import com.ylwoa.persistence.dao.ProjectDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProjectDaoImpl extends AbstractMySQLDao implements ProjectDao {

    public static final String NAMESPACE = "com.ylwoa.dao.ProjectMapper.";

    @Override
    public List<Project> select(Map<String, Object> params) {
        return sqlSessionTemplate.selectList(NAMESPACE + "select", params);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteByPrimaryKey", id);
    }

    @Override
    public int insert(Project project) {
        return sqlSessionTemplate.insert(NAMESPACE + "insert", project);
    }

    @Override
    public int insertSelective(Project project) {
        return 0;
    }

    @Override
    public Project selectByPrimaryKey(Long id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "selectByPrimaryKey",id);
    }

    @Override
    public int updateByPrimaryKeySelective(Project project) {
        return sqlSessionTemplate.update(NAMESPACE + "updateByPrimaryKeySelective", project);
    }

    @Override
    public int updateByPrimaryKey(Project project) {
        return 0;
    }
}
