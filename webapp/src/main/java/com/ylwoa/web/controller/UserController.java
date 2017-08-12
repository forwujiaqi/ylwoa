package com.ylwoa.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ylwoa.model.User;
import com.ylwoa.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * Created by wubiqing on 2017/8/12.
 */
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/getAllActiveUser",produces = "application/json;charset=UTF-8")
    public String getAllActiveUser() throws IOException {

        JSONArray json = new JSONArray();
        for(User user : userService.getUserList()){
            JSONObject jo = new JSONObject();
            jo.put("realName", user.getRealName());
            jo.put("phone", user.getPhone());
            json.add(jo);
        }
        return json.toString();
    }
}
