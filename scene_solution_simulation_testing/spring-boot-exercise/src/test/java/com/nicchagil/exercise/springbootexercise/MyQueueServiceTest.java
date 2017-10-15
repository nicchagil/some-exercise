package com.nicchagil.exercise.springbootexercise;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicchagil.exercise.springbootexercise.base.BaseSimpleSpringBootTest;
import com.nicchagil.exercise.springbootexercise.rabbitmq.producer.MyQueueService;

public class MyQueueServiceTest extends BaseSimpleSpringBootTest {
	
	@Autowired
	private MyQueueService myQueueService;
	
	@Test
	public void sendTest() throws IOException {
		myQueueService.convertAndSend("hello");
		
		System.in.read();
	}

}
