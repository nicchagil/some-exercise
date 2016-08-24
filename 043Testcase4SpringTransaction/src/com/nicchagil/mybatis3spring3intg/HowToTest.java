package com.nicchagil.mybatis3spring3intg;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicchagil.mybatis3spring3intg.service.UserService;

public class HowToTest {

	public static void main(String[] args) {
		ApplicationContext context =
                new ClassPathXmlApplicationContext("/config/spring_mybatis.xml");
		UserService userService = context.getBean("userService", UserService.class);
		
		// 测试一
		userService.test1();
		
		// 测试二
		// userService.test2Wrap();
		
		// 测试三
		// userService.test3Wrap();
	}

}
