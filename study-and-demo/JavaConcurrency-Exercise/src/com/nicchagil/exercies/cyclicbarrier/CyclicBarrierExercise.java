package com.nicchagil.exercies.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.nicchagil.exercies.countdownlatch.CountDownLatchExercise;

public class CyclicBarrierExercise {
	
	private static Logger logger = Logger.getLogger(CountDownLatchExercise.class.getName());
	
	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
		@Override
		public void run() {
			logger.info(Thread.currentThread().getName() + " call...");
		}
	});

	public static void main(String[] args) {
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
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		try {
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(Thread.currentThread().getName() + " complete..."); // 主任务完成
	}

}
