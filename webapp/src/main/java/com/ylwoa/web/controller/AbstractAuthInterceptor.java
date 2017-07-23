package com.ylwoa.web.controller;

import com.ylwoa.common.Commons;
import com.ylwoa.model.User;
import com.ylwoa.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.ylwoa.common.Commons.USER_COOKIE_KEY;
import static com.ylwoa.common.Commons.USER_SESSION_MARK;

public abstract class AbstractAuthInterceptor implements HandlerInterceptor {

    private static transient Logger log = LoggerFactory.getLogger(AbstractAuthInterceptor.class);

    @Resource
    private IUserService userService;

    protected boolean doPreHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        Cookie[] cookies = request.getCookies();
        String userCookieValue = getCookieValue(cookies, USER_COOKIE_KEY);
        if (userCookieValue==null ) {
            redirectToLoginPage(response);
            return false;
        }

        int pos = userCookieValue.indexOf('$');
        if (pos < 0) {
            return false;
        }
        String phone = userCookieValue.substring(0, pos);
        String maskedPassword = userCookieValue.substring(pos + 1);

        User user = userService.getUserByCookie(phone);
        if (user == null) {
            redirectToLoginPage(response);
            return false;
        }

        if (!Commons.maskForCookie(user.getPassword()).equals(maskedPassword)) {
            redirectToLoginPage(response);
            return false;
        }

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpSession session = servletRequest.getSession();
        session.setAttribute(USER_SESSION_MARK, user);
        return true;
    }

    private void redirectToLoginPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/toLogin");
    }

    private static String getCookieValue(Cookie[] cookies, String name) {
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
