package com.ylwoa.persistence.dao;

import com.ylwoa.model.ExcelData;
import com.ylwoa.model.ExcelDataWithBLOBs;

public interface ExcelDataDao {
    int deleteByPrimaryKey(Long id);

    int insert(ExcelDataWithBLOBs record);

    int insertSelective(ExcelDataWithBLOBs record);

    ExcelDataWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExcelDataWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ExcelDataWithBLOBs record);

    int updateByPrimaryKey(ExcelData record);
}