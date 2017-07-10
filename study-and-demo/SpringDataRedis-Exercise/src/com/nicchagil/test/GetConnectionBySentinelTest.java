package com.nicchagil.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 使用哨兵获取连接
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-redis-sentinel.xml"})
public class GetConnectionBySentinelTest {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void tc1() {
		this.stringRedisTemplate.opsForValue().set("time", "1", 1800, TimeUnit.SECONDS);
		System.out.println("ok");
	}

}
