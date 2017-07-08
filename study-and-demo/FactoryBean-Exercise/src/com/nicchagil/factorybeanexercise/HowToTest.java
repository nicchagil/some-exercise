package com.nicchagil.factorybeanexercise;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HowToTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
		
		User user = ac.getBean("user", User.class);
		System.out.println(user);
	}

}
