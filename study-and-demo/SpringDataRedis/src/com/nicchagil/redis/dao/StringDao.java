package com.nicchagil.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StringDao {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	public void put(final String key, final String value) {
		this.stringRedisTemplate.opsForValue().set(key, value);
	}
	
	public String get(final String key) {
		return this.stringRedisTemplate.opsForValue().get(key);
	}
	
	public Long increment(final String key, final Long l) {
		return this.stringRedisTemplate.opsForValue().increment(key, l);
	}

}
