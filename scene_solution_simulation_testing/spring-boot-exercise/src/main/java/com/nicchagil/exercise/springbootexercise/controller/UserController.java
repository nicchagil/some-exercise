package com.nicchagil.exercise.springbootexercise.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 包含@Controller和@ResponseBody的方便注解
@RequestMapping("user")
public class UserController {
	
    @RequestMapping("login")
    public String login(String username, String password) {
    	// TODO 校验逻辑
    	
        return "登录成功";
    }

}
