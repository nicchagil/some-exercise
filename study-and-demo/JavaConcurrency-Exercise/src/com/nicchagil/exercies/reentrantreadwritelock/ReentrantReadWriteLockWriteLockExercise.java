package com.nicchagil.exercies.reentrantreadwritelock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Logger;

public class ReentrantReadWriteLockWriteLockExercise {
	
	private static Logger logger = Logger.getLogger(ReentrantReadWriteLockWriteLockExercise.class.getName());
	
	private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

	public static void main(String[] args) {
		
		/* 先加写锁 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				reentrantReadWriteLock.writeLock().lock();
				logger.info(Thread.currentThread().getName() + "加写锁");
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					reentrantReadWriteLock.writeLock().unlock();
					logger.info(Thread.currentThread().getName() + "解写锁");
				}
			}
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		/* 然后加写锁 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				reentrantReadWriteLock.writeLock().lock();
				logger.info(Thread.currentThread().getName() + "加写锁");
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					reentrantReadWriteLock.writeLock().unlock();
					logger.info(Thread.currentThread().getName() + "解写锁");
				}
			}
		}).start();
		
		/* 然后加读锁 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				reentrantReadWriteLock.readLock().lock();
				logger.info(Thread.currentThread().getName() + "加读锁");
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					reentrantReadWriteLock.readLock().unlock();
					logger.info(Thread.currentThread().getName() + "解读锁");
				}
			}
		}).start();
		
	}

}
