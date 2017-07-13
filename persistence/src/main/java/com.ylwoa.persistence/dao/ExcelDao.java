package com.ylwoa.persistence.dao;

import com.ylwoa.model.Excel;

public interface ExcelDao {
    int deleteByPrimaryKey(Long id);

    int insert(Excel record);

    int insertSelective(Excel record);

    Excel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Excel record);

    int updateByPrimaryKey(Excel record);
}