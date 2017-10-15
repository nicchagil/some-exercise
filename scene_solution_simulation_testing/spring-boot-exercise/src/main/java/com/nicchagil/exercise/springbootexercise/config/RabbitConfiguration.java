package com.nicchagil.exercise.springbootexercise.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nicchagil.exercise.springbootexercise.properties.RabbitMqProperties;

@Configuration
public class RabbitConfiguration {
	
	@Autowired
	private RabbitMqProperties rabbitMqProperties;
	
	@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        
        connectionFactory.setHost(rabbitMqProperties.getHost());
        connectionFactory.setPort(rabbitMqProperties.getPort());
        connectionFactory.setUsername(rabbitMqProperties.getUsername());
        connectionFactory.setPassword(rabbitMqProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitMqProperties.getVirtualHost());
        
        return connectionFactory;
    }

	@Bean
	public Queue queue() {
		Queue queue =  new Queue("my-queue");
		return queue;
	}

}
