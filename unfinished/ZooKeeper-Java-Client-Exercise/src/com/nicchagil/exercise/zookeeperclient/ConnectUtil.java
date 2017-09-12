package com.nicchagil.exercise.zookeeperclient;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectUtil {
	
	private static Logger logger = LoggerFactory.getLogger(ConnectUtil.class);
	
	public static CountDownLatch connectCountDownLatch = new CountDownLatch(1);
	
	/**
	 * 获取ZooKeeper连接
	 */
	public static ZooKeeper getZooKeeperConnection() {
		try {
			ZooKeeper zookeeper = new ZooKeeper("192.168.1.101:2181", 5000, new ConnectUtil.ConnectWatcher()); // 异步创建连接
			connectCountDownLatch.await();
			return zookeeper;
		} catch (IOException e) {
			throw new RuntimeException("连接失败", e);
		} catch (InterruptedException e) {
			throw new RuntimeException("线程被中断", e);
		}
	}
	
	/**
	 * 连接观察者
	 */
	public static class ConnectWatcher implements Watcher {
		private Logger logger = LoggerFactory.getLogger(this.getClass());

		@Override
		public void process(WatchedEvent event) {
			this.logger.info("接收到事件 -> {}", event);
			
			if (KeeperState.SyncConnected == event.getState()) {
				ConnectUtil.connectCountDownLatch.countDown();
			}
		}

	}

}
