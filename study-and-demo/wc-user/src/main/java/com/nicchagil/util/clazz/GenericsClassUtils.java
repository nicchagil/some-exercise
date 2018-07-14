package com.nicchagil.util.clazz;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericsClassUtils {
	
	private static Logger logger = LoggerFactory.getLogger(GenericsClassUtils.class);
	
	/**
	 * 获取指定类、指定顺序的泛型的类型
	 */
	public static Class<?> getGenericsTypeByClassAndIndex(Class<?> clazz, int index) {
		Type type = clazz.getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType)type;
		
		return (Class<?>)parameterizedType.getActualTypeArguments()[index];
	}
	
	@Test
	public void getGenericsTypeByClassAndIndexTest() {
		Class<?> clazz = GenericsClassUtils.getGenericsTypeByClassAndIndex(MyHttpReturn.class, 0);
		
		logger.info("clazz : {}", clazz);
	}

}
