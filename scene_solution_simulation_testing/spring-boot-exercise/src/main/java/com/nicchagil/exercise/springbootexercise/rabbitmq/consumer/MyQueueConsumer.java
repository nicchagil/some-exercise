package com.nicchagil.exercise.springbootexercise.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyQueueConsumer {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RabbitListener(queues = "my-queue")
	public void processMessage(String content) {
		this.logger.info("消费消息：{}", content);
	}

}
