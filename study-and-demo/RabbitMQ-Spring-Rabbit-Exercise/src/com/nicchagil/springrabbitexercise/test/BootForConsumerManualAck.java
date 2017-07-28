package com.nicchagil.springrabbitexercise.test;

import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BootForConsumerManualAck {
	
	private static Logger logger = Logger.getLogger(BootForConsumerManualAck.class.getName());

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-rabbit-consumer-manual-ack.xml"});
		context.start();
		logger.info("startted.");
		
		System.in.read();
	}

}
