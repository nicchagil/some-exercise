package com.nicchagil.hahcsceneexercise.hyistrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.nicchagil.hahcsceneexercise.coupon.Coupon;
import com.nicchagil.hahcsceneexercise.coupon.CouponService;

public class CouponServiceCommand extends HystrixCommand<Coupon> {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	CouponService couponService = new CouponService();

	public CouponServiceCommand() {
		super(getCommandSetting());
	}

	private static HystrixCommand.Setter getCommandSetting() {
		HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("coupon-group"); // 服务分组
		HystrixCommandKey commandKey = HystrixCommandKey.Factory.asKey("get-coupon"); // 服务标识
		HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory.asKey("get-coupon-thread-pool"); // 服务线程池

		HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(groupKey).andCommandKey(commandKey)
				.andThreadPoolKey(threadPoolKey);
		/*
		HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(groupKey).andCommandKey(commandKey)
				.andThreadPoolKey(threadPoolKey).andThreadPoolPropertiesDefaults(HystrixCommonSetting.getThreadPoolPropertiesSetting())
				.andCommandPropertiesDefaults(HystrixCommonSetting.getCommandPropertiesSetting());
		*/
		return setter;
	}

	@Override
	protected Coupon run() throws Exception {
		return this.couponService.getCoupon(123456);
	}
	
	@Override
	protected Coupon getFallback() {
		this.logger.error("Fallback");
		return new Coupon();
	}

	public static void main(String[] args) {
		CouponServiceCommand couponServiceCommand = new CouponServiceCommand();
		
		for (int i = 0; i < 1; i++) {
			couponServiceCommand.execute();
		}
	}
	
}
