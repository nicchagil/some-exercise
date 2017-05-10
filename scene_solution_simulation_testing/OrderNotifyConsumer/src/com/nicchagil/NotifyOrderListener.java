package com.nicchagil;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class NotifyOrderListener implements MessageListener {
    
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
        	NotifyConsumer.consumer(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
}