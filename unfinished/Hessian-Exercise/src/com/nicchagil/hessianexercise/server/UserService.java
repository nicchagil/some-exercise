package com.nicchagil.hessianexercise.server;

import java.util.Date;

public class UserService implements UserAPI {

	@Override
	public User getUser() {
		User user = new User();
		user.setId(123);
		user.setName("NH");
		user.setCreateTime(new Date());
		return user;
	}

}
