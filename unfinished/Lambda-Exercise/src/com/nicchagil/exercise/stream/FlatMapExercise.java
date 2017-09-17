package com.nicchagil.exercise.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapExercise {

	/**
	 * 合并集合
	 */
	public static void main(String[] args) {
		List<String> list1 = Arrays.asList(new String[] {"1", "2", "3"});
		List<String> list2 = Arrays.asList(new String[] {"4", "5", "6"});
		
		Function<List<String>, Stream<String>> function = list -> list.stream(); // List<String>转换为Stream<String>
		List<String> allList = Stream.of(list1, list2).flatMap(function).collect(Collectors.toList()); // 合并多个集合
		System.out.println(allList);
	}

}
