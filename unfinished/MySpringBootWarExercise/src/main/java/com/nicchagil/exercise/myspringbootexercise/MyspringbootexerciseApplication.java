package com.nicchagil.exercise.myspringbootexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

import com.nicchagil.exercise.myspringbootexercise.entity.MySQLConfig;

@SpringBootApplication
@EnableConfigurationProperties({MySQLConfig.class})
public class MyspringbootexerciseApplication {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(MyspringbootexerciseApplication.class, args);
	}
}