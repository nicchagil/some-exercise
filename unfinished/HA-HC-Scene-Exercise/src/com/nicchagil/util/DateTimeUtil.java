package com.nicchagil.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DateTimeUtil.class);
	
	public static String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss"; // 默认时间格式
	public static String PATTERN_DATE = "yyyy-MM-dd"; // 默认日期格式
	public static String PATTERN_DATE_TIME_NUMBER = "yyyyMMddHHmmss";
	
	/**
	 * 格式化成时间字符
	 * @param pattern 格式
	 * @param date 日期
	 * @return 时间字符
	 */
	public static String format(String pattern, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern); // SimpleDateFormat非线程安全，暂用此方式
		return sdf.format(date);
	}
	
	/**
	 * 转换为时间对象
	 * @param pattern 格式
	 * @param source 时间字符
	 * @return 时间对象
	 */
	public static Date parse(String pattern, String source) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern); // SimpleDateFormat非线程安全，暂用此方式
		try {
			Date date = sdf.parse(source);
			return date;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
}
