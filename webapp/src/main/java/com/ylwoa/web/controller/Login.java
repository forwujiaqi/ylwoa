package com.ylwoa.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class Login {
    @RequestMapping(value = "/login")
    public String goLogin() throws IOException
    {
        return "/login";
    }
}
