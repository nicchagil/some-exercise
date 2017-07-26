package com.nicchagil.messagelandinghandler.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象远程操作处理器
 */
public abstract class AbstractRemoteOpsHandler<T> {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 远程操作
	 * @param t 对象
	 */
	public T ops(T t) {
		T r = null;
		try {
			r = this.doOps(t); // 调用实际的远程操作方法
		} catch (Exception e) {
			this.logger.error(this.getPrintMsgIfRemoteOpsException(), t); // 当远程操作方法出现异常，打印到本地日志
			throw new RuntimeException(this.getPrintMsgIfRemoteOpsException() + t); // 抛出异常，避免后续业务
		}
		
		this.logger.info(this.getPrintMsgIfRemoteOpsComplete(), t);
		return r;
	}
	
	/**
	 * 实际的远程操作方法
	 * @param t 对象
	 */
	public abstract T doOps(T t);
	
	/**
	 * 获取远程操作出现异常时打印到本地日志的文案
	 * @return 远程操作出现异常时打印到本地日志的文案
	 */
	public abstract String getPrintMsgIfRemoteOpsException();
	
	/**
	 * 获取远程操作成功完成时打印到本地日志的文案
	 * @return 远程操作成功完成时打印到本地日志的文案
	 */
	public String getPrintMsgIfRemoteOpsComplete() {
		return "Ops complate : {}";
	}
	
}
