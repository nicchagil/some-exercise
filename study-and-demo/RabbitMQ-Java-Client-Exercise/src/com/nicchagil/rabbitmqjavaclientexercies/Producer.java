package com.nicchagil.rabbitmqjavaclientexercies;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

    private final static String QUEUE_NAME = "hello world";

    public static void main(String[] argv) throws Exception {
        
        Connection connection = null;
        Channel channel = null;
        try {
            /* 创建连接工厂 */
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("nick-huang.example");
            factory.setPort(5672);
            factory.setUsername("nicchagil");
            factory.setPassword("123456");
            /* 创建连接 */
            connection = factory.newConnection();
            /* 创建信道 */
            channel = connection.createChannel();

            // 声明一个队列：名称、持久性的（重启仍存在此队列）、非私有的、非自动删除的
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            
            String message = "hello world..."; // 需发送的信息
            
            /* 发送消息，使用默认的direct交换器 */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Send message -> " + message);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            /* 关闭连接、通道 */
            channel.close();
            connection.close();
            System.out.println("Closed the channel and conn.");
        }

    }

}
