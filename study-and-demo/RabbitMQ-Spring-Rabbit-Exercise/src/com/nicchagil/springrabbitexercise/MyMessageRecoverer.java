package com.nicchagil.springrabbitexercise;

import java.util.logging.Logger;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;

public class MyMessageRecoverer implements MessageRecoverer {
	
	Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void recover(Message message, Throwable cause) {
		logger.info("message : " + message);
		logger.info("cause : " + cause);
	}

}
