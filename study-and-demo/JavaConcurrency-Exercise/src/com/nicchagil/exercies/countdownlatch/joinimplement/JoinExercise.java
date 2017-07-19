package com.nicchagil.exercies.countdownlatch.joinimplement;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class JoinExercise {
	
	private static Logger logger = Logger.getLogger(JoinExercise.class.getName());
	
	public static void main(String[] args) {
		logger.info(Thread.currentThread().getName() + " start..."); // 主任务开始
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				logger.info(Thread.currentThread().getName() + " complete..."); // 子任务一完成
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				logger.info(Thread.currentThread().getName() + " complete..."); // 子任务二完成
			}
		});
		
		t1.start();
		t2.start();
		
		/* 插入主线程，让主线程等待其完成 */
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info(Thread.currentThread().getName() + " complete..."); // 主任务完成
	}

}
