package com.nicchagil.exercies.condition;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class ReentrantLockConditionExercise {
	
	private static Logger logger = Logger.getLogger(ReentrantLockConditionExercise.class.getName());
	
	private static volatile boolean flag = false;
	
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				
				try {
					while (!flag) {
						logger.info(Thread.currentThread().getName() + "继续等待（条件还不成熟）");
						condition.await(); // 等待其他线程改变当前线程需要的条件（会释放锁）
					}
					logger.info(Thread.currentThread().getName() + "继续业务（条件已成熟）");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				
				try {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					logger.info(Thread.currentThread().getName() + "开始改变数据");
					flag = true;
					condition.signal(); // 唤醒其他线程（释放锁）
					logger.info(Thread.currentThread().getName() + "改变数据完毕，并通知其它线");
				} finally {
					lock.unlock();
				}
			}
		}).start();
		
	}

}
