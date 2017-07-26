package com.nicchgail.rollbackrecord.biz;

import java.util.logging.Logger;

import com.nicchgail.AbstractDistributedInvoke;
import com.othersystem.RandomExceptionUtil;

public class ConcreteDistributedInvoke extends AbstractDistributedInvoke<TransferVO, Boolean, TransferRollbackVO> {
	
	Logger logger = Logger.getLogger("ConcreteDistributedInvoke");

	@Override
	public Boolean doInvoke(TransferVO vo) {
		// 为了测试异常情况，这里总是抛出异常
		throw new RuntimeException("调用业务接口" + "发生异常，调用参数：" + vo);
		
		// this.logger.info("调用业务接口成功");
	}

	@Override
	public void doRollback(TransferRollbackVO vo) {
		RandomExceptionUtil.randomException("调用回滚接口", vo);
		this.logger.info("调用回滚接口成功");
	}

}
