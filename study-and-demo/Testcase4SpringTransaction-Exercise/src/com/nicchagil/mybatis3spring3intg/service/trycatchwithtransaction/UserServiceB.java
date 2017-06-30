
package com.nicchagil.mybatis3spring3intg.service.trycatchwithtransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nicchagil.mybatis3spring3intg.bean.User;
import com.nicchagil.mybatis3spring3intg.mapper.UserMapper;

@Service
public class UserServiceB {
	
	@Autowired
	private UserMapper mapper;
	
	@Transactional
	public void updateUser(User user) {
		this.mapper.updateByPrimaryKeySelective(user);
		
		/* 模拟异常 */
		String s = null;
		if (1 == 1) {
			s.charAt(1);
		}
		
		System.out.println("UserServiceB OK.");
	}
	
}
