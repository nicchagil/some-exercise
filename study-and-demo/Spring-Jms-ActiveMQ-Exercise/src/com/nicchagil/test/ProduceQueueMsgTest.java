package com.nicchagil.test;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicchagil.producer.ProducerService;


public class ProduceQueueMsgTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-activemq-producer.xml"});
        context.start();

        ProducerService producerService = (ProducerService)context.getBean("producerService");
        ActiveMQQueue activeMQQueue = (ActiveMQQueue)context.getBean("myQueue");
        producerService.sendQueueMessage(activeMQQueue, "hello.");
    }

}