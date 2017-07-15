package com.nicchagil.activemqspringexercise;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MyQueueProducer {
	
	@Autowired
    private JmsTemplate jmsTemplate;
   
    @Autowired
    private Destination myQueue;
   
    public void sendMessage(final String message) {
        jmsTemplate.send(myQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message); 
            }
        });
    }
    
}
