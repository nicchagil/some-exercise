package com.nicchagil.exercies.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class NewCachedThreadPoolExercise {
	
	private static Logger logger = Logger.getLogger(NewCachedThreadPoolExercise.class.getName());
	
	public static void main(String[] args) {
		// = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>())
		ExecutorService executorService = Executors.newCachedThreadPool();
		
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
