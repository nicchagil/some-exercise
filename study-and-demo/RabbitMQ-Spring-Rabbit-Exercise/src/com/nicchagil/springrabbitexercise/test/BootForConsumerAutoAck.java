package com.nicchagil.springrabbitexercise.test;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootForConsumerAutoAck {
	
	private static Logger logger = Logger.getLogger(BootForConsumerAutoAck.class.getName());

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-rabbit-consumer-auto-ack.xml"});
		context.start();
		logger.info("startted.");
		
		System.in.read();
	}

}
