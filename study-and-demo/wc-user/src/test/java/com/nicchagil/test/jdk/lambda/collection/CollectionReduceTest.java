package com.nicchagil.test.jdk.lambda.collection;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class CollectionReduceTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void reduceTestx1() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		logger.info("结果 : {}", list.stream().reduce((x, y) -> x + y).get());
	}
	
	@Test
	public void parallelReduceTestx1() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		
		logger.info("结果 : {}", list.stream().parallel().reduce((x, y) -> x + y).get());
	}
	
}
