package com.ylwoa.persistence.dao;

import com.ylwoa.model.ExcelData;

public interface ExcelDataDao {
    int deleteByPrimaryKey(Long id);

    int insert(String record);

    int insertSelective(String record);

    ExcelData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(String record);

    int updateByPrimaryKeyWithBLOBs(String record);

    int updateByPrimaryKey(ExcelData record);
}