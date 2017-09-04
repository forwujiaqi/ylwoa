package com.ylwoa.web.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.ylwoa.base.IProjectService;
import com.ylwoa.common.Commons;
import com.ylwoa.model.Project;
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
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    private static transient Logger log = LoggerFactory.getLogger(ProjectController.class);

    @RequestMapping(value = "/list/{pageNo}")
    public ModelAndView projectList(@PathVariable String pageNo, HttpSession session) {
        ModelAndView mv = new ModelAndView("/project/list");
        if (Strings.isNullOrEmpty(pageNo)) {
            mv.addObject("success", false);
            mv.addObject("message", "pageNo null");
            log.error("pageNo:null");
            return mv;
        }

        Map<String, Object> paras = Maps.newHashMap();
        List<Project> projectList;
        try {
            paras.put("deleteFlg", ACTIVE_STATE);
//            Commons.putPermissionCondition(session, paras,3);
            projectList = projectService.getList(paras);
        } catch (Exception e) {
            log.error("projectList error paras:"+paras, e);
            mv.addObject("success", false);
            mv.addObject("pageData", null);
            mv.addObject("message", "查询列表失败");
            mv.setViewName("/project/list");
            return mv;
        }
        mv.addObject("success", true);
        mv.addObject("pageData", projectList);
        if ("0".equals(pageNo)) {
            mv.addObject("pageNo", pageNo);
        } else {
            mv.addObject("pageNo", "9999");
        }
//        mv.addObject("permitNo",((User)session.getAttribute(USER_SESSION_MARK)).getPermit().substring(3,4));
        return mv;
    }



    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/project/add");
        return mv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView add(Project project, HttpSession session) {
        ModelAndView mv = new ModelAndView("/project/list");
        try {
            Date now = new Date();
            project.setUpdateUserId(((User)session.getAttribute(USER_SESSION_MARK)).getId());
            project.setUpdateTime(now);
            project.setUpdateUserName(((User)session.getAttribute(USER_SESSION_MARK)).getRealName());
            project.setCreateUserId(((User)session.getAttribute(USER_SESSION_MARK)).getId());
            project.setCreateTime(now);
            project.setCreateUserName(((User)session.getAttribute(USER_SESSION_MARK)).getRealName());
            project.setDeleteFlg(ACTIVE_STATE);
//            project.setOwnerName(project.getOwnerName().replaceAll("，",","));
            projectService.insertProject(project);
            mv.setViewName("forward:/project/list/0");
        } catch (Exception e) {
            log.error("add error project：" + project, e);
            mv.addObject("success", false);
            mv.addObject("message", "添加失败");
            mv.setViewName("/project/add");
            return mv;
        }

        return mv;
    }


    @RequestMapping(value = "/toView/{projectId}", method = RequestMethod.GET)
    public ModelAndView toView(@PathVariable String projectId) throws Exception {
        ModelAndView mv = new ModelAndView("/project/view");
        Map<String, Object> paras = Maps.newHashMap();
        paras.put("id", projectId);
        try {
            List<Project> projectList = projectService.getList(paras);
            if (null != projectList && projectList.size() > 0) {
                mv.addObject("success", true);
                mv.addObject("data", projectList.get(0));
            }
        } catch (Exception e) {
            log.error("toView error projectId：" + projectId, e);
            mv.addObject("success", false);
            throw e;
        }
        return mv;
    }

    @RequestMapping(value = "/delete/{projectId}")
    public ModelAndView delete(@PathVariable String projectId,HttpSession session) {
        ModelAndView mv = new ModelAndView("/project/list");
        try {
            Project project = new Project();
            project.setId(Long.parseLong(projectId));
            project.setDeleteFlg(INACTIVE_STATE);
            project.setUpdateUserId(((User)session.getAttribute(USER_SESSION_MARK)).getId());
            project.setUpdateTime(new Date());
            project.setUpdateUserName(((User)session.getAttribute(USER_SESSION_MARK)).getRealName());
            projectService.updateProject(project);
            mv.setViewName("forward:/project/list/9999");
        } catch (Exception e) {
            log.error("delete error projectId："+ projectId, e);
            mv.setViewName("/project/list");
            mv.addObject("success", false);
            mv.addObject("message", "删除失败");
            return mv;
        }

        return mv;
    }

    @RequestMapping(value = "/toEdit/{projectId}")
    public ModelAndView toEdit(@PathVariable String projectId) throws Exception {
        ModelAndView mv = new ModelAndView("/project/edit");
        Map<String, Object> paras = Maps.newHashMap();
        paras.put("id", projectId);
        try {
            List<Project> projectList = projectService.getList(paras);
            if (null != projectList && projectList.size() > 0) {
                mv.addObject("success", true);
                mv.addObject("data", projectList.get(0));
            }
        } catch (Exception e) {
            log.error("toEdit error projectId："+ projectId, e);
            mv.addObject("success", false);
            throw e;
        }
        return mv;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(Project project,HttpSession session) {
        ModelAndView mv = new ModelAndView("/project/list");
        Project projectForUpdate = new Project();
        projectForUpdate.setProjectName(project.getProjectName());
        projectForUpdate.setRemark(project.getRemark());
        projectForUpdate.setId(project.getId());
        projectForUpdate.setUpdateUserId(((User)session.getAttribute(USER_SESSION_MARK)).getId());
        projectForUpdate.setUpdateTime(new Date());
//        projectForUpdate.setOwnerName(project.getOwnerName().replaceAll("，",","));
        projectForUpdate.setUpdateUserName(((User)session.getAttribute(USER_SESSION_MARK)).getRealName());
        try {
            projectService.updateProject(projectForUpdate);
            mv.setViewName("forward:/project/list/9999");
        } catch (Exception e) {
            log.error("edit error project：" + project, e);
            mv.addObject("success", false);
            mv.addObject("message", "编辑失败");
            mv.setViewName("/project/edit");
        }
        return mv;
    }
}
