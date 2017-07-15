package com.ylwoa.persistence.dao.impl;

import com.ylwoa.model.JobRecord;
import com.ylwoa.model.User;
import com.ylwoa.persistence.dao.JobRecordDao;
import com.ylwoa.persistence.dao.UserDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class JobRecordDaoImpl extends AbstractMySQLDao implements JobRecordDao {

    public static final String NAMESPACE = "com.ylwoa.dao.JobRecordMapper.";

    @Override
    public List<JobRecord> select(Map<String, Object> params) {
        return sqlSessionTemplate.selectList(NAMESPACE + "select", params);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return sqlSessionTemplate.delete(NAMESPACE + "deleteByPrimaryKey", id);
    }

    @Override
    public int insert(JobRecord record) {
        return sqlSessionTemplate.insert(NAMESPACE + "insert", record);
    }

    @Override
    public int insertSelective(JobRecord record) {
        return 0;
    }

    @Override
    public JobRecord selectByPrimaryKey(Long id) {
        return sqlSessionTemplate.selectOne(NAMESPACE + "selectByPrimaryKey",id);
    }

    @Override
    public int updateByPrimaryKeySelective(JobRecord record) {
        return sqlSessionTemplate.update(NAMESPACE + "updateByPrimaryKeySelective", record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(JobRecord record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(JobRecord record) {
        return 0;
    }
}
