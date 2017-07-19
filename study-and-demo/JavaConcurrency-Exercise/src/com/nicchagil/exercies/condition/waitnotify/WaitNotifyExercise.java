package com.nicchagil.exercies.condition.waitnotify;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.nicchagil.exercies.condition.ReentrantLockConditionExercise;

public class WaitNotifyExercise {
	
	/*
	 * 内部类，封装boolean（不直接用Boolean，因为唤醒前改变数值时使用“flag = true”会修改flag的对象，导致用没加锁的对象调用“notify()”从而报异常）
	 */
	static class MyFlag {
		private Boolean flag = false;

		public Boolean getFlag() {
			return flag;
		}

		public void setFlag(Boolean flag) {
			this.flag = flag;
		}
	}

	private static Logger logger = Logger.getLogger(ReentrantLockConditionExercise.class.getName());

	private static volatile MyFlag myFlag = new MyFlag();
	
	public static void main(String[] args) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (myFlag) {
					try {
						while (!myFlag.getFlag()) {
							logger.info(Thread.currentThread().getName() + "继续等待（条件还不成熟）");
							myFlag.wait(); // 等待其他线程改变当前线程需要的条件（会释放锁）
						}
						logger.info(Thread.currentThread().getName() + "继续业务（条件已成熟）");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (myFlag) {
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					logger.info(Thread.currentThread().getName() + "开始改变数据");
					myFlag.setFlag(true);
					myFlag.notify(); // 唤醒其他线程（释放锁）
					logger.info(Thread.currentThread().getName() + "改变数据完毕，并通知其它线");
				}
			}
		}).start();
		
	}

}
