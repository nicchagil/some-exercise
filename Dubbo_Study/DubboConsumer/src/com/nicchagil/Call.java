package com.nicchagil;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicchagil.declare.entity.User;

public class Call {
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-dubbo.xml"});
		context.start();

		UserService userService = (UserService)context.getBean("userService");
		User user = userService.getUsernameById(1);

		System.out.println(user);
	}

}
