package com.ylwoa.user;

import com.ylwoa.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wubiqing on 2017/7/13.
 */
public interface IUserService {

    User login(HttpServletRequest request, User user) throws Exception;

    User getUserByCookie(String cookieValue);
}
