package com.nicchgail;

import java.util.logging.Logger;

import com.nicchgail.rollbackrecord.RollbackRecord;
import com.nicchgail.rollbackrecord.RollbackRecordService;

public abstract class AbstractDistributedInvoke {
	
	private Logger logger = Logger.getLogger("AbstractDistributedInvoke");
	private RollbackRecordService rollbackRecordService = new RollbackRecordService();
	
	public abstract void doInvoke();
	
	public abstract void doRollback();
	
	public void invoke() {
		try {
			this.doInvoke();
		} catch (Exception e) {
			logger.info("业务调用失败：" + e.getMessage() + "，需回滚");
			this.rollbackRecordService.insert(new RollbackRecord()); // 先插入需回滚记录
			
			try {
				this.doRollback();
				this.rollbackRecordService.update4Finish(new RollbackRecord()); // 回滚结果正确则更新需回滚记录的状态为已回滚
			} catch (Exception se) {
				logger.info("回滚失败：后续根据需回滚记录重试回滚");
			}
			
		}
		
	}

}
