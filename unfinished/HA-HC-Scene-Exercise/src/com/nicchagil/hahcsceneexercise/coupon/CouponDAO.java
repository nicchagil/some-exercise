package com.nicchagil.hahcsceneexercise.coupon;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.hahcsceneexercise.util.jdbc.JDBCUtil;

public class CouponDAO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 领取优惠券
	 * @param userId 用户ID
	 */
	public Map<String, String> getUnusedCoupon() {
		List<Map<String, String>> list = JDBCUtil.query("select t.* from t_coupon t where t.`used` = 0 limit 1");
		if (list == null || list.size() == 0) {
			return null;
		}
		
		return list.get(0);
	}
	
	/**
	 * 设置指定ID的优惠券为已领取
	 * @param id 优惠券ID
	 * @return 操作记录数
	 */
	public int setCouponUsed(Integer id) {
		return JDBCUtil.execute("update t_coupon t set t.used = 1 where t.id = ? and t.used = 0", new Object[] {id});
	}
	
	@Test
	public void getCouponTest() {
		this.logger.info("getUnusedCoupon() -> " + getUnusedCoupon());
	}

}
