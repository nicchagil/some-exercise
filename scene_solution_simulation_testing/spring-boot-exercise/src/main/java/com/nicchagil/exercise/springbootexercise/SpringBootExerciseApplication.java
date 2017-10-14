package com.nicchagil.exercise.springbootexercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootExerciseApplication {
	
	private static Logger logger = LoggerFactory.getLogger(SpringBootExerciseApplication.class);

	/**
	 * 启动Spring Boot
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringBootExerciseApplication.class, args);
		logger.info("Spring Boot 已启动：{}", applicationContext);
		
		logger.debug("只是测试DEBUG级别而已");
	}
}
