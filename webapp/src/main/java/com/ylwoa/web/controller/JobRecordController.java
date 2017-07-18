package com.ylwoa.web.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "/list")
    public ModelAndView dailyRecordList() {
        ModelAndView mv = new ModelAndView("/jobRecord/list");
        Map<String, Object> paras = Maps.newHashMap();
        List<JobRecord> jobRecordList;
        try {
            paras.put("deleteFlg", ACTIVE_STATE);
            jobRecordList = jobRecordService.getList(paras);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            mv.addObject("success", false);
            mv.addObject("pageData", null);
            return mv;
        }
        mv.addObject("success", true);
        mv.addObject("pageData", jobRecordList);
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
            jobRecordService.insertJobRecord(jobRecord);
            mv.setViewName("forward:/jobRecord/list");
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return mv;
    }


    @RequestMapping(value = "/view/{recordId}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getView(@PathVariable String recordId) {
        JSONObject jsonObject = new JSONObject();

        Map<String, Object> paras = Maps.newHashMap();
        paras.put("id", recordId);

        try {
            List<JobRecord> jobRecordList = jobRecordService.getList(paras);
            if (null != jobRecordList && jobRecordList.size() > 0) {
                jsonObject.put("success", true);
                jsonObject.put("data", jobRecordList.get(0));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            jsonObject.put("success", false);
            jsonObject.put("message", e.getMessage());
        }
        return JSONObject.toJSONString(jsonObject, SerializerFeature.WriteMapNullValue);
    }

    @RequestMapping(value = "/delete/{recordId}")
    public ModelAndView delete(@PathVariable String recordId) {
        ModelAndView mv = new ModelAndView("/jobRecord/list");
        try {
            JobRecord jobRecord = new JobRecord();
            jobRecord.setId(Long.parseLong(recordId));
            jobRecord.setDeleteFlg(INACTIVE_STATE);
            jobRecordService.updateJobRecord(jobRecord);
            mv.setViewName("forward:/jobRecord/list");
        } catch (Exception e) {
            log.error("recordId:" + recordId + " message:" + e.getMessage());
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
            log.error(e.getMessage());
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
        try {
            jobRecordService.updateJobRecord(jobRecordForUpdate);
        } catch (Exception e) {
            log.error(e.getMessage());
            mv.addObject("success", false);
        }
        return mv;
    }
}
