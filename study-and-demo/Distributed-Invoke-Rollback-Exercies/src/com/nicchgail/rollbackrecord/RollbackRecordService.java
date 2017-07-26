package com.nicchgail.rollbackrecord;

import java.util.logging.Logger;

public class RollbackRecordService {
	
	private Logger logger = Logger.getLogger("RollbackRecordService");
	private RollbackRecordDAO rollbackRecordDAO = new RollbackRecordDAO();
	
	public void insert(RollbackRecord record) {
		try {
			this.rollbackRecordDAO.insert(record);
		} catch (RuntimeException e) {
			// 如果插入回滚记录失败，则记录日志，后续获取日志回滚
			logger.info("插入t_rollback_log失败，记录本地日志，后续由定时任务将本地日志转换为t_rollback_log的记录，回滚参数：" + record);
			throw e;
		}
	}
	
	public void update4Finish(RollbackRecord record) {
		this.rollbackRecordDAO.update4Finish(record); // 回滚接口必须是幂等的
	}

}
