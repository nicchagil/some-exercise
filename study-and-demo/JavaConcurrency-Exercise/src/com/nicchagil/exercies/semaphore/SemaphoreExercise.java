package com.nicchagil.exercies.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SemaphoreExercise {
	
	private static Logger logger = Logger.getLogger(SemaphoreExercise.class.getName());
	
	private static Semaphore semaphore = new Semaphore(3); // 最多同时通过3个信号的信号量

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for (int i = 0; i <= 10; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire(); // 获取一个信号
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					/* 睡眠3S */
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					logger.info(Thread.currentThread().getName() + " run...");
					
					semaphore.release(); // 释放一个信号
				}
			});
		}
		
	}

}
