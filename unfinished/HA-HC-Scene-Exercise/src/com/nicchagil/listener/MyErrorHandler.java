package com.nicchagil.listener;

import java.util.logging.Logger;

import org.springframework.util.ErrorHandler;

public class MyErrorHandler implements ErrorHandler {
	
	Logger logger = Logger.getLogger(this.getClass().getName());

	@Override
	public void handleError(Throwable throwable) {
		logger.info("throwable : " + throwable);
	}

}
