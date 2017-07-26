package com.nicchagil.messagelandinghandler;

import java.util.Random;

public class UserLandingLogDAO {
	
	public UserLandingLog save(UserLandingLog userLandingLog) {
		if (new Random().nextInt(2) == 0) { // 结果可能为0、1，为0表示失败
			throw new RuntimeException("模拟保存失败");
		}
		
		return userLandingLog;
	}
	
	public void updateById(UserLandingLog userLandingLog) {
		if (new Random().nextInt(2) == 0) { // 结果可能为0、1，为0表示失败
			throw new RuntimeException("模拟更新失败");
		}
	}

}
