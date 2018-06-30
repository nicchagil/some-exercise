package com.nicchagil.test.jdk.lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class UseFunctionalInterfaceTest {

	/* 传统方式 */
	@Test
	public void testx0() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("hello world");
			}
		}).start();
	}

	/* 无参数、单语句方法体 */
	@Test
	public void testx1() {
		Runnable r = () -> System.out.print("hello world");
		new Thread(r).start();
	}

	@Test
	public void testx2() {
		new Thread(() -> System.out.print("hello world")).start();
	}

	/* 无参数、多语句方法体 */
	@Test
	public void testx3() {
		Runnable r = () -> {
			System.out.print("hello world x1");
			System.out.print("hello world x2");
		};
		new Thread(r).start();
	}

	/* 多参数、多语句方法体、有返回值 */
	@Test
	public void testx4() {
		Comparator<Integer> c = (x, y) -> {
			int temp = x - y;
			return temp;
		};

		List<Integer> list = Lists.newArrayList(2, 1, 3);
		Collections.sort(list, c);

		System.out.println(list);
	}

	/* 多参数、多语句方法体、有返回值（简化版） */
	@Test
	public void testx5() {
		List<Integer> list = Lists.newArrayList(2, 1, 3);

		Collections.sort(list, (x, y) -> {
			return x - y;
		});

		System.out.println(list);
	}

}
