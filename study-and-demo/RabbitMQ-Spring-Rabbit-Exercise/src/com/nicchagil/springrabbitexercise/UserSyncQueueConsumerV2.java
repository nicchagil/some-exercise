package com.nicchagil.springrabbitexercise;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;

@Service
public class UserSyncQueueConsumerV2 implements ChannelAwareMessageListener {

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		try {
			MessageProperties messageProperties = message.getMessageProperties();
			byte[] bytes = message.getBody();
			
			System.out.println("messageProperties : " + messageProperties);
			System.out.println("body : " + new String(bytes));
			
			if (new Random().nextInt(1) == 0) {
				// 模拟宕机
				TimeUnit.MINUTES.sleep(5);
				
				// 模拟异常
				/*
				throw new RuntimeException();
				*/
			}
			
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // 确认此消息，并只确认此消息（false为只确认此消息）
			System.out.println("Acked");
		} catch (Exception e) {
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); // 确认此消息处理异常
			System.out.println("Nacked");
		}
		
	}

}
