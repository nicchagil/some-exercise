package com.nicchagil.designpattern.strategy.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AppleStrategy implements ISomethingStrategy {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void doSomething() {
		this.logger.info("doSomething : {}", "apple");
	}

}
