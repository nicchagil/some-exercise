package com.nicchagil.redis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.nicchagil.entity.User;

@Repository
public class UserRedisDao {
	
	@Autowired
	private RedisTemplate<Object, User> redisTemplate;
	
	public void put(final Object key, final User value) {
		this.redisTemplate.opsForValue().set(key, value);
	}
	
	public User get(final Object key) {
		return this.redisTemplate.opsForValue().get(key);
	}
	
	public void del(final Object key) {
		ValueOperations<Object, User>  valueOps = this.redisTemplate.opsForValue();
		valueOps.getOperations().delete(key);
	}
	
}
