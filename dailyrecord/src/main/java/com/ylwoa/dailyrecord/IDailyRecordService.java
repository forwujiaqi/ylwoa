package com.ylwoa.dailyrecord;

import com.ylwoa.model.JobRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by wubiqing on 2017/7/15.
 */
public interface IDailyRecordService {

    List<JobRecord> getList(Map<String, Object> params) throws Exception;

    int insertDailyRecord(JobRecord jobRecord) throws Exception;

    int updateDailyRecord(JobRecord jobRecord) throws Exception;

}
