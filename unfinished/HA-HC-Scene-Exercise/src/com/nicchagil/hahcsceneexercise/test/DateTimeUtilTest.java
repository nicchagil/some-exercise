package com.nicchagil.hahcsceneexercise.test;

import java.util.Date;

import org.junit.Test;

import com.nicchagil.hahcsceneexercise.util.DateTimeUtil;

public class DateTimeUtilTest {
	
	@Test
	public void testFormat() {
		System.out.println(DateTimeUtil.format(DateTimeUtil.PATTERN_DATE_TIME, new Date()));
	}
	
	@Test
	public void testParse() {
		System.out.println(DateTimeUtil.parse(DateTimeUtil.PATTERN_DATE_TIME, "2017-01-02 01:02:03"));
	}
	
}
