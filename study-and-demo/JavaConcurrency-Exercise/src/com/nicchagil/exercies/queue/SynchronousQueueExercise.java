package com.nicchagil.exercies.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class SynchronousQueueExercise {
	
	private static Logger logger = Logger.getLogger(SynchronousQueueExercise.class.getName());
	
	private static SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
	
	public static void main(String[] args) {
		try {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(1);
						logger.info("take " + queue.take());
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
			
			logger.info("ready to put 1");
			queue.put(1); // 阻塞等待take
			logger.info("put 1");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
