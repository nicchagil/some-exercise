package com.nicchagil.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicchagil.service.CouponService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"spring-ioc.xml"})
public class CouponRedisServiceTest {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CouponService couponService;
	
	// 未调试成功
	@Test
	public void testCouponRedisService() {
		this.logger.info("result -> ");
	}

}
