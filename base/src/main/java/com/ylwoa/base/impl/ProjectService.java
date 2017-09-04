package com.ylwoa.base.impl;

import com.ylwoa.base.IProjectService;
import com.ylwoa.model.Project;
import com.ylwoa.persistence.dao.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wubiqing on 2017/7/15.
 */
@Service
public class ProjectService implements IProjectService {

    @Autowired
    private ProjectDao projectDao;


    @Override
    public List<Project> getList(Map<String, Object> params) throws Exception {
        return projectDao.select(params);
    }

    @Override
    public int insertProject(Project project) throws Exception {
        return projectDao.insert(project);
    }

    @Override
    public int updateProject(Project project) throws Exception {
        return projectDao.updateByPrimaryKeySelective(project);
    }
}
