package com.nicchagil.exercise.myspringbootexercise.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.exercise.myspringbootexercise.entity.MySQLConfig;
import com.nicchagil.exercise.myspringbootexercise.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MySQLConfig mySQLConfig;
	
	/**
	 * 添加或修改
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Object add(User user) {
		Assert.notNull(user, "参数异常");
		
		return "添加 -> " + user;
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Object delete(@PathVariable Integer id) {
		Assert.notNull(id, "ID为空");
		
		if (id.equals(123)) {
			return "删除用户 -> " + id;
		}
        return "无此用户";
    }
	
	/**
	 * 添加或修改
	 */
	@RequestMapping(method=RequestMethod.PUT)
	public Object update(User user) {
		Assert.notNull(user, "参数异常");
		Assert.notNull(user.getId(), "ID为空");
		
		return "更新 -> " + user;
	}
	
	/**
	 * 获取用户
	 * http://127.0.0.1:8080/user/123，结果：{"id":123,"name":"NH","createTime":1503892298029}
	 */
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Object get(@PathVariable Integer id) {
		Assert.notNull(id, "ID为空");
		
		this.logger.info("mySQLConfig -> {}", this.mySQLConfig);
		
		if (id.equals(123)) {
			return new User(123, "NH", new Date());
		}
        return "无此用户";
    }
	
	/**
	 * 获取用户列表
	 * http://127.0.0.1:8080/user?name=xxx&page=1&pageSize=20
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
    public Object list(String name, int page, int pageSize) {
        return "列表参数 -> page : " + page + ", pageSize : " + pageSize + ", name : " + name;
    }
	
}
