package com.ylwoa.user.impl;

import com.google.common.base.Preconditions;
import com.ylwoa.model.User;
import com.ylwoa.persistence.dao.UserDao;
import com.ylwoa.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wubiqing on 2017/7/13.
 */
@Service
public class UserService implements IUserService {

    public static final String USER_SESSION_MARK = "USER_SESSION_MARK";

    @Autowired
    private UserDao userDao;

    @Override
    public User login(HttpServletRequest request, User user) throws Exception {

        Preconditions.checkNotNull(user,"user is null");
        Preconditions.checkArgument(user.getPhone() != null,"phone is empty");
        Preconditions.checkArgument(user.getPassword() != null,"password is empty");

        Map<String, Object> paras = new HashMap<>();
        paras.put("phone", user.getPhone());
        List<User> userList = userDao.select(paras);
        if (null != userList && userList.size() > 0) {
            User loginUser = userList.get(0);
            if (loginUser.getPassword().endsWith(user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute(USER_SESSION_MARK, userList.get(0));
                return loginUser;
            }
            throw new Exception("用户名密码不正确");
        }

        throw new Exception("该用户不存在");
    }
}
