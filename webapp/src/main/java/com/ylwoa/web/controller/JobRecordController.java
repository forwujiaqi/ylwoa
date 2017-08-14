package com.ylwoa.web.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.ylwoa.common.Commons;
import com.ylwoa.jobrecord.IJobRecordService;
import com.ylwoa.model.JobRecord;
import com.ylwoa.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.ylwoa.common.Commons.*;

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
    public ModelAndView dailyRecordList(@PathVariable String pageNo, HttpSession session) {
        ModelAndView mv = new ModelAndView("/jobRecord/list");
        if (Strings.isNullOrEmpty(pageNo)) {
            mv.addObject("success", false);
            mv.addObject("message", "pageNo null");
            log.error("pageNo:null");
            return mv;
        }

        Map<String, Object> paras = Maps.newHashMap();
        List<JobRecord> jobRecordList;
        try {
            paras.put("deleteFlg", ACTIVE_STATE);
            Commons.putPermissionCondition(session, paras,3);
            jobRecordList = jobRecordService.getList(paras);
        } catch (Exception e) {
            log.error("dailyRecordList error paras:"+paras, e);
            mv.addObject("success", false);
            mv.addObject("pageData", null);
            mv.addObject("message", "查询列表失败");
            mv.setViewName("/jobRecord/list");
            return mv;
        }
        mv.addObject("success", true);
        mv.addObject("pageData", jobRecordList);
        if ("0".equals(pageNo)) {
            mv.addObject("pageNo", pageNo);
        } else {
            mv.addObject("pageNo", "9999");
        }
        mv.addObject("permitNo",((User)session.getAttribute(USER_SESSION_MARK)).getPermit().substring(3,4));
        return mv;
    }



    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/jobRecord/add");
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(JobRecord jobRecord,HttpSession session) {
        ModelAndView mv = new ModelAndView("/jobRecord/list");
        try {
            Date now = new Date();
            jobRecord.setRecordType(1);
            jobRecord.setUpdateUserId(((User)session.getAttribute(USER_SESSION_MARK)).getId());
            jobRecord.setUpdateTime(now);
            jobRecord.setCreateUserId(((User)session.getAttribute(USER_SESSION_MARK)).getId());
            jobRecord.setCreateTime(now);
            jobRecord.setDeleteFlg(ACTIVE_STATE);
            jobRecord.setOwnerName(jobRecord.getOwnerName().replaceAll("，",","));
            jobRecordService.insertJobRecord(jobRecord);
            mv.setViewName("forward:/jobRecord/list/0");
        } catch (Exception e) {
            log.error("add error jobRecord：" + jobRecord, e);
            mv.addObject("success", false);
            mv.addObject("message", "添加失败");
            mv.setViewName("/jobRecord/add");
            return mv;
        }

        return mv;
    }


    @RequestMapping(value = "/toView/{recordId}", method = RequestMethod.GET)
    public ModelAndView toView(@PathVariable String recordId) throws Exception {
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
            log.error("toView error recordId：" + recordId, e);
            mv.addObject("success", false);
            throw e;
        }
        return mv;
    }

    @RequestMapping(value = "/delete/{recordId}")
    public ModelAndView delete(@PathVariable String recordId,HttpSession session) {
        ModelAndView mv = new ModelAndView("/jobRecord/list");
        try {
            JobRecord jobRecord = new JobRecord();
            jobRecord.setId(Long.parseLong(recordId));
            jobRecord.setDeleteFlg(INACTIVE_STATE);
            jobRecord.setUpdateUserId(((User)session.getAttribute(USER_SESSION_MARK)).getId());
            jobRecord.setUpdateTime(new Date());
            jobRecordService.updateJobRecord(jobRecord);
            mv.setViewName("forward:/jobRecord/list/9999");
        } catch (Exception e) {
            log.error("delete error recordId："+ recordId, e);
            mv.setViewName("/jobRecord/list");
            mv.addObject("success", false);
            mv.addObject("message", "删除失败");
            return mv;
        }

        return mv;
    }

    @RequestMapping(value = "/toEdit/{recordId}")
    public ModelAndView toEdit(@PathVariable String recordId) throws Exception {
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
            log.error("toEdit error recordId："+ recordId, e);
            mv.addObject("success", false);
            throw e;
        }
        return mv;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(JobRecord jobRecord,HttpSession session) {
        ModelAndView mv = new ModelAndView("/jobRecord/list");
        JobRecord jobRecordForUpdate = new JobRecord();
        jobRecordForUpdate.setId(jobRecord.getId());
        jobRecordForUpdate.setRecordContent(jobRecord.getRecordContent());
        jobRecordForUpdate.setRecordName(jobRecord.getRecordName());
        jobRecordForUpdate.setUpdateUserId(((User)session.getAttribute(USER_SESSION_MARK)).getId());
        jobRecordForUpdate.setUpdateTime(new Date());
        jobRecordForUpdate.setOwnerName(jobRecord.getOwnerName().replaceAll("，",","));
        try {
            jobRecordService.updateJobRecord(jobRecordForUpdate);
            mv.setViewName("forward:/jobRecord/list/9999");
        } catch (Exception e) {
            log.error("edit error jobRecord：" + jobRecord, e);
            mv.addObject("success", false);
            mv.addObject("message", "编辑失败");
            mv.setViewName("/jobRecord/edit");
        }
        return mv;
    }
}
