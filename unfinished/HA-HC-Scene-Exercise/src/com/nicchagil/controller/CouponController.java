package com.nicchagil.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicchagil.model.Coupon;
import com.nicchagil.service.CouponService;
import com.nicchagil.util.DateTimeUtil;

@Controller
@RequestMapping("coupon")
public class CouponController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CouponService couponService;
    
    @RequestMapping("getCoupon")
    @ResponseBody
    public Object getCoupon() { // http://localhost:8080/coupon/getCoupon
    	try {
    		Coupon coupon = this.couponService.getCoupon(Long.valueOf(DateTimeUtil.format(DateTimeUtil.PATTERN_DATE_TIME_NUMBER, new Date())));
    		return coupon;
    	} catch (Exception e) {
    		this.logger.error("领取失败。{}", e);
    		return "领取失败。" + e.getMessage();
    	}
    }

}
