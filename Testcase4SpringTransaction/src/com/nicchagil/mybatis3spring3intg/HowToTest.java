package com.nicchagil.mybatis3spring3intg;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicchagil.mybatis3spring3intg.service.UserService;

public class HowToTest {

	public static void main(String[] args) {
		ApplicationContext context =
                new ClassPathXmlApplicationContext("/config/spring_mybatis.xml");
		UserService userService = context.getBean("userService", UserService.class);
		
		// 简单地测试事务是否正常
		// userService.test1();
		
		// 测试进入Service的第一个方法并无包含@Transactional，而该方法调用的含有@Transactional的方法，被调用的方法的事务是否有效
		// userService.test2Wrap();
		
		// 测试在调用的方法启动新事务（新方法在同一个Service与不同Service的区别）
		// userService.test3Wrap();
		
		/* 测试REQUIRES_NEW与NESTED的区别 */
		// userService.test4();
	}
	
	/* 备份相关SQL */
	/*
	   update t_user t set t.`username` = 'Nick Huang', t.`childhoodname` = 'nh' where t.id = 1;
	   select * from t_user t where t.`id` = 1;
	 */

}
