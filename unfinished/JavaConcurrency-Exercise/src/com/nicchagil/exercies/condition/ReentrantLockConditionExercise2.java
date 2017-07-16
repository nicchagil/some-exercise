package com.nicchagil.exercies.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class ReentrantLockConditionExercise2 {
	
	private static Logger logger = Logger.getLogger(ReentrantLockConditionExercise2.class.getName());
	
	private static volatile boolean flag = false;
	
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					condition.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 等待其他线程改变当前线程需要的条件（会释放锁）
			}
		}).start();
	}

}
