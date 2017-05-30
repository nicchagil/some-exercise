package com.nicchgail.rollbackrecord;

import java.util.logging.Logger;

import com.nicchgail.AbstractDistributedInvoke;
import com.othersystem.RandomExceptionUtil;

public class ConcreteDistributedInvoke extends AbstractDistributedInvoke {
	
	Logger logger = Logger.getLogger("ConcreteDistributedInvoke");

	@Override
	public void doInvoke() {
		// RandomExceptionUtil.randomException("调用业务接口");
		throw new RuntimeException("调用业务接口" + "假设异常");
		// this.logger.info("调用业务接口成功");
	}

	@Override
	public void doRollback() {
		RandomExceptionUtil.randomException("调用回滚接口");
		this.logger.info("调用回滚接口成功");
	}

}
