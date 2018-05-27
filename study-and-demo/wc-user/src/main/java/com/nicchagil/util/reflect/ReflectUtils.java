package com.nicchagil.util.reflect;

import java.lang.reflect.Field;

import org.springframework.util.Assert;

public class ReflectUtils {
	
	/**
	 * 根据名称获取属性对象
	 */
	public static Field getFieldByName(Object obj, String fieldName) {
		Assert.notNull(obj, "反射获取属性值，入参对象不可为空");
		Assert.hasText(fieldName, "反射获取属性值，属性名不可为空");
		
		Class<? extends Object> clazz = obj.getClass();
		
		/* 获取属性集合（不使用getDeclaredFields(String)为了避免检查时异常） */
		Field[] fields = clazz.getDeclaredFields();
		if (fields == null || fields.length == 0) {
			return null;
		}
		
		/* 遍历获取属性 */
		for (Field f : fields) {
			if (fieldName.equals(f.getName())) {
				return f;
			}
		}
		
		return null;
	}
	
	/**
	 * 获取属性值（JDK9以后有可能不能用此方法获取属性值）
	 */
	public static Object getFieldValue(Object obj, String fieldName) {
		Assert.notNull(obj, "反射获取属性值，入参对象不可为空");
		Assert.hasText(fieldName, "反射获取属性值，属性名不可为空");
		
		Field f = ReflectUtils.getFieldByName(obj, fieldName);
		if (f == null) {
			return null;
		}
		
		/* JDK9以后有可能不能用此方法获取属性值 */
		f.setAccessible(true);
		try {
			return f.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
