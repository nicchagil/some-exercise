package com.nicchagil.test;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ConsumerBoot {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-activemq-consumer.xml"});
        context.start();
        
        System.in.read();
    }

}