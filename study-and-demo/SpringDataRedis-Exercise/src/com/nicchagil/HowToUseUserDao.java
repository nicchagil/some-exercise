package com.nicchagil;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicchagil.entity.User;
import com.nicchagil.redis.dao.UserRedisDao;


public class HowToUseUserDao {

	public static void main(String[] args) throws IOException {
		
	}
	
	@Test
	public void put() {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-redis.xml"})) {
			UserRedisDao userRedisDao = context.getBean("userRedisDao", UserRedisDao.class);
			
			User user = new User();
			user.setId(5);
			user.setName("Nick Huang");
			
			userRedisDao.put("user_" + user.getId(), user);
		}
	}
	
	@Test
	public void get() {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-redis.xml"})) {
			UserRedisDao userRedisDao = context.getBean("userRedisDao", UserRedisDao.class);
			User user = userRedisDao.get("user_5");
			System.out.println(user);
		}
	}

}
