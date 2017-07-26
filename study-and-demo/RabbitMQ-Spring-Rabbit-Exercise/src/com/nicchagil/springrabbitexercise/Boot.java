package com.nicchagil.springrabbitexercise;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Boot {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-rabbit.xml"});
		context.start();
		System.out.println("startted.");
		
		System.in.read();
	}

}
