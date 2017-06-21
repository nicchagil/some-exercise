package com.nicchagil.transmittablethreadlocalexercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

/**
 * 父线程在不同时期设置不同值，线程池中复用子线程的效果测试
 */
public class SubThreadStatefulData {
	
	private static ThreadLocal<Integer> threadLocal = new TransmittableThreadLocal<Integer>();

	public static void main(String[] args) throws InterruptedException {
		threadLocal.set(1);
		
		/* 声明多线程组件 */
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				otherMethod();
			}
		};
		TtlRunnable ttlRunnable = TtlRunnable.get(runnable);
		
		// 运行一个线程
		executorService.execute(ttlRunnable);
		
		TimeUnit.SECONDS.sleep(1); // 睡眠，让上面线程跑完
		
		threadLocal.set(2);
		
		/* 运行一个线程 */
		executorService.execute(ttlRunnable);
	}
	
	public static void otherMethod() {
		System.out.println("threadLocal.get() -> " + threadLocal.get());
	}

}
