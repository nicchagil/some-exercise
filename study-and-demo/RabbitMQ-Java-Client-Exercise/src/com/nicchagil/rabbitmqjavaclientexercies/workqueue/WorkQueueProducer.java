package com.nicchagil.rabbitmqjavaclientexercies.workqueue;

import com.nicchagil.rabbitmqjavaclientexercies.util.RabbitMQClientUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class WorkQueueProducer {

    private final static String QUEUE_NAME = "myWorkQueue";

    public static void main(String[] argv) throws Exception {
        
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = RabbitMQClientUtil.getConnectionFactory(); // 获取连接工厂
            connection = factory.newConnection(); // 创建连接
            channel = connection.createChannel(); // 创建信道

            channel.basicQos(1); // 预取数量，服务数量
            // 声明一个队列：名称、持久性的（重启仍存在此队列）、非私有的、非自动删除的
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            
            String message = "hello "; // 需发送的信息
            
            /* 发送消息，使用默认的direct交换器 */
            for (int i = 1; i <= 100; i++) {
            	String tempMsg = message + i;
            	channel.basicPublish("", QUEUE_NAME, null, tempMsg.getBytes());
                System.out.println("Send message -> " + tempMsg);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            channel.close(); // 关闭信道
            connection.close(); // 关闭连接
        }

    }

}
