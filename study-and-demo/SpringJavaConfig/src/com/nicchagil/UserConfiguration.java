package com.nicchagil;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
	
	@Bean
	public UserService userService(UserDAO userDAO) {
		return new UserService();
	}
	
	@Bean
	public UserDAO userDAO() {
		return new UserDAO();
	}

}
