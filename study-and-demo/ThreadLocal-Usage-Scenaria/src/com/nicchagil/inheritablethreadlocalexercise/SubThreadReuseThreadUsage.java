package com.nicchagil.inheritablethreadlocalexercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池中复用子线程的效果测试
 */
public class SubThreadReuseThreadUsage {
	
	private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<Integer>();

	public static void main(String[] args) throws InterruptedException {
		threadLocal.set(1);
		
		/* 声明多线程组件 */
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Runnable runnableA = new Runnable() {
			@Override
			public void run() {
				otherMethodA();
			}
		};
		Runnable runnableB = new Runnable() {
			@Override
			public void run() {
				otherMethodB();
			}
		};
		
		// 运行一个线程
		executorService.execute(runnableA);
		
		TimeUnit.SECONDS.sleep(1); // 睡眠，让上面线程跑完
		
		/* 运行一个线程 */
		executorService.execute(runnableB);
	}
	
	public static void otherMethodA() {
		System.out.println("threadLocal.get() -> " + threadLocal.get());
		threadLocal.set(2);
	}
	
	public static void otherMethodB() {
		System.out.println("threadLocal.get() -> " + threadLocal.get());
	}

}
