package com.ylwoa.dailyrecord.impl;

import com.ylwoa.dailyrecord.IDailyRecordService;
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
public class DailyReocrdService implements IDailyRecordService {

    @Autowired
    private JobRecordDao jobRecordDao;


    @Override
    public List<JobRecord> getList(Map<String, Object> params) throws Exception {
        return jobRecordDao.select(params);
    }

    @Override
    public int insertDailyRecord(JobRecord jobRecord) throws Exception {
        return jobRecordDao.insert(jobRecord);
    }

    @Override
    public int updateDailyRecord(JobRecord jobRecord) throws Exception {
        return jobRecordDao.updateByPrimaryKeySelective(jobRecord);
    }
}
}
