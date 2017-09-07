package com.nicchagil.consumer.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTopicMessageListenerA implements MessageListener {
	
	private static Logger logger = LoggerFactory.getLogger(MyTopicMessageListenerA.class);
    
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
        	logger.info("处理消息：{}", textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}