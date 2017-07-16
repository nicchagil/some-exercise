package com.nicchagil.exercies.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class NewFixedThreadPoolExercise {
	
	private static Logger logger = Logger.getLogger(NewFixedThreadPoolExercise.class.getName());
	
	public static void main(String[] args) {
		// = new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		
		// = new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()))
		// Executors.newSingleThreadExecutor();
		
		for (int i = 0; i <= 10; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					logger.info(Thread.currentThread().getName() + "开始运行");
					
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}

}
