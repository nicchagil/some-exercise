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
			this.doInvoke(); // 实际业务接口调用
		} catch (Exception e) {
			logger.info("业务调用出现异常，需回滚：" + e.getMessage());
			this.rollbackRecordService.insert(new RollbackRecord()); // 先插入需回滚记录
			
			try {
				this.doRollback();
				this.rollbackRecordService.update4Finish(new RollbackRecord()); // 回滚结果正确则更新需回滚记录的状态为已回滚
			} catch (Exception se) {
				logger.info("回滚调用失败（由于t_rollback_log已存在待回滚记录，此时无需处理即可）");
			}
			
		}
		
	}

}
