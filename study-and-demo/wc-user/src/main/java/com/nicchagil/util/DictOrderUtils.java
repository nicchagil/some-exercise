package com.nicchagil.util;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;

public class DictOrderUtils {
	
	/**
	 * 集合按字典序排序
	 */
	public static List<String> dictOrder(List<String> sourceList) {
		if (CollectionUtils.isEmpty(sourceList)) {
			return sourceList;
		}
		
		/* 排字典序 */
		Collections.sort(sourceList);
		/*
		sourceList.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		*/
		
		return sourceList;
	}

	public static void main(String[] args) {
		List<String> list = Lists.newArrayList("d9dhff7fkf8f62h48f7gjfg", "1527002495", "167941888");
		System.out.println(DictOrderUtils.dictOrder(list));
	}

}
