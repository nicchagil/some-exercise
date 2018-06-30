package com.nicchagil.test.jdk.lambda.collection;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class CollectionFilterTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/* 过滤 */
	@Test
	public void filterTestx1() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
		
        logger.info("小于等于3的记录 : {}", list.stream().filter(i -> i != null && i <= 3).collect(Collectors.toList())); // [1, 2, 3]
	}

}
