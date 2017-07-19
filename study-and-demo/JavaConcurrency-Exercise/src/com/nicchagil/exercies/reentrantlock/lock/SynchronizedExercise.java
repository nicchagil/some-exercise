package com.nicchagil.exercies.reentrantlock.lock;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SynchronizedExercise {
	
	private static Logger logger = Logger.getLogger(SynchronizedExercise.class.getName());
	private static Object obj = new Object();

	public static void main(String[] args) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj) {
					logger.info(Thread.currentThread().getName() + " run.");
					
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (obj) {
					logger.info(Thread.currentThread().getName() + " run.");
					
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

}
