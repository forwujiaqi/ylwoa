package com.ylwoa.web.controller;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.ylwoa.jobrecord.IJobRecordService;
import com.ylwoa.model.JobRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

import static com.ylwoa.common.Commons.ACTIVE_STATE;
import static com.ylwoa.common.Commons.INACTIVE_STATE;

/**
 * Created by wubiqing on 2017/7/14.
 */
@Controller
@RequestMapping("/jobRecord")
public class JobRecordController {

    @Autowired
    private IJobRecordService jobRecordService;

    private static transient Logger log = LoggerFactory.getLogger(JobRecordController.class);

    @RequestMapping(value = "/list/{pageNo}")
    public ModelAndView dailyRecordList(@PathVariable String pageNo) {
        ModelAndView mv = new ModelAndView("/jobRecord/list");
        Map<String, Object> paras = Maps.newHashMap();
        List<JobRecord> jobRecordList;
        try {
            paras.put("deleteFlg", ACTIVE_STATE);
            jobRecordList = jobRecordService.getList(paras);
        } catch (Exception e) {
            log.error("dailyRecordList error", pageNo, e);
            mv.addObject("success", false);
            mv.addObject("pageData", null);
            return mv;
        }
        mv.addObject("success", true);
        mv.addObject("pageData", jobRecordList);
        if ("0".equals(pageNo)) {
            mv.addObject("pageNo", pageNo);
        } else {
            mv.addObject("pageNo", "9999");
        }

        return mv;
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/jobRecord/add");
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(JobRecord jobRecord) {
        ModelAndView mv = new ModelAndView("/jobRecord/list");
        try {
            Date now = new Date();
            jobRecord.setRecordType(1);
            jobRecord.setUpdateUserId(1);
            jobRecord.setUpdateTime(now);
            jobRecord.setCreateUserId(1);
            jobRecord.setCreateTime(now);
            jobRecord.setDeleteFlg(ACTIVE_STATE);
            jobRecord.setOwnerName(jobRecord.getOwnerName());
            jobRecordService.insertJobRecord(jobRecord);
            mv.setViewName("forward:/jobRecord/list/0");
        } catch (Exception e) {
            log.error("add error", jobRecord, e);
        }

        return mv;
    }


    @RequestMapping(value = "/toView/{recordId}", method = RequestMethod.GET)
    public ModelAndView toView(@PathVariable String recordId) {
        ModelAndView mv = new ModelAndView("/jobRecord/view");
        Map<String, Object> paras = Maps.newHashMap();
        paras.put("id", recordId);
        try {
            List<JobRecord> jobRecordList = jobRecordService.getList(paras);
            if (null != jobRecordList && jobRecordList.size() > 0) {
                mv.addObject("success", true);
                mv.addObject("data", jobRecordList.get(0));
            }
        } catch (Exception e) {
            log.error("toView error", recordId, e);
            mv.addObject("success", false);
        }
        return mv;
    }

    @RequestMapping(value = "/delete/{recordId}")
    public ModelAndView delete(@PathVariable String recordId) {
        ModelAndView mv = new ModelAndView("/jobRecord/list");
        try {
            JobRecord jobRecord = new JobRecord();
            jobRecord.setId(Long.parseLong(recordId));
            jobRecord.setDeleteFlg(INACTIVE_STATE);
            jobRecordService.updateJobRecord(jobRecord);
            mv.setViewName("forward:/jobRecord/list/9999");
        } catch (Exception e) {
            log.error("delete error", recordId, e);
        }

        return mv;
    }

    @RequestMapping(value = "/toEdit/{recordId}")
    public ModelAndView toEdit(@PathVariable String recordId) {
        ModelAndView mv = new ModelAndView("/jobRecord/edit");
        Map<String, Object> paras = Maps.newHashMap();
        paras.put("id", recordId);
        try {
            List<JobRecord> jobRecordList = jobRecordService.getList(paras);
            if (null != jobRecordList && jobRecordList.size() > 0) {
                mv.addObject("success", true);
                mv.addObject("data", jobRecordList.get(0));
            }
        } catch (Exception e) {
            log.error("toEdit error", recordId, e);
            mv.addObject("success", false);
        }
        return mv;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(JobRecord jobRecord) {
        ModelAndView mv = new ModelAndView("/jobRecord/list");
        JobRecord jobRecordForUpdate = new JobRecord();
        jobRecordForUpdate.setId(jobRecord.getId());
        jobRecordForUpdate.setRecordContent(jobRecord.getRecordContent());
        jobRecordForUpdate.setRecordName(jobRecord.getRecordName());
        jobRecordForUpdate.setUpdateUserId(1);
        jobRecordForUpdate.setUpdateTime(new Date());
        jobRecordForUpdate.setOwnerName(jobRecord.getOwnerName());
        try {
            jobRecordService.updateJobRecord(jobRecordForUpdate);
            mv.setViewName("forward:/jobRecord/list/9999");
        } catch (Exception e) {
            log.error("edit error", jobRecord, e);
            mv.addObject("success", false);
        }
        return mv;
    }
}
