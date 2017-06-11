package com.nicchagil;

import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
	
	Logger logger = Logger.getLogger("MyBeanPostProcessor");
	
	public Object postProcessBeforeInitialization(Object obj, String arg1)
			throws BeansException {
		return obj;
	}

	public Object postProcessAfterInitialization(Object obj, String arg1)
			throws BeansException {
		if (obj != null) {
			logger.info("实例化：" + obj + ", Class：" + obj.getClass().getName());
		}
		
		return obj;
	}

}
