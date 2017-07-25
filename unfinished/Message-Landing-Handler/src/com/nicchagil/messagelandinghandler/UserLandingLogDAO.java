package com.nicchagil.messagelandinghandler;

import java.util.Random;

public class UserLandingLogDAO {
	
	public UserLandingLog save(UserLandingLog userLandingLog) {
		if (new Random().nextInt(2) == 0) {
			throw new RuntimeException("模拟保存失败");
		}
		
		return userLandingLog;
	}
	
	public void updateById(UserLandingLog userLandingLog) {
		
	}

}
