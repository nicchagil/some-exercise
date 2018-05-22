package com.nicchagil.exercise.quickreturn.syncexecute;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Joiner;

public abstract class AbstractQuickReturnService<I, O, T> {
	
	/**
	 * 校验入参是否可以运行
	 * @param input 入参
	 * @return 错误信息集合
	 */
	public abstract List<String> validate(I input);
	
	/**
	 * 保存入参数据记录（到支持事务的关系型数据库），此记录的状态标识为“未完成处理”，保存的事务与当前请求在同一事务中
	 * @param input 入参
	 * @return 持久化的记录
	 */
	public abstract T quickSave(I input);
	
	/**
	 * 执行业务方法
	 * 方法的运行特征：
	 * 1、异步
	 * 2、事务是新启的事务
	 * @param input 入参      
	 * @return 出参
	 */
	@Async
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public O ayncExecute(I input, T task) {
		O output = this.doAyncExecute(input, task);
		
		/* 睡眠一段时间，避免当前事务与原事务更新一笔记录产生死锁 */
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		this.postUpdate(input, task); // 这里是新事务，要尝试是否与原事务（在此时还为结束）产生冲突、死锁
		
		return output;
	}
	
	/**
	 * 实际的异步执行方法（此方法内的内容通常为处理时间较长的业务，比如远程调用第三方接口）
	 * @param input 入参
	 * @return 出参
	 */
	public abstract O doAyncExecute(I input, T task);
	
	/**
	 * 更新此记录的处理状态
	 * @param input 入参
	 * @return 出参
	 */
	public abstract int postUpdate(I input, T task);
	
	/**
	 * 快速返回结果，再异步执行业务
	 * @param input 入参
	 * @return 出参
	 */
	public O quickReturnAndAsyncExecute(I input) {
		/* 校验 */
		List<String> validateResultList = this.validate(input);
		if (!CollectionUtils.isEmpty(validateResultList)) {
			throw new IllegalArgumentException("入参不符合要求：" + Joiner.on("、").join(validateResultList));
		}
		
		/* 实际处理业务前，先保存数据并置标志为“未完成”，后续是否完成业务再更新标志为“已完成” */
		T task = this.quickSave(input);
		Assert.notNull(task, "保存数据异常");
		
		/* 异步执行业务 */
		O output = this.ayncExecute(input, task);
		
		return output;
	}

}
