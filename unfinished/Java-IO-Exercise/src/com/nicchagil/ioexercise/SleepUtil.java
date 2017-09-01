package com.nicchagil.ioexercise;

import java.util.concurrent.TimeUnit;

public class SleepUtil {
	
	public static void sleep(long timeout) {
		try {
			TimeUnit.MILLISECONDS.sleep(timeout);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
