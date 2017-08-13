package com.nicchagil.constant;

public enum CouponStatusEnum {
	
	UN_USED(0), USED(1);
	
	private Integer code;

	private CouponStatusEnum(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
	
}
