package com.nicchagil.exercies.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CountDownLatchExercise {
	
	private static Logger logger = Logger.getLogger(CountDownLatchExercise.class.getName());
	
	private static CountDownLatch countDownLatch = new CountDownLatch(2);

	public static void main(String[] args) throws InterruptedException {
		logger.info(Thread.currentThread().getName() + " start..."); // 主任务开始
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				logger.info(Thread.currentThread().getName() + " complete..."); // 子任务一完成
				countDownLatch.countDown();
			}
		});
		
		executorService.execute(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				logger.info(Thread.currentThread().getName() + " complete..."); // 子任务二完成
				countDownLatch.countDown();
			}
		});
		
		countDownLatch.await();
		logger.info(Thread.currentThread().getName() + " complete..."); // 主任务完成
	}

}
