package com.nicchagil.hahcsceneexercise.coupon;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class CouponService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private CouponDAO couponDAO = new CouponDAO();
	
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
		countDownLatch.countDown(); // 用于计算时间（用于测试）
		
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
		Map<String, String> map = this.couponDAO.getUnusedCoupon();
		String id = map.get("id");
		
		int num = this.couponDAO.setCouponUsed(Integer.valueOf(id));
		
		if (num == 1) {
			Coupon c = new Coupon();
			c.setCode(map.get("code"));
			return c;
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
	
	private static CountDownLatch countDownLatch = new CountDownLatch(200);
	
	@Test
	public void testGetCoupon() throws Exception {
		final CouponService couponService = new CouponService();
		
		ExecutorService es = Executors.newFixedThreadPool(35);
		
		this.logger.info("开始执行");
		for (int i = 0; i < 200; i++) {
			es.execute(new Runnable() {
				@Override
				public void run() {
					couponService.getCoupon(123456);
				}
			});
		}
		
		countDownLatch.await();
		this.logger.info("结束执行");
		
		/** 没用连接池，测试结果：
		 * 2017-08-12 22:13:02.951 [main] INFO  c.n.h.coupon.CouponService - 开始执行 
		 * 2017-08-12 22:13:30.558 [main] INFO  c.n.h.coupon.CouponService - 结束执行 
		 * 
		 */
	}
	
}
