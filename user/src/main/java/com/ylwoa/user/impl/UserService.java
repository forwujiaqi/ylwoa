package com.ylwoa.user.impl;

import com.google.common.base.Preconditions;
import com.ylwoa.common.Commons;
import com.ylwoa.user.IUserService;
import com.ylwoa.model.User;
import com.ylwoa.persistence.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ylwoa.common.Commons.*;

/**
 * Created by wubiqing on 2017/7/13.
 */
@Service
public class UserService implements IUserService {


    @Autowired
    private UserDao userDao;

    @Override
    public User login(HttpServletRequest request, User user) throws Exception {

        Preconditions.checkNotNull(user, "user is null");
        Preconditions.checkArgument(user.getPhone() != null, "phone is empty");
        Preconditions.checkArgument(user.getPassword() != null, "password is empty");

        Map<String, Object> paras = new HashMap<>();
        paras.put("phone", user.getPhone());
        paras.put(DELETE_FLG, ACTIVE_STATE);
        List<User> userList = userDao.select(paras);
        if (null != userList && userList.size() > 0) {
            User userFromDB = userList.get(0);
            if (userFromDB.getPassword().endsWith(Commons.maskForDB(user.getPassword()))) {
                HttpSession session = request.getSession();
                session.setAttribute(USER_SESSION_MARK, userList.get(0));
                return userFromDB;
            }
            throw new Exception("用户名密码不正确");
        }

        throw new Exception("该用户不存在");
    }

    @Override
    public User getUserByCookie(String cookieValue) {
        Map<String, Object> paras = new HashMap<>();
        paras.put("phone", cookieValue);
        paras.put(DELETE_FLG, ACTIVE_STATE);
        List<User> userList = userDao.select(paras);
        User user = null;
        if (null != userList && userList.size() > 0) {
            user = userList.get(0);
        }
        return user;
    }

    @Override
    public List<User> getUserList() {
        Map<String, Object> paras = new HashMap<>();
        paras.put(DELETE_FLG, ACTIVE_STATE);
        List<User> userList = userDao.select(paras);
        return userList;
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKey(User user) {
        return userDao.updateByPrimaryKeySelective(user);
    }
}
