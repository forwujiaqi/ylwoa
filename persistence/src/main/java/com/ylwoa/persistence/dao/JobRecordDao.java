package com.ylwoa.persistence.dao;

import com.ylwoa.model.JobRecord;

import java.util.List;
import java.util.Map;

public interface JobRecordDao {
    List<JobRecord> select(Map<String, Object> params);

    int deleteByPrimaryKey(Long id);

    int insert(JobRecord record);

    int insertSelective(JobRecord record);

    JobRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(JobRecord record);

    int updateByPrimaryKeyWithBLOBs(JobRecord record);

    int updateByPrimaryKey(JobRecord record);
}