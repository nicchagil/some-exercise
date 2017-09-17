package com.nicchagil.exercise.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapExercise {

	/**
	 * 转换类型
	 */
	public static void main(String[] args) {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		
		Stream<String> stream = list.stream();
		Function<String, Integer> function = i -> Integer.valueOf(i); // Function<T, R>，转换成不同的类型
		List<Integer> resultList = stream.map(function).collect(Collectors.toList());
		
		System.out.println(resultList);
	}

}
