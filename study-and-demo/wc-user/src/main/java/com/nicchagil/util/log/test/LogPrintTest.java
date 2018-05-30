package com.nicchagil.util.log.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogPrintTest {
	
	private static Logger logger = LoggerFactory.getLogger(LogPrintTest.class);	
	
	/**
	 * 在指定包打印日志，以测试此日志是不是定位到指定日志文件
	 */
	public static void printLogInSpecialPackage() {
		logger.info("在指定包打印日志，以测试此日志是不是定位到指定日志文件");
	}

}
