package com.ylwoa.persistence.dao;

import com.ylwoa.model.ExcelData;

public interface ExcelDataDao {
    int deleteByPrimaryKey(Long id);

    int insert(ExcelData excelData);

    int insertSelective(ExcelData excelData);

    ExcelData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExcelData excelData);

    int updateByPrimaryKeyWithBLOBs(ExcelData excelData);

    int updateByPrimaryKey(ExcelData excelData);
}