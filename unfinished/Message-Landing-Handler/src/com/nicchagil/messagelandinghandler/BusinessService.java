package com.nicchagil.messagelandinghandler;

import java.util.Random;

import com.nicchagil.messagelandinghandler.framework.BusinessStatus;
import com.nicchagil.messagelandinghandler.framework.MessageLandingHandler;

public class BusinessService {
	
	private MessageLandingHandler<User> messageLandingHandler = new MessageLandingHandler<User>();
	private UserLandingLogOpsHandler userLandingLogOpsHandler = new UserLandingLogOpsHandler();
	
	public void business(User user) {
		UserLandingLog userLandingLog = this.messageLandingHandler.landing(user);
		try {
			this.doBusiness(user);
		} catch (Exception e) {
			userLandingLog.setStatus(BusinessStatus.EXCEPTION.getCode());
			userLandingLog.setExceptionMsg(e.getMessage());
		}
		
		userLandingLog.setStatus(BusinessStatus.COMPLATE.getCode());
		this.userLandingLogOpsHandler.ops(userLandingLog);
		
	}
	
	public void doBusiness(User user) {
		if (new Random().nextInt(2) == 0) {
			throw new RuntimeException("模拟业务失败");
		}
	}

}
