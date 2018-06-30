package com.nicchagil.test.jdk.lambda.collection;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CollectionChangeTypeTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/* 转换类型 */
	@Test
	public void filterTestx1() {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
		
		Stream<String> stream = list.stream();
		Function<String, Integer> function = i -> Integer.valueOf(i); // Function<T, R>，转换成不同的类型
		List<Integer> resultList = stream.map(function).collect(Collectors.toList());
		
		logger.info("resultList : {}", resultList);
	}
	
	/* 转换类型（简化版） */
	@Test
	public void filterTestx2() {
		List<String> list = Arrays.asList(new String[] {"1", "2", "3"});
        
        List<Integer> resultList = list.stream().map(i -> Integer.valueOf(i)).collect(Collectors.toList());
        logger.info("resultList : {}", resultList);
	}

}
