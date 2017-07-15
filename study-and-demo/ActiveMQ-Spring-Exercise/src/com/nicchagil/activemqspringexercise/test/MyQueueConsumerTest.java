package com.nicchagil.activemqspringexercise.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyQueueConsumerTest {
	
	@Test
	public void consumer() {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-activemq.xml"})) {
			try {
				TimeUnit.SECONDS.sleep(100000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
