package com.ylwoa.base;

import com.ylwoa.model.Project;

import java.util.List;
import java.util.Map;

/**
 * Created by wubiqing on 2017/7/15.
 */
public interface IProjectService {

    List<Project> getList(Map<String, Object> params) throws Exception;

    int insertProject(Project project) throws Exception;

    int updateProject(Project project) throws Exception;

}
