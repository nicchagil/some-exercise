package com.nicchagil.listener;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

import com.nicchagil.model.Coupon;

@Component
public class CouponConsumerListener {
	
	Logger logger = Logger.getLogger(this.getClass().getName());

	// @RabbitListener(queues = "couponQueue")
	public void consume(Coupon c) {
		logger.info("consume msg : " + c);
	}

}
