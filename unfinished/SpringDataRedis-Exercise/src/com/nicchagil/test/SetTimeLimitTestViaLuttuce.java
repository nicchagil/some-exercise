package com.nicchagil.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-lettuce-redis.xml"})
public class SetTimeLimitTestViaLuttuce {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void tc1() {
		this.stringRedisTemplate.opsForValue().set("time", "999", 1800, TimeUnit.SECONDS);
		
		String time = this.stringRedisTemplate.opsForValue().get("time");
		System.out.println("ok -> " + time);
	}

}
