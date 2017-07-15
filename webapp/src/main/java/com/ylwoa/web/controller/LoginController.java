package com.ylwoa.web.controller;

import com.ylwoa.model.User;
import com.ylwoa.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/toLogin")
    public String goLogin() throws IOException {
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, User user) {
        ModelAndView modelAndView = new ModelAndView("/toLogin");
        try {
            userService.login(request, user);
            modelAndView.addObject("success", true);
            modelAndView.setViewName("redirect:/dailyrecord");
        } catch (Exception e) {
            modelAndView.addObject("success", false);
            modelAndView.addObject("message", e.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        request.getSession().invalidate();
        return new ModelAndView("redirect:/login");
    }
}
