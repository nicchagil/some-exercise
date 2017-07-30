package com.nicchagil.springintegrationmqttexervice;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class MyConsumer {
	
	Logger logger = Logger.getLogger("MyConsumer");

	public void consumerForBusiness(String message) {
		logger.info("开始处理：" + message);
		
		int random = new Random().nextInt(2); // 这里只测试0、1两种情况。TODO 2即抛出异常的情况需另外处理
		if (random == 0) {
			logger.info("模拟业务正常完成");
			
		} else if (random == 1) {
			logger.info("模拟处理业务需时一段时间");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else if (random == 2) {
			logger.info("模拟抛出异常");
			throw new RuntimeException("模拟抛出异常");
			
		}
		
		logger.info("完成处理：" + message);
	}

}
