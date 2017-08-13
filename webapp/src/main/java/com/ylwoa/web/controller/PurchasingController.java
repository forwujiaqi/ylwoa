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
@RequestMapping("/purchasing")
@Controller
public class PurchasingController {
    @Autowired
    private IProgressService progressService;

    private static transient Logger log = LoggerFactory.getLogger(PurchasingController.class);

    @RequestMapping(value = "/list/{pageNo}")
    public ModelAndView list(@PathVariable String pageNo, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("/purchasing/list");
        Map<String, Object> paras = Maps.newHashMap();

        List<Excel> list;
        try {
            paras.put("deleteFlg", ACTIVE_STATE);
            paras.put("excelType", ExcelTypeEnum.PURCHASING.ordinal());
            Commons.putPermissionCondition(session, paras,0);
            list = progressService.getList(paras);
        } catch (Exception e) {
            log.error("list error pageNo:"+ pageNo, e);
            mv.addObject("success", false);
            mv.addObject("message", "查询列表失败");
            mv.setViewName("/purchasing/list");
            return mv;
        }
        mv.addObject("success", true);
        mv.addObject("pageData", list);
        if ("0".equals(pageNo)) {
            mv.addObject("pageNo", pageNo);
        } else {
            mv.addObject("pageNo", "9999");
        }
        mv.addObject("permitNo",((User)session.getAttribute(USER_SESSION_MARK)).getPermit().substring(0,1));
        return mv;
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/purchasing/add");
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request, Excel excel) throws Exception {
        ModelAndView mv = new ModelAndView("/purchasing/list");
        try {
            User user = Commons.getUserInfoFromSession(request);
            user.setId(user.getId());
            user.setRealName(user.getRealName());
            excel.setExcelType(ExcelTypeEnum.PURCHASING.ordinal());
            progressService.insertExcel(excel,user);
            mv.setViewName("forward:/purchasing/list/0");
        } catch (Exception e) {
            log.error("add error excel:"+ excel, e);
            mv.addObject("success", false);
            mv.addObject("message", "添加失败");
            mv.setViewName("/purchasing/add");
            return mv;
        }

        return mv;
    }


    @RequestMapping(value = "/toView/{excelId}", method = RequestMethod.GET)
    public ModelAndView toView(@PathVariable String excelId) throws Exception {
        ModelAndView mv = new ModelAndView("/purchasing/view");
        Map<String, Object> paras = Maps.newHashMap();
        paras.put("excelId", excelId);
        try {
            List<Excel> excelList = progressService.getListById(paras);
            if (null != excelList && excelList.size() > 0) {
                mv.addObject("success", true);
                mv.addObject("data", excelList.get(0));
            }
        } catch (Exception e) {
            log.error("toView error excelId:"+ excelId, e);
            throw e;
        }
        return mv;
    }

    @RequestMapping(value = "/delete/{excelId}")
    public ModelAndView delete(HttpServletRequest request,@PathVariable String excelId) throws Exception {
        ModelAndView mv = new ModelAndView("/purchasing/list");
        try {
            User user = Commons.getUserInfoFromSession(request);
            user.setId(user.getId());
            user.setRealName(user.getRealName());

            Excel excel = new Excel();
            excel.setId(Long.parseLong(excelId));
            excel.setDeleteFlg(INACTIVE_STATE);

            progressService.deleteExcel(excel,user);
            mv.setViewName("forward:/purchasing/list/9999");
        } catch (Exception e) {
            log.error("delete error excelId:"+ excelId, e);
            mv.addObject("success", false);
            mv.addObject("message", "删除失败");
            mv.setViewName("/purchasing/list");
            return mv;
        }

        return mv;
    }

    @RequestMapping(value = "/toEdit/{excelId}")
    public ModelAndView toEdit(@PathVariable String excelId) throws Exception {
        ModelAndView mv = new ModelAndView("/purchasing/edit");
        Map<String, Object> paras = Maps.newHashMap();
        paras.put("excelId", excelId);
        try {
            List<Excel> excelList = progressService.getListById(paras);
            if (null != excelList && excelList.size() > 0) {
                mv.addObject("success", true);
                mv.addObject("data", excelList.get(0));
            }
        } catch (Exception e) {
            log.error("toEdit error excelId:"+ excelId, e);
            throw e;
        }
        return mv;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(HttpServletRequest request,Excel excel) throws Exception {
        ModelAndView mv = new ModelAndView("/purchasing/list");
        User user = Commons.getUserInfoFromSession(request);
        user.setId(user.getId());
        user.setRealName(user.getRealName());
        try {
            progressService.updateExcel(excel,user);
            mv.setViewName("forward:/purchasing/list/9999");
        } catch (Exception e) {
            log.error("edit error excel:"+ excel, e);
            mv.addObject("success", false);
            mv.addObject("message", "编辑失败");
            mv.setViewName("/purchasing/edit");
            return mv;
        }
        return mv;
    }
}
