package com.nicchagil.exercise.stream;

import java.util.Arrays;
import java.util.List;

public class MaxMin {

	/**
	 * 计算最大最小值
	 */
	public static void main(String[] args) {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		
		String max = list.stream().max(String::compareTo).get(); // 使用compareTo
		String min = list.stream().min((x, y) -> x.compareTo(y)).get(); // 手动调用compareTo
		
		System.out.println("max -> " + max);
		System.out.println("min -> " + min);
	}

}
