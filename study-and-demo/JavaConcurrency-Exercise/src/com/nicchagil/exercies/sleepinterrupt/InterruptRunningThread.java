package com.nicchagil.exercies.sleepinterrupt;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * 没效果，待修改 TODO
 */
public class InterruptRunningThread {
	
	private static Logger logger = Logger.getLogger(InterruptRunningThread.class.getName());
	
	public static boolean mySwitch = true;

	public static void main(String[] args) throws InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (mySwitch) {
					logger.info(Thread.currentThread() + "is runing.");
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				/* 睡眠1S后关闭开关 */
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				mySwitch = false;
			}
		}).start();
		
		TimeUnit.SECONDS.sleep(10); // 让程序持续5S以观察结果
	}
	
}
