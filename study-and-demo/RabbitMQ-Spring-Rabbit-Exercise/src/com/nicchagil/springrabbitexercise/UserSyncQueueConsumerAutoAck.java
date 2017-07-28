package com.nicchagil.springrabbitexercise;

import java.util.Random;
import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.nicchagil.springrabbitexercise.entity.User;

@Component
public class UserSyncQueueConsumerAutoAck {
	
	Logger logger = Logger.getLogger(this.getClass().getName());

	@RabbitListener(queues = "userSyncQueue")
	public void consume(User user) {
		logger.info("consume msg : " + user);
		
		if (new Random().nextInt(2) == 0) {
			// 模拟宕机
			// TimeUnit.MINUTES.sleep(5);
			
			// 模拟异常
			throw new RuntimeException();
		}
		
	}

}
