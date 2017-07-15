package com.ylwoa.jobrecord;

import com.ylwoa.model.JobRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by wubiqing on 2017/7/15.
 */
public interface IJobRecordService {

    List<JobRecord> getList(Map<String, Object> params) throws Exception;

    int insertJobRecord(JobRecord jobRecord) throws Exception;

    int updateJobRecord(JobRecord jobRecord) throws Exception;

}
