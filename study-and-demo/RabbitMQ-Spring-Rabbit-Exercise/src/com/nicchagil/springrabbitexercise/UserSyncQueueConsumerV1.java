package com.nicchagil.springrabbitexercise;

import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.nicchagil.springrabbitexercise.entity.User;

@Component
public class UserSyncQueueConsumerV1 {
	
	Logger logger = Logger.getLogger(this.getClass().getName());

	@RabbitListener(queues = "userSyncQueue")
	public void consume(User user) {
		logger.info("consume msg : " + user);
	}

}
