package com.nicchagil.util.excel.parse.controller.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

/**
 * 目前实现了“yyyy-MM-dd HH:mm:ss”格式的字符串转换为Date
 *
 */
public class DateConverter implements Converter {

	@Override
	public <T> T convert(Class<T> type, Object value) {
		if (value == null) {
			return null;
		}
		
		if (Date.class.getName().equals(type.getName())) {
			if (!(value instanceof String)) {
				return null;
			}
			
			/* 判空 */
			String valueString = value.toString();
			if (valueString.trim().length() == 0) {
				return null;
			}
			
			try {
				return (T) new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value.toString());
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		
		return null;
	}

}
