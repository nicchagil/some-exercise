package com.nicchagil.springrabbitexercise;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.nicchagil.springrabbitexercise.entity.User;

public class Producer {

	public static void main(String[] args) {
		ApplicationContext context = new GenericXmlApplicationContext("classpath:/spring-rabbit-producer.xml");
		AmqpTemplate template = context.getBean("amqpTemplate", AmqpTemplate.class);

		template.convertAndSend("userSyncQueue", new User(1000000, "Nick Huang", new Date()));
	}

}
