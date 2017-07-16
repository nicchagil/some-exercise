package com.nicchagil.exercies.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ThreadPoolExecutorExercise {
	
	private static Logger logger = Logger.getLogger(ThreadPoolExecutorExercise.class.getName());

	public static void main(String[] args) {
		/* 核心线程池为3，最大线程池位6，链式堵塞队列长度为2 */
		ExecutorService executorService = new ThreadPoolExecutor(3, 6, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2));
		
		for (int i = 0; i <= 10; i++) {
			try {
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						logger.info(Thread.currentThread().getName() + "开始运行");
						
						try {
							TimeUnit.SECONDS.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			} catch (Exception e) {
				logger.info("第几个线程提交失败：" + i);
			}
		}
	}

}
