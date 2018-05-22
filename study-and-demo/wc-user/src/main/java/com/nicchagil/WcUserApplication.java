package com.nicchagil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.nicchagil.service.WechatVerifyService;

@SpringBootApplication
public class WcUserApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(WcUserApplication.class, args);
	}
}
