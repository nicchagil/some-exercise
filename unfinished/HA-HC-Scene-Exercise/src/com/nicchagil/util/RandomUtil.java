package com.nicchagil.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RandomUtil.class);
	
	/**
	 * 模拟numerator / denominator几率的异常
	 * @param numerator 分子
	 * @param denominator 分母
	 */
	public static void simulateException(int numerator, int denominator) {
		int random = new Random().nextInt(denominator);
		
		for (int i = 0; i < numerator; i++) {
			if (random == i) {
				LOGGER.error("模拟异常");
				throw new RuntimeException("模拟异常");
			}
		}
	}
	
	/**
	 * 睡眠不超过指定毫秒的时间（睡眠毫秒数随机）
	 * @param milliSecond 毫秒数
	 */
	public static void sleep(int milliSecond) {
		try {
			milliSecond = new Random().nextInt(milliSecond);
			LOGGER.info("睡眠：" + milliSecond + "毫秒");
			TimeUnit.MILLISECONDS.sleep(milliSecond);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
