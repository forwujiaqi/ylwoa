package com.ylwoa.progress;

import com.ylwoa.model.Excel;
import com.ylwoa.model.ExcelData;

import java.util.List;
import java.util.Map;

/**
 * Created by wubiqing on 2017/7/20.
 */
public interface IProgressService {

    List<Excel> getList(Map<String, Object> params) throws Exception;

    void insertExcel(Excel excel) throws Exception;

    void updateExcel(Excel excel) throws Exception;

    void insertExcelData(ExcelData excelData) throws Exception;
}
