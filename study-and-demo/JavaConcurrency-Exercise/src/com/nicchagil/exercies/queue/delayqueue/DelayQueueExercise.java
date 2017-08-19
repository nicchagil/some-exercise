package com.nicchagil.exercies.queue.delayqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueExercise {
	
	private static DelayQueue<MyDelayed> queue = new DelayQueue<MyDelayed>();
	
	public static void main(String[] args) {
		queue.offer(new MyDelayed(TimeUnit.NANOSECONDS.convert(5, TimeUnit.SECONDS)));
		queue.offer(new MyDelayed(TimeUnit.NANOSECONDS.convert(3, TimeUnit.SECONDS)));
		queue.offer(new MyDelayed(TimeUnit.NANOSECONDS.convert(8, TimeUnit.SECONDS)));
		
		try {
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
