package com.nicchagil.exercise.wcuser.thirdpartyframework.guava.listpartition;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class ListPartitionTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void partitionTest() {
		List<Integer> list = Lists.newArrayList();
		for (int i = 1; i <= 1004; i++) {
			list.add(i);
		}
		
		List<List<Integer>> partitionList = Lists.partition(list, 50);
		this.logger.info("partitionList : {}", partitionList);
	}

}
