package com.nicchagil.exercise.lambda.anonymousclass;

public class NoParameter {
	
	public static void main(String[] args) {
		MyInterface myInterface = () -> System.out.println("Hello World");
		myInterface.methodA();
	}
	
	public interface MyInterface {
		public void methodA();
	}

}
