package com.nicchagil;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicchagil.redis.dao.StringDao;


public class HowToUseStringDao {

	public static void main(String[] args) throws IOException {
		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-redis.xml"})) {
			StringDao stringDao = context.getBean("stringRedisService", StringDao.class);
			stringDao.put("chinese", "中文");
		}
	}

}
