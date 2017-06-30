package com.nicchagil.mybatis3spring3intg;

import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicchagil.mybatis3spring3intg.bean.User;
import com.nicchagil.mybatis3spring3intg.service.trycatchwithtransaction.UserServiceA;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/config/spring_mybatis.xml"})
public class TryCatchWithTransactionTester {
	
	@Autowired
	private UserServiceA userServiceA;
	
	/**
	 * 重现：org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only
	 */
	@Test
	public void tc2() throws FileNotFoundException {
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("Nick(2017-06-30)");
		
		User user2 = new User();
		user2.setId(2);
		user2.setUsername("Robin(2017-06-30)");
		
		// 不捕捉异常
		// this.userServiceA.updateUser(user1, user2);
		
		// 捕捉异常
		try {
			this.userServiceA.updateUser(user1, user2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		  	update t_user_test_transaction t set t.`username` = 'Nick' where t.`id` = 1;
			update t_user_test_transaction t set t.`username` = 'Robin' where t.`id` = 2;
			
			select * from t_user_test_transaction t;
		 */
	}
	
}
