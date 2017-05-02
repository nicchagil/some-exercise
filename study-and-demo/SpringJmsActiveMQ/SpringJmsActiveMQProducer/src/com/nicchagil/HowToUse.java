package com.nicchagil;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class HowToUse {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-activemq.xml"});
        context.start();

        ProducerService producerService = (ProducerService)context.getBean("producerService");
        ActiveMQQueue activeMQQueue = (ActiveMQQueue)context.getBean("testQueue");
        producerService.sendMessage(activeMQQueue, "hello.");
    }

}