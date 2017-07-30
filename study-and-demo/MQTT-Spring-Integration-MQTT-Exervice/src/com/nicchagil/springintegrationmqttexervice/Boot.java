package com.nicchagil.springintegrationmqttexervice;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Boot {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-integration-mqtt-consumer.xml"});
        context.start();
        
        System.in.read();
    }

}