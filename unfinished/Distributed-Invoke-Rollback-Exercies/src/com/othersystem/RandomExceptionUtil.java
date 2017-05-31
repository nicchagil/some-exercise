package com.othersystem;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomExceptionUtil {
	
	/**
	 * 模拟不稳定的接口，有一定的时间成本，有一定几率失败
	 */
	public static void randomException(String opsName) {
		int i = new Random().nextInt(2);
		
		if (i == 0) {
			throw new RuntimeException(opsName + "发生异常");
		}
		
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
