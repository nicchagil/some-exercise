package com.nicchagil.test;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicchagil.producer.ProducerService;


public class ProduceTopicMsgTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-activemq-producer.xml"});
        context.start();

        ProducerService producerService = (ProducerService)context.getBean("producerService");
        ActiveMQTopic activeMQTopic = (ActiveMQTopic)context.getBean("myTopic");
        producerService.sendQueueMessage(activeMQTopic, "This is a topic");
    }

}