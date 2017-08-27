package com.nicchagil.hessianexercise.server;

import java.util.Date;

import com.caucho.hessian.server.HessianServlet;

public class UserService extends HessianServlet implements UserAPI {

	@Override
	public User getUser() {
		User user = new User();
		user.setId(123);
		user.setName("NH");
		user.setCreateTime(new Date());
		return user;
	}

}
