package com.nicchagil.test;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.nicchagil.model.Coupon;

public class Producer {

	public static void main(String[] args) {
		ApplicationContext context = new GenericXmlApplicationContext("classpath:/spring-rabbit-producer.xml");
		AmqpTemplate template = context.getBean("amqpTemplate", AmqpTemplate.class);

		template.convertAndSend("couponQueue", new Coupon());
	}

}
