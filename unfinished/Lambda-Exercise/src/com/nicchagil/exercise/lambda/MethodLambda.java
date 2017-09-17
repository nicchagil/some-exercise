package com.nicchagil.exercise.lambda;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MethodLambda {

	public static void main(String[] args) {
		traditional();
		lambda();
	}

	public static void traditional() {
		String[] datas = new String[] { "1", "2", "3" };
		Arrays.sort(datas);

		Stream.of(datas).forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
	}

	public static void lambda() {
		String[] datas = new String[] { "1", "2", "3" };
		Arrays.sort(datas, (x, y) -> Integer.compare(x.length(), y.length()));

		Stream.of(datas).forEach(param -> System.out.println(param));
	}

}
