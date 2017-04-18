package com.nicchagil;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nicchagil.declare.entity.User;
import com.nicchagil.declare.interfaces.UserDubboService;

@Service
public class UserService {
	
	@Reference
	private UserDubboService userDubboService;
	
	public User getUsernameById(Integer id) {
		return this.userDubboService.getUsernameById(id);
	}

}
