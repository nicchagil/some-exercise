
package com.nicchagil.mybatis3spring3intg.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nicchagil.mybatis3spring3intg.bean.User;
import com.nicchagil.mybatis3spring3intg.mapper.UserMapper;

@Service
public class CheckedExceptionTryCatchService {
	
	@Autowired
	private UserMapper mapper;
	
	@Transactional
	public void test1() throws FileNotFoundException {
		User user = new User();
		user.setId(3);
		user.setAge(2);
		
		this.mapper.updateByPrimaryKeySelective(user);
		System.out.println("OK.");
		
		/* 测试结果：对于运行时异常，不回滚 */
		FileInputStream fis = new FileInputStream("d:/dsfadfadsfas/asdfasdfa.log");
	}
	
	@Transactional
	public void test2() {
		User user = new User();
		user.setId(3);
		user.setAge(3);
		
		this.mapper.updateByPrimaryKeySelective(user);
		System.out.println("OK.");
		
		/* 测试结果：回滚 */
		try {
			FileInputStream fis = new FileInputStream("d:/dsfadfadsfas/asdfasdfa.log");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
