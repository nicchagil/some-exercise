package com.nicchagil;

import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HowToUse {
	
	public static Logger LOGGER = Logger.getLogger("HowToUse");

	public static void main(String[] args) {
		ApplicationContext context =
			    new ClassPathXmlApplicationContext("spring.xml");
		
		UserDAO userDAO = context.getBean("userDAO", UserDAO.class);
		LOGGER.info("userDAO : " + userDAO);
		UserService userService = context.getBean("userService", UserService.class);
		LOGGER.info("userService : " + userService);
	}

}
