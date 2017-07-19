package com.nicchagil.exercies.reentrantlock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class LockExercise {
	
	private static Logger logger = Logger.getLogger(LockExercise.class.getName());
	private static Lock lock = new ReentrantLock();

	public static void main(String[] args) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				try {
					logger.info(Thread.currentThread().getName() + " run.");
					
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
					logger.info(Thread.currentThread().getName() + " run.");
					
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} finally {
					lock.unlock();
				}
			}
		}).start();
		
	}

}
