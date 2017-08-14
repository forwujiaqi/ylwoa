package com.ylwoa.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import com.ylwoa.common.Commons;
import com.ylwoa.model.User;
import com.ylwoa.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import static com.ylwoa.common.Commons.USER_SESSION_MARK;

/**
 * Created by wubiqing on 2017/8/12.
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    private static transient Logger log = LoggerFactory.getLogger(UserController.class);


    @Deprecated
    @ResponseBody
    @RequestMapping(value = "/getAllActiveUser", produces = "application/json;charset=UTF-8")
    public String getAllActiveUser() throws IOException {

        JSONArray json = new JSONArray();
        for (User user : userService.getUserList()) {
            JSONObject jo = new JSONObject();
            jo.put("realName", user.getRealName());
            jo.put("phone", user.getPhone());
            json.add(jo);
        }
        return json.toString();
    }

    @RequestMapping(value = "/toChangePassword", method = RequestMethod.GET)
    public ModelAndView toChangePassword() {
        ModelAndView mv = new ModelAndView("/user/changePassword");
        return mv;
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ModelAndView changePassword(User user, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/user/changePassword");
        if (null == user) {
            modelAndView.addObject("success", false);
            modelAndView.addObject("message", "密码不能为空");
            log.error("para:null");
            return modelAndView;
        }

        if (Strings.isNullOrEmpty(user.getPassword1()) ||
                Strings.isNullOrEmpty(user.getPassword2()) ||
                Strings.isNullOrEmpty(user.getPassword3())) {
            modelAndView.addObject("success", false);
            modelAndView.addObject("message", "密码不能为空");
            log.error("para:" + user.toString());
            return modelAndView;
        }

        if (!user.getPassword2().equals(user.getPassword3())) {
            modelAndView.addObject("success", false);
            modelAndView.addObject("message", "新密码两次输入不一致");
            log.error("para:" + user.toString());
            return modelAndView;
        }

        if (!CharMatcher.javaLetterOrDigit().matchesAllOf(user.getPassword2())) {
            modelAndView.addObject("success", false);
            modelAndView.addObject("message", "密码只能由英数字组成");
            log.error("para:" + user.toString());
            return modelAndView;
        }

        if (user.getPassword2().length() < 8  || user.getPassword2().length() > 12) {

            modelAndView.addObject("success", false);
            modelAndView.addObject("message", "密码长度需要在8~12位");
            log.error("para:" + user.toString());
            return modelAndView;
        }

        User userFromSession = (User) session.getAttribute(USER_SESSION_MARK);

        User userFromDB = userService.getUserById(userFromSession.getId());
        if (!userFromDB.getPassword().equals(Commons.maskForDB(user.getPassword1()))) {
            modelAndView.addObject("success", false);
            modelAndView.addObject("message", "旧密码不正确");
            log.error("para:" + user.toString());
            return modelAndView;
        }

        User userForUpdate = new User();
        userForUpdate.setPassword(Commons.maskForDB(user.getPassword2()));
        userForUpdate.setUpdateTime(new Date());
        userForUpdate.setUpdateUserId(userFromDB.getId());
        userForUpdate.setId(userFromDB.getId());
        int count = userService.updateByPrimaryKey(userForUpdate);
        if (count == 0) {
            modelAndView.addObject("success", false);
            modelAndView.addObject("message", "更新失败");
            log.error("userForUpdate para:" + userForUpdate.toString());
            return modelAndView;
        }

        modelAndView.addObject("success", true);
        modelAndView.addObject("message", "密码修改成功，请重新登录");
        modelAndView.setViewName("/login");
        return modelAndView;
    }
}
