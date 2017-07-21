package com.nicchagil.exercies.reentrantlock.interruptibly;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SyncInterruptiblyExercise {
	
	private static Logger logger = Logger.getLogger(SyncInterruptiblyExercise.class.getName());
	private static Object obj = new Object();

	/**
	 * 测试synchronized获取锁时被打断是否抛出InterruptedException
	 * 结果：
	 * 七月 12, 2017 9:30:42 下午 com.nicchagil.exercies.reentrantlock.interruptibly.SyncInterruptiblyExercise main
	 * 信息: 阻塞获取锁
	 * 七月 12, 2017 9:30:48 下午 com.nicchagil.exercies.reentrantlock.interruptibly.SyncInterruptiblyExercise main
	 * 信息: 释放锁
	 */
	public static void main(String[] args) {
		synchronized (obj) {
			logger.info("阻塞获取锁");
			
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						synchronized (obj) {
							
						}
					} catch (Exception e) {
						logger.info(Thread.currentThread().getName() + "获取锁被打断");
					}
				}
			});
			t1.start();
			
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			t1.interrupt(); // 打断线程
			
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			logger.info("释放锁");
		}
	}

}
