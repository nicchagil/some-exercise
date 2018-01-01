package com.nicchagil.controller;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    
    
    @RequestMapping("/query")
    public String query() {
        System.out.println("query trgger.");
        return "user/userList";
    }
    
    @RequestMapping("/queryForApp")
    @ResponseBody
    public Object queryForApp() {
        System.out.println("queryForApp trgger.");
        
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("userName", "Nick Huang");
        
        return map;
    }

}