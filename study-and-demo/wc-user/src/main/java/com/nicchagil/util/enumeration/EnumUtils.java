package com.nicchagil.util.enumeration;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.util.exception.BusinessException;
import com.nicchagil.util.exception.ExceptionCodeEnum;

public class EnumUtils {
	
	private Logger logger = LoggerFactory.getLogger(EnumUtils.class);
	
	/**
	 * 根据枚举的Class获取枚举的所有遍历值
	 */
	public static <T> T[] getEnumValuesByEnumClass(Class<T> clazz) {
		try {
			Method valuesMethod = clazz.getMethod("values", null);
			Object resultObj = valuesMethod.invoke(null, null);
			
			if (resultObj != null) {
				return (T[]) resultObj;
			}
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new BusinessException(ExceptionCodeEnum.MSG_00002);
		}
		
		return null;
	}
	
	@Test
	public void getEnumValuesByEnumClassTestx1() {
		StatusEnum[] objs = EnumUtils.getEnumValuesByEnumClass(StatusEnum.class);
		logger.info("objs : {}", Arrays.asList(objs));
	}

}
