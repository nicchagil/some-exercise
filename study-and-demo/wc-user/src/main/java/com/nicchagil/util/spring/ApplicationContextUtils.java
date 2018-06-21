package com.nicchagil.util.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.nicchagil.util.string.WcStringUtils;

@Component
public class ApplicationContextUtils implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		applicationContext = ac;
	}
	
	/**
	 * 获取应用上下文
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	/**
	 * 根据类型获取Bean
	 */
	public static <T> T getBean(Class<T> clazz) {
		return ApplicationContextUtils.getApplicationContext().getBean(clazz);
	}
	
	/**
	 * 根据名字、类型获取Bean
	 */
	public static <T> T getBean(String name, Class<T> clazz) {
		return ApplicationContextUtils.getApplicationContext().getBean(name, clazz);
	}
	
	/**
	 * 根据Spring的默认的Bean的名字获取指定类型的Bean
	 */
	public static <T> T getBeanByDefaultSpringBeanName(Class<T> clazz) {
		String defaultSpringBeanName = ApplicationContextUtils.getDefaultSpringBeanName(clazz);
		return ApplicationContextUtils.getApplicationContext().getBean(defaultSpringBeanName, clazz);
	}
	
	/**
	 * 获取Spring的默认的Bean的名字
	 */
	public static <T> String getDefaultSpringBeanName(Class<T> clazz) {
		String simpleClassName = clazz.getSimpleName();
		String defaultSpringBeanName = WcStringUtils.toLowerCaseForFirstChar(simpleClassName);
		return defaultSpringBeanName;
	}

}
