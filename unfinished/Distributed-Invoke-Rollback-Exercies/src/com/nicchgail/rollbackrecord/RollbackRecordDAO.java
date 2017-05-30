package com.nicchgail.rollbackrecord;

import java.util.logging.Logger;

import com.othersystem.RandomExceptionUtil;

public class RollbackRecordDAO {
	
	Logger logger = Logger.getLogger("RollbackRecordDAO");
	
	public void insert(RollbackRecord record) {
		RandomExceptionUtil.randomException("记录回滚日志");
		logger.info("记录回滚日志成功");
	}
	
	public void update4Finish(RollbackRecord record) {
		RandomExceptionUtil.randomException("更新回滚日志");
		logger.info("更新回滚日志成功");
	}

}
