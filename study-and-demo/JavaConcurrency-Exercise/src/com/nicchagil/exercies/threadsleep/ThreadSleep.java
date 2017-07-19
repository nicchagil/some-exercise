package com.nicchagil.exercies.threadsleep;

import java.util.logging.Logger;

public class ThreadSleep {
	
	private static Logger logger = Logger.getLogger(ThreadSleep.class.getName());

	public static void main(String[] args) {
		logger.info("开始睡眠");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.info("结束睡眠");
	}

}
