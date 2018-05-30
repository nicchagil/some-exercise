package com.nicchagil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.nicchagil.util.log.test.LogPrintTest;

@SpringBootApplication
@EnableScheduling
public class WcUserApplication {
	
	private static Logger logger = LoggerFactory.getLogger(WcUserApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(WcUserApplication.class, args);
		
		/* 以下为调试日志打印 */
		logger.debug("启动完毕...");
		logger.info("启动完毕...");
		logger.error("启动完毕...（测试错误日志打印）");
		LogPrintTest.printLogInSpecialPackage();
	}
}
