package com.nicchagil.exercise.springbootexercise.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyQueueService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** 路由键 */
	private static String ROUTING_KEY = "my-queue";
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	/**
	 * 发送消息
	 * @param message 消息
	 */
	public void convertAndSend(Object message) {
		this.logger.info("生产消息：{}, {}", ROUTING_KEY, message);
		this.amqpTemplate.convertAndSend(ROUTING_KEY, message);
	}

}
