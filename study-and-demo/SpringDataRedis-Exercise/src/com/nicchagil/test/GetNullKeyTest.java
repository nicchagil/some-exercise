package com.nicchagil.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-redis.xml"})
public class GetNullKeyTest {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void tc1() {
		String s = this.stringRedisTemplate.opsForValue().get("asdfasdfa");
		System.out.println("s : " + s);
	}

}
