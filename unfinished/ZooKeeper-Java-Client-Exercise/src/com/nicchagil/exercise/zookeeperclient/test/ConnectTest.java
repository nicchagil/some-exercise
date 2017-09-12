package com.nicchagil.exercise.zookeeperclient.test;

import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.exercise.zookeeperclient.ConnectUtil;

public class ConnectTest {
	
	private static Logger logger = LoggerFactory.getLogger(ConnectTest.class);
	
	public static void main(String[] args) {
		ZooKeeper zookeeper = ConnectUtil.getZooKeeperConnection();
		logger.info("zookeeper -> {}", zookeeper);
	}

}
