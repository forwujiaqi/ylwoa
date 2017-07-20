package com.ylwoa.persistence.dao.impl;

import com.ylwoa.model.Excel;
import com.ylwoa.persistence.dao.ExcelDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by wubiqing on 2017/7/20.
 */
@Repository
public class ExcelDataImpl implements ExcelDao {
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Excel record) {
        return 0;
    }

    @Override
    public int insertSelective(Excel excel) {
        return 0;
    }

    @Override
    public Excel selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Excel excel) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Excel excel) {
        return 0;
    }

    @Override
    public List<Excel> select(Map<String, Object> params) {
        return null;
    }
}
