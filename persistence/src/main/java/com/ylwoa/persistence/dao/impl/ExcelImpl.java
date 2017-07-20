package com.ylwoa.persistence.dao.impl;

import com.ylwoa.model.ExcelData;
import com.ylwoa.persistence.dao.ExcelDataDao;
import org.springframework.stereotype.Repository;

/**
 * Created by wubiqing on 2017/7/20.
 */
@Repository
public class ExcelImpl implements ExcelDataDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(ExcelData excelData) {
        return 0;
    }

    @Override
    public int insertSelective(ExcelData excelData) {
        return 0;
    }

    @Override
    public ExcelData selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ExcelData excelData) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(ExcelData excelData) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(ExcelData excelData) {
        return 0;
    }
}
