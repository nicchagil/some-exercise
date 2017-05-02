package com.nicchagil;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Boot {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-dubbo.xml"});
		context.start();
		System.out.println("startted.");
		
		System.in.read();
	}

}
