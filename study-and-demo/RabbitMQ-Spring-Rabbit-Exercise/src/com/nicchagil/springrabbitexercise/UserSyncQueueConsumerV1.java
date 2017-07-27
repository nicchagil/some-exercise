package com.nicchagil.springrabbitexercise;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.nicchagil.springrabbitexercise.entity.User;

@Component
public class UserSyncQueueConsumerV1 {

	@RabbitListener(queues = "userSyncQueue")
	public void consume(User user) {
		System.out.println("consume msg : " + user);
	}

}
