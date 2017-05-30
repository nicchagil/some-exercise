package com.nicchgail.rollbackrecord;

import java.util.logging.Logger;

public class RollbackRecordService {
	
	private Logger logger = Logger.getLogger("RollbackRecordService");
	private RollbackRecordDAO rollbackRecordDAO = new RollbackRecordDAO();
	
	public void insert(RollbackRecord record) {
		try {
			this.rollbackRecordDAO.insert(record);
		} catch (Exception e) {
			// 如果插入回滚记录失败，则记录日志，后续获取日志回滚
			logger.info("插入回滚记录失败，则记录日志，后续获取日志回滚");
		}
	}
	
	public void update4Finish(RollbackRecord record) {
		this.rollbackRecordDAO.update4Finish(record); // 回滚接口必须是幂等的
	}

}
