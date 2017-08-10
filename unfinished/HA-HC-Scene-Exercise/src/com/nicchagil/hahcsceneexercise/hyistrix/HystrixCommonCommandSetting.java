package com.nicchagil.hahcsceneexercise.hyistrix;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class HystrixCommonCommandSetting {
	
	public static HystrixThreadPoolProperties.Setter getThreadPoolPropertiesSetting() {
		/* 核心线程池大小为5、线程池空闲线程生存时间为5分钟、等待队列为无限、动态队列大小 */
		HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter().withCoreSize(5)
				.withKeepAliveTimeMinutes(5).withMaximumSize(Integer.MAX_VALUE).withQueueSizeRejectionThreshold(10000);
		
		return threadPoolProperties;
	}
	
	public static HystrixCommandProperties.Setter getCommandPropertiesSetting() {
		/* 线程隔离 */
		HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
				.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD);
		return commandProperties;
	}
	
}
