package com.nicchagil.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.nicchagil.constant.CouponStatusEnum;
import com.nicchagil.dao.CouponMapper;
import com.nicchagil.model.Coupon;

@Service
public class CouponService extends AbstractCouponService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CouponMapper couponMapper;
	
	@Autowired
	private CouponRedisService couponRedisService;
	
	@Autowired
	private CouponRabbitMQService couponRabbitMQService;
	
	@Value("${coupon_100_max}")
	private long coupon100Max;
	
	@Value("${coupon_100_min}")
	private long coupon100Min;
	
	/**
	 * 领取优惠券
	 * @param userId 用户ID
	 */
	public Coupon getCoupon(Long userId) {
		Assert.notNull(userId, "请传入用户ID");
		
		/* 检查是否有领取权限 */
		if (!this.getCouponPermission(userId)) {
			throw new RuntimeException("你没有领取权限");
		}
		
		long result = this.couponRedisService.subtractCoupon();
		if (result < coupon100Min - 1) {
			throw new RuntimeException("优惠券已领完");
		}
		
		// 将业务接到RabbitMQ
		return this.couponRabbitMQService.send(result + 1, userId);
		
		// 将业务接到MySQL
		/*
		int tries = 0;
		Coupon c = null;
		while (true) {
			tries++;
			c = this.doGetCoupon(result + 1, userId); // 尝试获取
			if (c != null) {
				break; // 已获取，跳出
			} else {
				continue; // 继续重试
			}
		}
		
		this.logger.info("重试 -> " + tries);
		
		if (c == null) {
			throw new RuntimeException("领取失败");
		}
		
		return c;
		*/
	}
	
	/**
	 * 获取优惠券（从库存中获取未领取的优惠券，然后设置该优惠券为已领取）
	 * @param userId 用户ID
	 * @return 优惠券
	 */
	public Coupon doGetCoupon(Long userId) {
		Coupon coupon = this.couponMapper.getUnusedCoupon(CouponStatusEnum.UN_USED.getCode());
		
		int num = this.couponMapper.updateUsedById(coupon.getId(), userId, CouponStatusEnum.UN_USED.getCode(), CouponStatusEnum.USED.getCode());
		
		if (num == 1) {
			return coupon;
		} else {
			return null;
		}
	}
	
	/**
	 * 获取优惠券
	 * @param id 优惠券ID
	 * @param userId 用户ID
	 * @return 优惠券
	 */
	public Coupon doGetCoupon(Long id, Long userId) {
		int num = this.couponMapper.updateUsedById(id, userId, CouponStatusEnum.UN_USED.getCode(), CouponStatusEnum.USED.getCode());
		
		if (num == 1) {
			Coupon coupon = this.couponMapper.selectByPrimaryKey(id);
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
	public boolean getCouponPermission(Long userId) {
		return true;
	}
	
}
