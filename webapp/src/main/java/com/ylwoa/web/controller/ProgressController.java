package com.ylwoa.web.controller;

import com.google.common.collect.Maps;
import com.ylwoa.common.Commons;
import com.ylwoa.common.ExcelTypeEnum;
import com.ylwoa.model.Excel;
import com.ylwoa.model.User;
import com.ylwoa.progress.IProgressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static com.ylwoa.common.Commons.ACTIVE_STATE;
import static com.ylwoa.common.Commons.INACTIVE_STATE;
import static com.ylwoa.common.Commons.USER_SESSION_MARK;

/**
 * Created by wubiqing on 2017/7/20.
 */
@RequestMapping("/progress")
@Controller
public class ProgressController {
    @Autowired
    private IProgressService progressService;

    private static transient Logger log = LoggerFactory.getLogger(ProgressController.class);

    @RequestMapping(value = "/list/{pageNo}")
    public ModelAndView progressList(@PathVariable String pageNo, HttpSession session) {
        ModelAndView mv = new ModelAndView("/progress/list");
        Map<String, Object> paras = Maps.newHashMap();

        List<Excel> progressList;
        try {
            paras.put("deleteFlg", ACTIVE_STATE);
            paras.put("excelType", ExcelTypeEnum.PROGRESS.ordinal());
            Commons.putPermissionCondition(session, paras,2);
            progressList = progressService.getList(paras);
        } catch (Exception e) {
            log.error("progressList error pageNo:"+ pageNo, e);
            mv.addObject("success", false);
            mv.addObject("message", "查询列表失败");
            mv.setViewName("/progress/list");
            return mv;
        }
        mv.addObject("success", true);
        mv.addObject("pageData", progressList);
        if ("0".equals(pageNo)) {
            mv.addObject("pageNo", pageNo);
        } else {
            mv.addObject("pageNo", "9999");
        }
        mv.addObject("permitNo",((User)session.getAttribute(USER_SESSION_MARK)).getPermit().substring(2,3));
        return mv;
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/progress/add");
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request, Excel progress) {
        ModelAndView mv = new ModelAndView("/progress/list");
        try {
            User user = Commons.getUserInfoFromSession(request);
            user.setId(user.getId());
            user.setRealName(user.getRealName());
            progress.setExcelType(ExcelTypeEnum.PROGRESS.ordinal());
            progressService.insertExcel(progress, user);
            mv.setViewName("forward:/progress/list/0");
        } catch (Exception e) {
            log.error("add error progress:"+ progress, e);
            mv.addObject("success", false);
            mv.addObject("message", "添加失败");
            mv.setViewName("/progress/add");
        }

        return mv;
    }


    @RequestMapping(value = "/toView/{excelId}", method = RequestMethod.GET)
    public ModelAndView toView(@PathVariable String excelId) throws Exception {
        ModelAndView mv = new ModelAndView("/progress/view");
        Map<String, Object> paras = Maps.newHashMap();
        paras.put("excelId", excelId);
        try {
            List<Excel> progressList = progressService.getListById(paras);
            if (null != progressList && progressList.size() > 0) {
                mv.addObject("success", true);
                mv.addObject("data", progressList.get(0));
            }
        } catch (Exception e) {
            log.error("toView error excelId:"+ excelId, e);
            throw e;
        }
        return mv;
    }

    @RequestMapping(value = "/delete/{excelId}")
    public ModelAndView delete(HttpServletRequest request, @PathVariable String excelId) {
        ModelAndView mv = new ModelAndView("/progress/list");
        try {
            User user = Commons.getUserInfoFromSession(request);
            user.setId(user.getId());
            user.setRealName(user.getRealName());

            Excel progress = new Excel();
            progress.setId(Long.parseLong(excelId));
            progress.setDeleteFlg(INACTIVE_STATE);

            progressService.deleteExcel(progress, user);
            mv.setViewName("forward:/progress/list/9999");
        } catch (Exception e) {
            log.error("delete error excelId:"+ excelId, e);
            mv.addObject("success", false);
            mv.addObject("message", "删除失败");
            mv.setViewName("/progress/list");
        }

        return mv;
    }

    @RequestMapping(value = "/toEdit/{excelId}")
    public ModelAndView toEdit(@PathVariable String excelId) throws Exception {
        ModelAndView mv = new ModelAndView("/progress/edit");
        Map<String, Object> paras = Maps.newHashMap();
        paras.put("excelId", excelId);
        try {
            List<Excel> progressList = progressService.getListById(paras);
            if (null != progressList && progressList.size() > 0) {
                mv.addObject("success", true);
                mv.addObject("data", progressList.get(0));
            }
        } catch (Exception e) {
            log.error("toEdit error excelId:"+ excelId, e);
            throw e;
        }
        return mv;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(HttpServletRequest request, Excel progress) {
        ModelAndView mv = new ModelAndView("/progress/list");
        User user = Commons.getUserInfoFromSession(request);
        user.setId(user.getId());
        user.setRealName(user.getRealName());
        try {
            progressService.updateExcel(progress, user);
            mv.setViewName("forward:/progress/list/9999");
        } catch (Exception e) {
            log.error("edit error progress:"+ progress, e);
            mv.addObject("success", false);
            mv.addObject("message", "编辑失败");
            mv.setViewName("/progress/edit");
        }
        return mv;
    }
}
