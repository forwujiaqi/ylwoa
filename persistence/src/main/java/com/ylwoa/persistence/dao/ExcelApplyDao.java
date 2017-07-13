package com.ylwoa.persistence.dao;

import com.ylwoa.model.ExcelApply;

public interface ExcelApplyDao {
    int deleteByPrimaryKey(Long id);

    int insert(ExcelApply record);

    int insertSelective(ExcelApply record);

    ExcelApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExcelApply record);

    int updateByPrimaryKey(ExcelApply record);
}