package com.nicchagil.util.string;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WcStringUtils {
	
	private static Logger logger = LoggerFactory.getLogger(WcStringUtils.class);
	
	/**
	 * 首字符转小写
	 */
	public static String toLowerCaseForFirstChar(String s) {
		if (s == null || s.trim().length() == 0) {
			return s;
		}
		
		String result = s.substring(0, 1).toLowerCase() + s.substring(1);
		return result;
	}
	
	@Test
	public void toLowerCaseForFirstCharx1() {
		logger.info("1.{}", WcStringUtils.toLowerCaseForFirstChar(null));
		logger.info("2.{}", WcStringUtils.toLowerCaseForFirstChar("W"));
		logger.info("3.{}", WcStringUtils.toLowerCaseForFirstChar("WcBean"));
	}

}
