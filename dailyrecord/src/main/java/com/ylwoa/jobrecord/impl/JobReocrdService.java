package com.ylwoa.jobrecord.impl;

import com.ylwoa.jobrecord.IJobRecordService;
import com.ylwoa.model.JobRecord;
import com.ylwoa.persistence.dao.JobRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wubiqing on 2017/7/15.
 */
@Service
public class JobReocrdService implements IJobRecordService {

    @Autowired
    private JobRecordDao jobRecordDao;


    @Override
    public List<JobRecord> getList(Map<String, Object> params) throws Exception {
        return jobRecordDao.select(params);
    }

    @Override
    public int insertJobRecord(JobRecord jobRecord) throws Exception {
        return jobRecordDao.insert(jobRecord);
    }

    @Override
    public int updateJobRecord(JobRecord jobRecord) throws Exception {
        return jobRecordDao.updateByPrimaryKeySelective(jobRecord);
    }
}
