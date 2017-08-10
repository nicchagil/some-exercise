package com.nicchagil.hahcsceneexercise.coupon;

import org.springframework.util.Assert;

public class CouponService {
	
	/**
	 * 领取优惠券
	 * @param userId 用户ID
	 */
	public Coupon getCoupon(Integer userId) {
		Assert.notNull(userId, "请传入用户ID");
		
		/* 检查是否有领取权限 */
		if (!this.getCouponPermission(userId)) {
			throw new RuntimeException("你没有领取权限");
		}
		
		return this.getCoupon();
	}
	
	/**
	 * 是否有领取优惠券的权限
	 * @param userId 用户ID
	 * @return 是否有领取优惠券的权限
	 */
	public boolean getCouponPermission(Integer userId) {
		return true;
	}
	
	/**
	 * 获取优惠券
	 * @return 优惠券
	 */
	public Coupon getCoupon() {
		// RandomUtil.sleep(500);
		// RandomUtil.simulateException(0, 10);
		
		return new Coupon();
	}

}
