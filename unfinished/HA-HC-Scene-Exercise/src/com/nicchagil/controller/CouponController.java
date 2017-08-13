package com.nicchagil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nicchagil.model.Coupon;
import com.nicchagil.service.CouponService;

@Controller
@RequestMapping("coupon")
public class CouponController {
	
	@Autowired
	private CouponService couponService;
    
    @RequestMapping("getCoupon")
    @ResponseBody
    public Object getCoupon() { // http://localhost:8080/coupon/getCoupon
    	Coupon coupon = this.couponService.getCoupon(123456);
        return coupon;
    }

}
