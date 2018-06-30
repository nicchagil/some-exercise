package com.nicchagil.test.jdk.lambda.defaultinterface;

public interface MyInterface {
	
	default void sayHelloWorld() {
		System.out.println("hello world");
	}
	
	default void sayHappyNewYear() {
		System.out.println("happy new year");
	}

}
