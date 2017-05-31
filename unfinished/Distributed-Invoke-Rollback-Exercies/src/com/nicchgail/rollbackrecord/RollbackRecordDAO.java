package com.nicchgail.rollbackrecord;

import java.util.logging.Logger;

import com.othersystem.RandomExceptionUtil;

public class RollbackRecordDAO {
	
	Logger logger = Logger.getLogger("RollbackRecordDAO");
	
	public void insert(RollbackRecord record) {
		RandomExceptionUtil.randomException("插入t_rollback_log（回滚状态为待回滚）");
		logger.info("插入t_rollback_log成功（回滚状态为待回滚）");
	}
	
	public void update4Finish(RollbackRecord record) {
		RandomExceptionUtil.randomException("更新t_rollback_log（回滚状态为已回滚）");
		logger.info("更新t_rollback_log成功（回滚状态为已回滚）");
	}

}
