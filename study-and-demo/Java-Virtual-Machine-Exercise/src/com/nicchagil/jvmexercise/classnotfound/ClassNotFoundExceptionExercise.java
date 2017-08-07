package com.nicchagil.jvmexercise.classnotfound;

public class ClassNotFoundExceptionExercise {
	
	public static void main(String[] args) {
		try {
			Class.forName("java.lang.StringX");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
