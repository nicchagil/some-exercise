package com.nicchagil.exercise.lambda.anonymousclass;

public class ThreadByLambda {

	public static void main(String[] args) {
		new Thread(() -> {
			System.out.println("1");
			System.out.println("2");
		}).start();
	}

	public static void traditional() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("hello world");
			}
		}).start();
	}

	public static void lambda() {
		new Thread(() -> System.out.println("hello world")).start();
	}

}
