package com.nicchagil.exercise.tranditionalweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicchagil.exercise.tranditionalweb.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("update")
    public String update() {
        this.userService.update();
        return "hello"; // 返回hello.jsp页面
    }
    
    @RequestMapping("isUser")
    @ResponseBody
    public String isUser() {
        this.userService.update();
        return "yes"; // 返回字符串
    }

}
