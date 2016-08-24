
package com.nicchagil.mybatis3spring3intg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nicchagil.mybatis3spring3intg.bean.User;
import com.nicchagil.mybatis3spring3intg.mapper.UserMapper;

@Service
public class AnotherUserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void test3() {
		User user = new User();
		user.setId(2);
		user.setUsername("Robin Chen 3-2");
		
		this.mapper.updateByPrimaryKeySelective(user);
		System.out.println("OK.");
		
		throw new RuntimeException();
	}
	
}
