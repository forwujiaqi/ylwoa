package com.ylwoa.progress.impl;

import com.ylwoa.common.Commons;
import com.ylwoa.model.Excel;
import com.ylwoa.model.ExcelData;
import com.ylwoa.model.User;
import com.ylwoa.persistence.dao.ExcelDao;
import com.ylwoa.persistence.dao.ExcelDataDao;
import com.ylwoa.progress.IProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ylwoa.common.Commons.ACTIVE_STATE;
import static com.ylwoa.common.Commons.INACTIVE_STATE;
import static com.ylwoa.common.Commons.PROGRESS;

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
    public List<Excel> getListById(Map<String, Object> params) throws Exception {
        return excelDao.selectById(params);
    }

    // TODO @Transactional不起作用
    @Override
    @Transactional
    public void insertExcel(Excel excel, User user) throws Exception {
        excel.setVersion((long) 1);
        excel.setExcelType(PROGRESS);
        excelDao.insert(excel);

        ExcelData excelData = new ExcelData();
        Date now = new Date();
        excelData.setUpdateUserId(user.getId());
        excelData.setUpdateUserName(user.getRealName());
        excelData.setUpdateTime(now);
        excelData.setCreateUserId(user.getId());
        excelData.setCreateTime(now);
        excelData.setCreateUserName(user.getRealName());

        Commons.DateRange dateRange =Commons.parseDateRange(excel.getProgressRange());
        excelData.setPlanStartDate(dateRange.getStart());
        excelData.setPlanEndDate(dateRange.getEnd());

        excelData.setExcelId(excel.getId());
        excelData.setExcelName(excel.getExcelName());
        excelData.setDataJson(excel.getDataJson());
        excelData.setStatus(0);
        excelData.setDeleteFlg(ACTIVE_STATE);
        excelData.setVersion((long) 1);
        excelDataDao.insert(excelData);
    }

    @Override
    @Transactional
    public void updateExcel(Excel excel, User user) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("excelId", excel.getId());
        List<Excel> excelList = excelDao.selectById(params);
        if (excelList != null && excelList.size() > 0) {
            Excel excelResult = excelList.get(0);

            ExcelData excelData = new ExcelData();
            Date now = new Date();
            excelData.setUpdateUserId(user.getId());
            excelData.setUpdateUserName(user.getRealName());
            excelData.setUpdateTime(now);

            excelData.setCreateUserId(excelResult.getCreateUserId());
            excelData.setCreateTime(excelResult.getCreateTime());
            excelData.setCreateUserName(excelResult.getCreateUserName());

            Commons.DateRange dateRange =Commons.parseDateRange(excel.getProgressRange());
            excelData.setPlanStartDate(dateRange.getStart());
            excelData.setPlanEndDate(dateRange.getEnd());

            excelData.setExcelId(excelResult.getId());
            excelData.setExcelName(excel.getExcelName());
            excelData.setDataJson(excel.getDataJson());
            excelData.setStatus(excel.getStatus());
            excelData.setVersion(excelResult.getVersion() + 1);
            excelData.setDeleteFlg(ACTIVE_STATE);
            excelDataDao.insert(excelData);

            Excel excelForUpdate = new Excel();
            excelForUpdate.setId(excel.getId());
            excelForUpdate.setVersion(excelResult.getVersion() + 1);
            excelDao.updateByPrimaryKeySelective(excelForUpdate);
        }
    }

    @Override
    public void deleteExcel(Excel excel, User user) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("excelId", excel.getId());
        List<Excel> excelList = excelDao.selectById(params);
        if (excelList != null && excelList.size() > 0) {
            Excel excelResult = excelList.get(0);

            ExcelData excelData = new ExcelData();
            Date now = new Date();
            excelData.setUpdateUserId(user.getId());
            excelData.setUpdateUserName(user.getRealName());
            excelData.setUpdateTime(now);

            excelData.setCreateUserId(excelResult.getCreateUserId());
            excelData.setCreateTime(excelResult.getCreateTime());
            excelData.setCreateUserName(excelResult.getCreateUserName());

            excelData.setPlanStartDate(excelResult.getPlanStartDate());
            excelData.setPlanEndDate(excelResult.getPlanEndDate());

            excelData.setExcelId(excelResult.getId());
            excelData.setExcelName(excelResult.getExcelName());
            excelData.setDataJson(excelResult.getDataJson());
            excelData.setStatus(excelResult.getStatus());
            excelData.setVersion(excelResult.getVersion() + 1);
            excelData.setDeleteFlg(INACTIVE_STATE);
            excelDataDao.insert(excelData);

            Excel excelForUpdate = new Excel();
            excelForUpdate.setId(excel.getId());
            excelForUpdate.setVersion(excelResult.getVersion() + 1);
            excelDao.updateByPrimaryKeySelective(excelForUpdate);
        }
    }
}
