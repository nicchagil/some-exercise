package com.nicchagil.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.nicchagil.dao.CouponMapper;
import com.nicchagil.model.Coupon;

@Service
public class CouponService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CouponMapper couponMapper;
	
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
		
		int tries = 0;
		Coupon c = null;
		while (tries < 3) {
			c = this.doGetCoupon(userId);
			if (c != null) {
				break;
			}
		}
		
		this.logger.info("重试 -> " + tries);
		
		if (c == null) {
			throw new RuntimeException("领取失败");
		}
		
		return c;
	}
	
	/**
	 * 获取优惠券（实际）
	 * @param userId 用户ID
	 * @return 优惠券
	 */
	public Coupon doGetCoupon(Integer userId) {
		Coupon coupon = this.couponMapper.getUnusedCoupon();
		
		int num = this.couponMapper.updateUsedById(coupon.getId());
		
		if (num == 1) {
			return coupon;
		} else {
			return null;
		}
	}
	
	/**
	 * 是否有领取优惠券的权限
	 * @param userId 用户ID
	 * @return 是否有领取优惠券的权限
	 */
	public boolean getCouponPermission(Integer userId) {
		return true;
	}
	
}
