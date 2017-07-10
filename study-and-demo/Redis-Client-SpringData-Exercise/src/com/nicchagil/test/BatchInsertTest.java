package com.nicchagil.test;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicchagil.entity.User;
import com.nicchagil.redis.dao.UserRedisDao;

/**
 * 批量插入
 */
public class BatchInsertTest {

	public static void main(String[] args) throws IOException {
		
	}
	
	@Test
	public void putAndDel() {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-redis.xml"})) {
			UserRedisDao userRedisDao = context.getBean("userRedisDao", UserRedisDao.class);
			
			User user = new User();
			user.setId(123456);
			user.setName("Nick Huang");
			
			for (int i = 1; i < 1000; i++) {
				userRedisDao.put("user_" + i, user);
			}
			
		}
	}
	
}
