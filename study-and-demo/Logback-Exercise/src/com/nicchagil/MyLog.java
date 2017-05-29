package com.nicchagil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyLog {
	
	private static Logger LOGGER = LoggerFactory.getLogger(MyLog.class);
	
	public static void printMsg() {
		LOGGER.info("It is MyLog.");
		LOGGER.debug("It is MyLog.");
	}

}
