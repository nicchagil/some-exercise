package com.nicchagil.test.jdk.lambda.collection;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class CollectionMergeTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/* 合并 */
	@Test
	public void mergeTestx1() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        
        logger.info("小于等于3的记录 : {}", list.stream().filter(s -> s != null && s <= 3).collect(Collectors.toList())); // [1, 2, 3, 4, 5]
	}
	
	/* 合并 */
	@Test
	public void mergeTestx2() {
		List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
		
		Stream<Integer> stream = list.stream();
		Predicate<Integer> predicate = s -> s != null && s <= 3; // 断言，Predicate的boolean test(T t)
		
		logger.info("小于等于3的记录 : {}", stream.filter(predicate).collect(Collectors.toList())); // [[1, 2, 3], [4, 5]]
	}

}
