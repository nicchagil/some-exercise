package com.nicchagil.exercies.volatileexercise;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.nicchagil.exercies.reentrantreadwritelock.ReentrantReadWriteLockReadLockExercise;

public class VolatileExercise {
	
	private static Logger logger = Logger.getLogger(ReentrantReadWriteLockReadLockExercise.class.getName());
	private static boolean flag = true;

	/**
	 * Note : Unfinished
	 */
	public static void main(String[] args) throws IOException {
		
		new Thread(new MyRunnable()).start();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				flag = false;
			}
		}).start();
		
		System.in.read();
	}
	
	public static class MyRunnable implements Runnable {
		@Override
		public void run() {
			while (true) {
				logger.info("do business.");
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if (!flag) {
					break;
				}
			}
		}
	}

}
