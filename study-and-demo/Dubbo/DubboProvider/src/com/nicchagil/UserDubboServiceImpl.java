package com.nicchagil;

import com.alibaba.dubbo.config.annotation.Service;
import com.nicchagil.declare.entity.User;
import com.nicchagil.declare.interfaces.UserDubboService;

@Service
public class UserDubboServiceImpl implements UserDubboService {
	
	@Override
	public User getUsernameById(Integer id) {
		if (id == null) {
			return null;
		}
		
		return new User(id, "User" + id);
	}

}
