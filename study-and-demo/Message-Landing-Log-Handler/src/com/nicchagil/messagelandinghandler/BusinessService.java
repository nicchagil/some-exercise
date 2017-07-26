package com.nicchagil.messagelandinghandler;

import java.util.Random;

import com.nicchagil.messagelandinghandler.framework.MessageLandingHandler;
import com.nicchagil.messagelandinghandler.framework.constant.BusinessStatusEnum;

public class BusinessService {
	
	private MessageLandingHandler<User> messageLandingHandler = new MessageLandingHandler<User>();
	private UserLandingLogOpsHandler userLandingLogOpsHandler = new UserLandingLogOpsHandler();
	
	public void business(User user) {
		UserLandingLog userLandingLog = this.messageLandingHandler.landing(user);
		try {
			this.doBusiness(user);
			userLandingLog.setStatus(BusinessStatusEnum.COMPLATE.getCode());
			
		} catch (Exception e) {
			userLandingLog.setStatus(BusinessStatusEnum.EXCEPTION.getCode());
			userLandingLog.setExceptionMsg(e.getMessage());
		}
		
		this.userLandingLogOpsHandler.ops(userLandingLog);
		
	}
	
	public void doBusiness(User user) {
		/* 此业务要保证幂等性（比如上次执行时，业务已执行，但更新日志表失败） */
		
		if (new Random().nextInt(2) == 0) { // 结果可能为0、1，为0表示失败
			throw new RuntimeException("模拟业务失败");
		}
	}

}
