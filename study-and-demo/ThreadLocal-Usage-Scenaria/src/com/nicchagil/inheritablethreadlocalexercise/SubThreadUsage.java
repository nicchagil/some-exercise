package com.nicchagil.inheritablethreadlocalexercise;

public class SubThreadUsage {
	
	private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<Integer>();

	public static void main(String[] args) {
		threadLocal.set(1);
		
		// 新启一个线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				otherMethod();
			}
		}).start();
	}
	
	public static void otherMethod() {
		System.out.println("threadLocal.get() -> " + threadLocal.get());
	}

}
