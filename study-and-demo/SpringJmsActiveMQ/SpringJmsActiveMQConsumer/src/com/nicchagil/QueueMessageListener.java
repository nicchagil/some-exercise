package com.nicchagil;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class QueueMessageListener implements MessageListener {
    
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("处理消息：" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}