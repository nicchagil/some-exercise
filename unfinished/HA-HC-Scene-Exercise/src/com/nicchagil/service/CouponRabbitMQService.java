package com.nicchagil.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicchagil.model.Coupon;

@Service
public class CouponRabbitMQService {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	/**
	 * 发送消息
	 * @param id 优惠券ID
	 * @param userId 用户ID
	 */
	public Coupon send(Long id, Long userId) {
		Coupon coupon = new Coupon();
		coupon.setId(id);
		coupon.setUserId(userId);
		
		this.amqpTemplate.convertAndSend("couponQueue", coupon);
		return coupon;
	}

}
