package com.nicchagil.rabbitmqjavaclientexercies.util;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQClientUtil {
	
	/**
	 * 获取连接工厂
	 */
	public static ConnectionFactory getConnectionFactory() {
		/* 创建连接工厂 */
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("nick-huang.example");
        factory.setPort(1234);
        factory.setUsername("xxxxx");
        factory.setPassword("123456");
        
        return factory;
	}
	
}
