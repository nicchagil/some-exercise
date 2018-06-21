package com.nicchagil.designpattern.strategy.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.nicchagil.util.spring.ApplicationContextUtils;

@Service
public class UseStrategyService {
	
	/**
	 * 使用策略例子
	 */
	public void useStrategyPatternDemo(SomethingEnum somethingEnum) {
		/* 维护策略的映射关系 */
		Map<SomethingEnum, String> strategyMap = new HashMap<>();
		strategyMap.put(SomethingEnum.APPLE, ApplicationContextUtils.getDefaultSpringBeanName(AppleStrategy.class));
		strategyMap.put(SomethingEnum.BALL, ApplicationContextUtils.getDefaultSpringBeanName(BallStrategy.class));
		
		/* 获取对应的策略 */
		String strategyBeanName = strategyMap.get(somethingEnum);
		Assert.notNull(strategyBeanName, "不存在此类型的策略");
		
		ISomethingStrategy somethingStrategy = ApplicationContextUtils.getBean(strategyBeanName, ISomethingStrategy.class);
		
		// 调用策略业务方法
		somethingStrategy.doSomething();
	}
	
	public enum SomethingEnum {
		APPLE, BALL;
	}

}
