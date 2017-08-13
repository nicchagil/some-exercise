package com.nicchagil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.nicchagil.constant.RedisConstant;

@Service
public class CouponRedisService {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public Long subtractCoupon() {
		return redisTemplate.opsForValue().increment(RedisConstant.COUPON_100, -1L); // TODO 结果异常
	}

}
