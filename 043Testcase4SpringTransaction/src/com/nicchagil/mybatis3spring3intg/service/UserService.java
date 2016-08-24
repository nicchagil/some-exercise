
package com.nicchagil.mybatis3spring3intg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nicchagil.mybatis3spring3intg.bean.User;
import com.nicchagil.mybatis3spring3intg.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private AnotherUserService anotherUserService;
	
	/**
	 * 简单地测试事务是否正常
	 */
	@Transactional
	public void test1() {
		User user = new User();
		user.setId(1);
		user.setUsername("Nick Huang 1");
		
		this.mapper.updateByPrimaryKeySelective(user);
		System.out.println("OK.");
		
		throw new RuntimeException();		
	}
	
	/**
	 * 测试进入Service的第一个方法并无包含@Transactional，而该方法调用的含有@Transactional的方法，被调用的方法的事务是否有效
	 */
	public void test2Wrap() {
		this.test2();
	}
	
	@Transactional
	public void test2() {
		User user = new User();
		user.setId(1);
		user.setUsername("Nick Huang 2");
		
		this.mapper.updateByPrimaryKeySelective(user);
		System.out.println("OK.");
		
		throw new RuntimeException();		
	}
	
	/**
	 * 测试在调用的方法启动新事务（新方法在同一个Service与不同Service的区别）
	 */
	@Transactional
	public void test3Wrap() {
		User user = new User();
		user.setId(1);
		user.setUsername("Nick Huang 3-1");
		
		this.mapper.updateByPrimaryKeySelective(user);
		System.out.println("OK.");
		
		try {
			this.test3(); // 方式一
			// this.anotherUserService.test3(); // 方式二，对比结果
		} catch (Exception e) {
			System.out.println("Catched a exception.");
		}
	}
	
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
