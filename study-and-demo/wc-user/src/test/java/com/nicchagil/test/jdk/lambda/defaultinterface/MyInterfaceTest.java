package com.nicchagil.test.jdk.lambda.defaultinterface;

import org.junit.Test;

public class MyInterfaceTest implements MyInterface {
	
	@Test
	public void test() {
		new MyInterfaceTest().sayHelloWorld();
		new MyInterfaceTest().sayHappyNewYear();
	}
	
}
