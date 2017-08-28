package com.nicchagil.rabbitmqjavaclientexercies.fanout;

import com.nicchagil.rabbitmqjavaclientexercies.util.RabbitMQClientUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class FanoutProducer {

    private final static String EXCHANGE_NAME = "myFanout";

    public static void main(String[] argv) throws Exception {
        
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = RabbitMQClientUtil.getConnectionFactory(); // 获取连接工厂
            connection = factory.newConnection(); // 创建连接
            channel = connection.createChannel(); // 创建信道

            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
            
            String message = "hello world..."; // 需发送的信息
            
            /* 发送消息 */
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println("Send message -> " + message);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            channel.close(); // 关闭信道
            connection.close(); // 关闭连接
        }

    }

}
