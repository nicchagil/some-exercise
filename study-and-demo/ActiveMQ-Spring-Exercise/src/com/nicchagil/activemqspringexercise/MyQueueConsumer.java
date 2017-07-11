package com.nicchagil.activemqspringexercise;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyQueueConsumer {
	
	@JmsListener(destination="myQueue")
    public void receiveMessage(String message){
		System.out.println("消费：" + message);
    }
    
}
