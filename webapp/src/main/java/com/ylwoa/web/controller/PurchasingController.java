package com.ylwoa.web.controller;

import com.google.common.collect.Maps;
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

import java.util.List;
import java.util.Map;

import static com.ylwoa.common.Commons.ACTIVE_STATE;
import static com.ylwoa.common.Commons.INACTIVE_STATE;

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
    public ModelAndView list(@PathVariable String pageNo) throws Exception {
        ModelAndView mv = new ModelAndView("/purchasing/list");
        Map<String, Object> paras = Maps.newHashMap();

        List<Excel> list;
        try {
            paras.put("deleteFlg", ACTIVE_STATE);
            paras.put("excelType", ExcelTypeEnum.PURCHASING.ordinal());
            list = progressService.getList(paras);
        } catch (Exception e) {
            log.error("list error", pageNo, e);
            throw e;
        }
        mv.addObject("success", true);
        mv.addObject("pageData", list);
        if ("0".equals(pageNo)) {
            mv.addObject("pageNo", pageNo);
        } else {
            mv.addObject("pageNo", "9999");
        }

        return mv;
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/purchasing/add");
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(Excel excel) throws Exception {
        ModelAndView mv = new ModelAndView("/purchasing/list");
        try {
            User user = new User();
            user.setId(1);
            user.setRealName("吴");
            excel.setExcelType(ExcelTypeEnum.PURCHASING.ordinal());
            progressService.insertExcel(excel,user);
            mv.setViewName("forward:/purchasing/list/0");
        } catch (Exception e) {
            log.error("add error", excel, e);
            throw e;
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
            log.error("toView error", excelId, e);
            throw e;
        }
        return mv;
    }

    @RequestMapping(value = "/delete/{excelId}")
    public ModelAndView delete(@PathVariable String excelId) throws Exception {
        ModelAndView mv = new ModelAndView("/purchasing/list");
        try {
            User user = new User();
            user.setId(1);
            user.setRealName("吴");

            Excel excel = new Excel();
            excel.setId(Long.parseLong(excelId));
            excel.setDeleteFlg(INACTIVE_STATE);

            progressService.deleteExcel(excel,user);
            mv.setViewName("forward:/purchasing/list/9999");
        } catch (Exception e) {
            log.error("delete error", excelId, e);
            throw e;
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
            log.error("toEdit error", excelId, e);
            throw e;
        }
        return mv;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(Excel excel) throws Exception {
        ModelAndView mv = new ModelAndView("/purchasing/list");
        User user = new User();
        user.setId(1);
        user.setRealName("吴");
        try {
            progressService.updateExcel(excel,user);
            mv.setViewName("forward:/purchasing/list/9999");
        } catch (Exception e) {
            log.error("edit error", excel, e);
            throw e;
        }
        return mv;
    }
}
