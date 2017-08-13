package com.nicchagil.dao;

import org.springframework.stereotype.Repository;

import com.nicchagil.model.Coupon;

@Repository
public interface CouponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
    
    Coupon getUnusedCoupon();
    
    int updateUsedById(Long userId);
    
}