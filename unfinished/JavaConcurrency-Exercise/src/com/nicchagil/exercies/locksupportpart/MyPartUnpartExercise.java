package com.nicchagil.exercies.locksupportpart;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Logger;

public class MyPartUnpartExercise {
	
	private static Logger logger = Logger.getLogger(MyPartUnpartExercise.class.getName());
	private static Object object = new Object();

	public static void main(String[] args) {
		Thread mainThread = Thread.currentThread();
		
		/* 其他线程在30S后唤醒主线程 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				LockSupport.unpark(mainThread); // 唤醒
				logger.info(Thread.currentThread().getName() + "唤醒" + mainThread.getName());
			}
		}).start();
		
		logger.info(Thread.currentThread().getName() + "准备被阻塞");
		LockSupport.park(object); // 阻塞
		logger.info(Thread.currentThread().getName() + "被唤醒，开始执行");
	}

}
