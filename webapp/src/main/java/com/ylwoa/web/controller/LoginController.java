package com.ylwoa.web.controller;

import com.ylwoa.user.IUserService;
import com.ylwoa.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.ylwoa.common.Commons.USER_COOKIE_KEY;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    private static transient Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/toLogin")
    public String toLogin() throws IOException {
        log.info("toLogin start");
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, User user) {
        log.info("login start");

        ModelAndView modelAndView = new ModelAndView("/toLogin");
        try {
            userService.login(request, user);
            // TODO cookie的值要参考下
            String cookie = user.getPhone();
            Cookie cookieToAdd = new Cookie(USER_COOKIE_KEY, cookie);
            cookieToAdd.setMaxAge(60 * 60 * 24); //过期时间1天
            response.addCookie(cookieToAdd);
            modelAndView.addObject("success", true);
            modelAndView.setViewName("redirect:/jobRecord/list");
        } catch (Exception e) {
            log.error("login error",user,e);
            modelAndView.addObject("success", false);
            modelAndView.addObject("message", e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        log.info("logout start");
        request.getSession().invalidate();
        Cookie cookieToAdd = new Cookie(USER_COOKIE_KEY, null);
        cookieToAdd.setMaxAge(0);
        response.addCookie(cookieToAdd);
        return new ModelAndView("redirect:/toLogin");
    }
}
