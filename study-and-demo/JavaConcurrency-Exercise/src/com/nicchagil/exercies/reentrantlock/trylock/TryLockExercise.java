package com.nicchagil.exercies.reentrantlock.trylock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class TryLockExercise {
	
	private static Logger logger = Logger.getLogger(TryLockExercise.class.getName());

	public static void main(String[] args) {
		Lock lock = new ReentrantLock(); // 声明可重入锁
		
		lock.lock(); // 阻塞获取锁
		logger.info("阻塞获取锁");
		try {
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					int tryLockCount = 0;
					boolean result = false;
					
					while (!result) {
						result = lock.tryLock(); // 尝试获取锁
						tryLockCount++;
						logger.info("第" + tryLockCount + "次尝试获取锁");
						
						if (tryLockCount >= 3) {
							break;
						}
					}
				}
			}).start();
			
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
