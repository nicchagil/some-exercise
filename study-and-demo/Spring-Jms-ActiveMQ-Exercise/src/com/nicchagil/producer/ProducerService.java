package com.nicchagil.producer;
import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    
    @Resource(name="jmsQueueTemplate")
    private JmsTemplate jmsQueueTemplate;
    
    @Resource(name="jmsTopicTemplate")
    private JmsTemplate jmsTopicTemplate;
    
    /**
     * 发送队列消息
     * @param destination 目的地
     * @param message 消息
     */
    public void sendQueueMessage(Destination destination, final String message) {  
        jmsQueueTemplate.send(destination, new MessageCreator() {  
            public Message createMessage(Session session) throws JMSException {  
                return session.createTextMessage(message);  
            }  
        });  
    }
    
    /**
     * 发送主题消息
     * @param destination 目的地
     * @param message 消息
     */
    public void sendTopicMessage(Destination destination, final String message) {  
    	jmsTopicTemplate.send(destination, new MessageCreator() {  
            public Message createMessage(Session session) throws JMSException {  
                return session.createTextMessage(message);  
            }  
        });  
    }

}