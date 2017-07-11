package com.nicchagil.activemqspringexercise.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicchagil.activemqspringexercise.MyQueueProducer;
import com.nicchagil.activemqspringexercise.User;

public class MyQueueProducerTest {
	
	@Test
	public void send() throws JsonProcessingException {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-activemq.xml"})) {
			MyQueueProducer myQueueProducer = context.getBean("myQueueProducer", MyQueueProducer.class);
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			for (int i = 1; i < 1000; i++) {
				myQueueProducer.sendMessage(objectMapper.writeValueAsString(new User(i, "Nick Huang")));
			}
			
		}
	}

}
