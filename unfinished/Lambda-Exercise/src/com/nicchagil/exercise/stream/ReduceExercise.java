package com.nicchagil.exercise.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReduceExercise {

	private static Logger logger = LoggerFactory.getLogger(ReduceExercise.class);

	/**
	 * 计算1-10总和
	 */
	public static void main(String[] args) throws Exception {
		List<Integer> list = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }); // 声明集合

		BinaryOperator<Integer> binaryOperator = (x, y) -> {
			int temp = x + y;
			logger.info("temp sum -> {}", temp);
			return temp;
		};

		int sum = list.stream().reduce(binaryOperator).get();
		logger.info("sum -> {}", sum);
	}

}
