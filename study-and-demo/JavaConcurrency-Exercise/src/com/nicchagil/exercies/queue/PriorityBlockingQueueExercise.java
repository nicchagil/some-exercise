package com.nicchagil.exercies.queue;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueExercise {
	
	private static PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<Integer>();
	
	public static void main(String[] args) {
		queue.offer(3);
		queue.offer(1);
		queue.offer(2);
		
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}

}
