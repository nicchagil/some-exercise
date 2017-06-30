
package com.nicchagil.mybatis3spring3intg.service.trycatchwithtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nicchagil.mybatis3spring3intg.bean.User;
import com.nicchagil.mybatis3spring3intg.mapper.UserMapper;

@Service
public class UserServiceA {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private UserServiceB userServiceB;
	
	@Transactional
	public void updateUser(User user1, User user2) {
		this.mapper.updateByPrimaryKeySelective(user1);
		System.out.println("UserServiceA OK.");
		
		try {
			userServiceB.updateUser(user2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
