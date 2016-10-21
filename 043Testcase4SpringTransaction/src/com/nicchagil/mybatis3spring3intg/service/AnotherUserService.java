
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
		user.setId(1);
		user.setUsername("Robin Chen 3-2");
		
		this.mapper.updateByPrimaryKeySelective(user);
		System.out.println("OK.");
		
		throw new RuntimeException();
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void test4RequiresNew() {
		User user = new User();
		user.setId(1);
		user.setUsername("REQUIRES_NEW");
		
		this.mapper.updateByPrimaryKeySelective(user);
		System.out.println("OK.");
	}
	
	@Transactional(propagation=Propagation.NESTED)
	public void test4Nested() {
		User user = new User();
		user.setId(1);
		user.setUsername("NESTED");
		
		this.mapper.updateByPrimaryKeySelective(user);
		System.out.println("OK.");
		
		/* 抛出异常。判断是为了通过编译 */
		if (1 == 1) {
			// throw new RuntimeException();
		}
	}
	
}
