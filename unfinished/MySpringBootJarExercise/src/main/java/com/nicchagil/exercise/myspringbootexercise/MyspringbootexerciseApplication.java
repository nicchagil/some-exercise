package com.nicchagil.exercise.myspringbootexercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.nicchagil.exercise.myspringbootexercise.service.UserService;

@SpringBootApplication
public class MyspringbootexerciseApplication {
	
	private static Logger logger = LoggerFactory.getLogger(MyspringbootexerciseApplication.class);
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(MyspringbootexerciseApplication.class, args);
		
		UserService userService = applicationContext.getBean(UserService.class); // 从IOC容器中获取Bean
		logger.info("userService -> {}", userService);
	}
}
