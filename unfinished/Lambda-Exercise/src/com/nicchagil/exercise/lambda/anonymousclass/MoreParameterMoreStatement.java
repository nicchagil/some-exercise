package com.nicchagil.exercise.lambda.anonymousclass;

public class MoreParameterMoreStatement {

	public static void main(String[] args) {
		MyInterface myInterface = (x, y) -> {
			System.out.println("1st parameter : " + x);
			System.out.println("2nd parameter : " + y);
		};
		myInterface.methodA(1, 5);
	}

	public interface MyInterface {
		public void methodA(Integer x, Integer y);
	}

}
