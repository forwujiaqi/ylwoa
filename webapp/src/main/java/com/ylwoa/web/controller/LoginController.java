package com.ylwoa.web.controller;

import com.google.code.kaptcha.Constants;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.ylwoa.common.Commons.USER_COOKIE_KEY;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    private static transient Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/toLogin")
    public String toLogin() throws IOException {
        return "/login";
    }

    @RequestMapping(value = "/toHomepage")
    public String toHomepage() throws IOException {
        return "/homepage";
    }

    @RequestMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, User user,String mcode) {

        if (user == null || Strings.isNullOrEmpty(user.getPhone()) ) {
            return new ModelAndView("redirect:/toLogin");
        }
        ModelAndView modelAndView = new ModelAndView("/login");

        HttpSession session = request.getSession();
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!code.equals(mcode)) {
            log.error("login error. captcha  code:"+code +" mcode:"+mcode +" user:"+ user);
            modelAndView.addObject("success", false);
            modelAndView.addObject("message", "验证码不正确");
            modelAndView.setViewName("/login");
            return modelAndView;
        }

        try {
            user = userService.login(request, user);
            String phoneCookie = user.getPhone();
            String password = user.getPassword();
            String maskedCookie = Commons.maskForCookie(password);

            Cookie cookieToAdd = new Cookie(USER_COOKIE_KEY, phoneCookie + "$" + maskedCookie);
            cookieToAdd.setMaxAge(60 * 60 * 24); //过期时间1天
            response.addCookie(cookieToAdd);
            modelAndView.addObject("success", true);
            modelAndView.setViewName("redirect:/toHomepage");
        } catch (Exception e) {
            log.error("login error", user, e);
            modelAndView.addObject("success", false);
            modelAndView.addObject("message", e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession().invalidate();
        Cookie cookieToAdd = new Cookie(USER_COOKIE_KEY, null);
        cookieToAdd.setMaxAge(0);
        response.addCookie(cookieToAdd);
        return new ModelAndView("redirect:/toLogin");
    }
}
