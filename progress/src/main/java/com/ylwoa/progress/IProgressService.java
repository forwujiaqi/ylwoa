package com.ylwoa.progress;

import com.ylwoa.model.Excel;
import com.ylwoa.model.ExcelData;
import com.ylwoa.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by wubiqing on 2017/7/20.
 */
public interface IProgressService {

    List<Excel> getList(Map<String, Object> params) throws Exception;

    List<Excel> getListById(Map<String, Object> params) throws Exception;

    void insertExcel(Excel excel, User user) throws Exception;

    void updateExcel(Excel excel,User user) throws Exception;

    void deleteExcel(Excel excel,User user) throws Exception;
}
