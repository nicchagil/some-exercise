package com.nicchagil.threadlocalexercise;

public class BasicUsage {
	
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

	public static void main(String[] args) {
		threadLocal.set(1);
		otherMethod();
	}
	
	public static void otherMethod() {
		System.out.println("threadLocal.get() -> " + threadLocal.get()); // 其它Class、其它方法，只要在此线程内就可获取
	}

}
