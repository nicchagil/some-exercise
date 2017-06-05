package com.nicchgail;

import java.util.logging.Logger;

import com.nicchgail.rollbackrecord.RollbackRecord;
import com.nicchgail.rollbackrecord.RollbackRecordService;
import com.nicchgail.rollbackrecord.biz.RollbackRecordStatusEnum;

public abstract class AbstractDistributedInvoke<A, B, C> {
	
	private Logger logger = Logger.getLogger("AbstractDistributedInvoke");
	private RollbackRecordService rollbackRecordService = new RollbackRecordService();
	
	public abstract B doInvoke(A a);
	
	public abstract void doRollback(C c);
	
	public B invoke(A a, C c) {
		try {
			return this.doInvoke(a); // 实际业务接口调用
			
		} catch (Exception e) {
			/* 先插入需回滚记录 */
			logger.info("业务调用出现异常：" + e.getMessage() + "，需回滚，回滚参数：" + c);
			RollbackRecord rollbackRecord = new RollbackRecord(c.toString(), RollbackRecordStatusEnum.UN_ROLLBACK.getStatus());
			this.rollbackRecordService.insert(rollbackRecord);
			
			try {
				this.doRollback(c); // 调用回滚接口
				
				/* 回滚结果正确则更新需回滚记录的状态为已回滚 */
				rollbackRecord.setStatus(RollbackRecordStatusEnum.ROLLBACKED.getStatus());
				this.rollbackRecordService.update4Finish(rollbackRecord);
			} catch (Exception se) {
				logger.info("回滚调用出现异常：" + se.getMessage() + "（由于t_rollback_log已存在待回滚记录，此时无需处理即可）");
			}
			
		}
		
		return null;
	}

}
