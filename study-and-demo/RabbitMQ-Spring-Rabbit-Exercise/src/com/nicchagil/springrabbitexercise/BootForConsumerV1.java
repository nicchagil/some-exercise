package com.nicchagil.springrabbitexercise;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootForConsumerV1 {
	
	private static Logger logger = Logger.getLogger(BootForConsumerV1.class.getName());

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-rabbit-consumer-v1.xml"});
		context.start();
		logger.info("startted.");
		
		System.in.read();
	}

}
