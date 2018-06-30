package com.nicchagil.test.jdk.lambda.collection;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class CollectionStreamTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void filterTestx1() {
		List<Integer> list1 = Lists.newArrayList(1, 2, 3);
		List<Integer> list2 = Lists.newArrayList(4, 5);
		
		logger.info("合并记录 : {}", Stream.of(list1, list2).collect(Collectors.toList()));
	}
	
	@Test
	public void filterTestx2() {
		List<Integer> list1 = Lists.newArrayList(1, 2, 3);
		List<Integer> list2 = Lists.newArrayList(4, 5);
		
		logger.info("合并记录 : {}", Stream.of(list1, list2).flatMap(list -> list.stream()).collect(Collectors.toList()));
	}

}
