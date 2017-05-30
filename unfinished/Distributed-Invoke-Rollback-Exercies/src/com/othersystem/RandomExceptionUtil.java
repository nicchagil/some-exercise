package com.othersystem;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RandomExceptionUtil {
	
	public static void randomException(String opsName) {
		int i = new Random().nextInt(0);
		
		if (i == 0) {
			throw new RuntimeException(opsName + "假设异常");
		}
		
		try {
			TimeUnit.SECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
