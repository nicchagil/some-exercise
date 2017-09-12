package com.nicchagil.exercise.zookeeperclient;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateTest {
	
	private static Logger logger = LoggerFactory.getLogger(CreateTest.class);
	
	/**
	 * 获取ZooKeeper连接
	 */
	public static void syncCreate() {
		ZooKeeper zookeeper = ConnectUtil.getZooKeeperConnection();
		
		try {
			String path = zookeeper.create("/my-zk-java-client-exercise-2", "hello".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			logger.info("path -> {}", path);
		} catch (KeeperException | InterruptedException e) {
			throw new RuntimeException("创建节点异常", e);
		}
	}
	
	public static void main(String[] args) {
		CreateTest.syncCreate();
	}
	
}
