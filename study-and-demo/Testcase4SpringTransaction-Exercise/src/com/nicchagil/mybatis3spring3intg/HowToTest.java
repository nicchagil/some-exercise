package com.nicchagil.mybatis3spring3intg;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicchagil.mybatis3spring3intg.service.CheckedExceptionTryCatchService;
import com.nicchagil.mybatis3spring3intg.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/config/spring_mybatis.xml"})
public class HowToTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CheckedExceptionTryCatchService checkedExceptionTryCatchService;

	/**
	 * 测试检查时异常是否回滚事务：直接抛出检查时异常，不回滚
	 */
	@Test
	public void tc2() throws FileNotFoundException {
		checkedExceptionTryCatchService.test1();
	}
	
	/**
	 * 测试检查时异常是否回滚事务：捕捉后转换为运行时异常抛出，回滚
	 */
	@Test
	public void tc3() {
		checkedExceptionTryCatchService.test2();
	}
	
	/**
	 * 测试进入Service的第一个方法非注解事务方法
	 */
	@Test
	public void tc1() {
		
		// 简单地测试事务是否正常
		// userService.test1();
		
		// 测试进入Service的第一个方法并无包含@Transactional，而该方法调用的含有@Transactional的方法，被调用的方法的事务是否有效
		// userService.test2Wrap();
		
		// 测试在调用的方法启动新事务（新方法在同一个Service与不同Service的区别）
		// userService.test3Wrap();
		
		/* 测试REQUIRES_NEW与NESTED的区别 */
		userService.test4();
		
		/* 备份相关SQL */
		/*
		    update t_user t set t.`username` = 'Nick Huang', t.`childhoodname` = 'nh' where t.id = 1;
			select * from t_user t where t.`id` = 1;
			
			update t_user t set t.`username` = 'Robin Chen', t.`childhoodname` = 'rc' where t.id = 2;
			select * from t_user t where t.`id` = 2;
		 */
	}

}
