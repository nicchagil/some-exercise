package com.nicchagil.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicchagil.entity.User;
import com.nicchagil.redis.dao.UserRedisDao;

/**
 * 批量插入
 */
public class BatchInsertTest {

	@Test
	public void putAndDel() {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-redis-sentinel.xml"})) {
			UserRedisDao userRedisDao = context.getBean("userRedisDao", UserRedisDao.class);
			
			User user = new User();
			user.setId(123456);
			user.setName("Nick Huang");
			
			for (int i = 1; i <= 1000; i++) {
				userRedisDao.put("user_" + i, user);
			}
			
			int oddCounter = 0;
			for (int i = 1; i <= 1000; i++) {
				if (i % 2 == 1) {
					userRedisDao.del("user_" + i);
					oddCounter++;
				}
			}
			System.out.println("delete the number of odd : " + oddCounter);
			
		}
	}
	
}
