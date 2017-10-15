package com.nicchagil.exercise.springbootexercise.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	/**
	 * 发送消息
	 */
	public void send(String routingKey, String message) {
		// 发送消息
		this.rabbitTemplate.convertAndSend(routingKey, message);
	}

}
