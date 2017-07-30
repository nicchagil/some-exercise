package com.nicchagil.springintegrationmqttexervice;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

public class Producer {
	
	private static Logger logger = Logger.getLogger("Producer");

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-integration-mqtt-producer.xml"});
		MqttPahoMessageHandler mqttPahoMessageHandler = context.getBean("mqttPahoMessageHandler", MqttPahoMessageHandler.class);
		
		for (int i = 1; i <= 3 ; i++) {
			send(mqttPahoMessageHandler, "hello world " + i);
			
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void send(MqttPahoMessageHandler mqttPahoMessageHandler, String payload) {
		Message<String> message = MessageBuilder.withPayload(payload)
				.setHeader(MqttHeaders.TOPIC, "myqueue")
				.setHeader(MqttHeaders.RETAINED, true)
				// .setHeader(MqttHeaders.DUPLICATE, true)
				.build();
		mqttPahoMessageHandler.handleMessage(message);
		logger.info("发送成功：" + message);
	}

}
