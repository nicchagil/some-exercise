package com.nicchagil.util.partitionservice;

import java.util.List;

import org.apache.shiro.util.CollectionUtils;

import com.google.common.collect.Lists;

public abstract class AbstractPartitionService <I, O> {
	
	/**
	 * 默认分组数量为100，欢迎覆盖
	 */
	public int getPartitionNum() {
		return 100;
	}
	
	/**
	 * 总服务方法
	 */
	public List<O> service(List<I> inputList) {
		if (CollectionUtils.isEmpty(inputList)) {
			return null;
		}
		
		// 获取分组数量
		int partitionNum = this.getPartitionNum();
		// 包装好的入参集合
		List<List<I>> packedInputList = Lists.partition(inputList, partitionNum);
		
		// 出参总集合
		List<O> totalOutputList = Lists.newLinkedList();
		
		for (int i = 0; i < packedInputList.size(); i++) {
			List<I> partitionInputList = packedInputList.get(i);
			
			List<O> partitionOutputList = this.doService(partitionInputList);
			
			if (!CollectionUtils.isEmpty(partitionOutputList)) {
				totalOutputList.addAll(partitionOutputList);
			}
		}
		
		if (CollectionUtils.isEmpty(totalOutputList)) {
			return null;
		}
		
		return totalOutputList;
	}
	
	/**
	 * 实际的查询方法
	 */
	public abstract List<O> doService(List<I> inputList);

}
