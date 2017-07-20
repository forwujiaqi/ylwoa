package com.ylwoa.progress.impl;

import com.ylwoa.model.Excel;
import com.ylwoa.model.ExcelData;
import com.ylwoa.persistence.dao.ExcelDao;
import com.ylwoa.persistence.dao.ExcelDataDao;
import com.ylwoa.progress.IProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wubiqing on 2017/7/20.
 */
@Service
public class ProgressService implements IProgressService {

    @Autowired
    private ExcelDao excelDao;

    @Autowired
    private ExcelDataDao excelDataDao;

    @Override
    public List<Excel> getList(Map<String, Object> params) throws Exception {
        return excelDao.select(params);
    }

    @Override
    @Transactional
    public void insertExcel(Excel excel) throws Exception {
        excelDao.insertSelective(excel);
        excelDataDao.insertSelective(new ExcelData());
    }

    @Override
    @Transactional
    public void updateExcel(Excel excel) throws Exception {
        excelDao.updateByPrimaryKeySelective(excel);
        excelDataDao.insertSelective(new ExcelData());
    }

    @Override
    public void insertExcelData(ExcelData excelData) throws Exception {
        excelDataDao.insertSelective(new ExcelData());
    }
}
