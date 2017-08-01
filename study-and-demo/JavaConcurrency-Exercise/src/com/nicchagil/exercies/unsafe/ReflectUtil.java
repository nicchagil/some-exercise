package com.nicchagil.exercies.unsafe;

import java.lang.reflect.Field;

public class ReflectUtil {
	
	/**
	 * 根据属性名称获取属性
	 * @param clazz 类
	 * @param fieldName 属性名称
	 * @return 属性
	 */
	public static Field getFiled(Class clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
