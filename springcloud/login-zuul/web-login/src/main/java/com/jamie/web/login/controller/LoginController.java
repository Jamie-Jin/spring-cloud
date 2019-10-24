package com.jamie.web.login.controller;

import com.jamie.common.web.result.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping("/login-page")
    public String login(){
        return "user/login";
    }

    @RequestMapping("/login-success")
    @ResponseBody
    public JsonResult success(String userName, String password){
        return JsonResult.success("Hello " + userName);
    }


}
