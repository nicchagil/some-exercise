package com.nicchagil.exercies.reentrantlock.interruptibly;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class LockInterruptiblyExercise {
	
	private static Logger logger = Logger.getLogger(LockInterruptiblyExercise.class.getName());

	public static void main(String[] args) {
		Lock lock = new ReentrantLock(); // 声明可重入锁
		
		lock.lock(); // 阻塞获取锁
		logger.info("阻塞获取锁");
		try {
			
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						lock.lockInterruptibly(); // 尝试获取锁
					} catch (InterruptedException e) {
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
			
		} finally {
			lock.unlock(); // 释放锁
			logger.info("释放锁");
		}
	}

}
