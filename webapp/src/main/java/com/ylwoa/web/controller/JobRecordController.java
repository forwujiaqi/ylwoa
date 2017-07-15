package com.ylwoa.web.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ylwoa.jobrecord.IJobRecordService;
import com.ylwoa.model.JobRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static com.ylwoa.common.Commons.ACTIVE_STATE;

/**
 * Created by wubiqing on 2017/7/14.
 */
@Controller
@RequestMapping("/jobRecord/")
public class JobRecordController {

    @Autowired
    private IJobRecordService jobRecordService;

    private static transient Logger log = LoggerFactory.getLogger(JobRecordController.class);

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView dailyRecordList() {

        log.info("dailyRecordList start");

        ModelAndView mv = new ModelAndView("jobRecord/list");
        Map<String, Object> paras = Maps.newHashMap();
        List<JobRecord> jobRecordList = Lists.newArrayList();
        try {
            paras.put("deleteFlg", ACTIVE_STATE);
            jobRecordList = jobRecordService.getList(paras);
        } catch (Exception e) {
            e.printStackTrace();
            mv.addObject("success", false);
            mv.addObject("data", null);

            return mv;
        }
        mv.addObject("success", true);
        mv.addObject("data", jobRecordList);
        return mv;
    }
}
