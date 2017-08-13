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
@RequestMapping("/arrive")
@Controller
public class ArriveController {
    @Autowired
    private IProgressService progressService;

    private static transient Logger log = LoggerFactory.getLogger(ArriveController.class);

    @RequestMapping(value = "/list/{pageNo}")
    public ModelAndView list(@PathVariable String pageNo, HttpSession session) throws Exception {
        ModelAndView mv = new ModelAndView("/arrive/list");
        Map<String, Object> paras = Maps.newHashMap();

        List<Excel> list;
        try {
            paras.put("deleteFlg", ACTIVE_STATE);
            Commons.putPermissionCondition(session, paras,1);
            paras.put("excelType", ExcelTypeEnum.ARRIVE.ordinal());
            list = progressService.getList(paras);
        } catch (Exception e) {
            log.error("list error pageNo："+ pageNo, e);
            mv.addObject("success", false);
            mv.addObject("message", "查询列表失败");
            return mv;
        }
        mv.addObject("success", true);
        mv.addObject("pageData", list);
        if ("0".equals(pageNo)) {
            mv.addObject("pageNo", pageNo);
        } else {
            mv.addObject("pageNo", "9999");
        }
        mv.addObject("permitNo",((User)session.getAttribute(USER_SESSION_MARK)).getPermit().substring(1,2));
        return mv;
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/arrive/add");
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(HttpServletRequest request, Excel excel) throws Exception {
        ModelAndView mv = new ModelAndView("/arrive/list");
        try {
            User user = Commons.getUserInfoFromSession(request);
            user.setId(user.getId());
            user.setRealName(user.getRealName());
            excel.setExcelType(ExcelTypeEnum.ARRIVE.ordinal());
            progressService.insertExcel(excel,user);
            mv.setViewName("forward:/arrive/list/0");
        } catch (Exception e) {
            log.error("add error excel:"+ excel, e);
            mv.addObject("success", false);
            mv.addObject("message", "添加失败");
            mv.setViewName("/arrive/add");
            return mv;
        }

        return mv;
    }


    @RequestMapping(value = "/toView/{excelId}", method = RequestMethod.GET)
    public ModelAndView toView(@PathVariable String excelId) throws Exception {
        ModelAndView mv = new ModelAndView("/arrive/view");
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
        ModelAndView mv = new ModelAndView("/arrive/list");
        try {
            User user = Commons.getUserInfoFromSession(request);
            user.setId(user.getId());
            user.setRealName(user.getRealName());

            Excel excel = new Excel();
            excel.setId(Long.parseLong(excelId));
            excel.setDeleteFlg(INACTIVE_STATE);

            progressService.deleteExcel(excel,user);
            mv.setViewName("forward:/arrive/list/9999");
        } catch (Exception e) {
            log.error("delete error excelId:"+ excelId, e);
            mv.addObject("success", false);
            mv.addObject("message", "删除失败");
            mv.setViewName("/arrive/list");
            return mv;
        }

        return mv;
    }

    @RequestMapping(value = "/toEdit/{excelId}")
    public ModelAndView toEdit(@PathVariable String excelId) throws Exception {
        ModelAndView mv = new ModelAndView("/arrive/edit");
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
        ModelAndView mv = new ModelAndView("/arrive/list");
        User user = Commons.getUserInfoFromSession(request);
        user.setId(user.getId());
        user.setRealName(user.getRealName());
        try {
            progressService.updateExcel(excel,user);
            mv.setViewName("forward:/arrive/list/9999");
        } catch (Exception e) {
            log.error("edit error", excel, e);
            mv.addObject("success", false);
            mv.addObject("message", "编辑失败");
            mv.setViewName("/arrive/edit");
        }
        return mv;
    }
}
