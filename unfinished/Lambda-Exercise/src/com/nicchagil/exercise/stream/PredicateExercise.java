package com.nicchagil.exercise.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PredicateExercise {
	
	/**
	 * 过滤
	 */
	public static void main(String[] args) {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		
		Stream<String> stream = list.stream();
		Predicate<String> predicate = s -> s != null && s.equals("1"); // 断言，Predicate。入参对象，出参boolean，用于判别一个对象
		System.out.println("count -> " + stream.filter(predicate).count()); // 过滤并统计
	}

}
