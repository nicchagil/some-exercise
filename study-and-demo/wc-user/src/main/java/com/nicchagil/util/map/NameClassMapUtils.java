package com.nicchagil.util.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

public class NameClassMapUtils {
	
	/**
	 * 根据Class集合返回<Class的简单名称,Class>的Map
	 */
	public static Map<String, Class<?>> generateClassNameAndClassMap(List<Class<?>> classList) {
		if (CollectionUtils.isEmpty(classList)) {
			return new HashMap<>();
		}
		
		Map<String, Class<?>> map = new HashMap<>();
		for (Class<?> clazz : classList) {
			map.put(clazz.getSimpleName(), clazz);
		}
		
		return map;
	}

}
