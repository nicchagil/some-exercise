package com.nicchagil.exercies.threadsleep;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TimeUnitThreadSleep {
	
	private static Logger logger = Logger.getLogger(TimeUnitThreadSleep.class.getName());

	public static void main(String[] args) {
		logger.info("开始睡眠");
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("结束睡眠");
	}

}
