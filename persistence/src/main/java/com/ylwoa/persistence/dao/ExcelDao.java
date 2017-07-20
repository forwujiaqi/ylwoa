package com.ylwoa.persistence.dao;

import com.ylwoa.model.Excel;

import java.util.List;
import java.util.Map;

public interface ExcelDao {
    int deleteByPrimaryKey(Long id);

    int insert(Excel record);

    int insertSelective(Excel excel);

    Excel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Excel excel);

    int updateByPrimaryKey(Excel excel);

    List<Excel> select(Map<String, Object> params);
}