package com.nicchagil.exercise.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamExercise {
	
	private static List<String> list = new ArrayList<String>();
	
	static {
		for (int i = 0; i < 5; i++) {
			list.add(String.valueOf(i));
		}
	}
	
	public static void main(String[] args) {
		StreamExercise.traditional();
		StreamExercise.lambda();
	}
	
	public static void traditional() {
		long count = 0;
		for (String s : list) {
			if (s != null && s.equals("1")) {
				count++;
			}
		}
		
		System.out.println("count -> " + count);
	}
	
	public static void lambda() {
		Stream<String> stream = list.stream();
		System.out.println("count -> " + stream.filter(s -> s != null && s.equals("1")).count());
	}

}
