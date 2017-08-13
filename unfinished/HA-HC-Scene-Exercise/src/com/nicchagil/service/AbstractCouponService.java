package com.nicchagil.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.model.Coupon;

public abstract class AbstractCouponService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 领取优惠券
	 * @param userId 用户ID
	 */
	public abstract Coupon getCoupon(Long userId);
	
	/**
	 * 是否有领取优惠券的权限
	 * @param userId 用户ID
	 * @return 是否有领取优惠券的权限
	 */
	public boolean getCouponPermission(Long userId) {
		return true;
	}
	
}
