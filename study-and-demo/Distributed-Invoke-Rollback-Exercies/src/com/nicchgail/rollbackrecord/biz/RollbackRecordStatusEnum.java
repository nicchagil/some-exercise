package com.nicchgail.rollbackrecord.biz;

public enum RollbackRecordStatusEnum {
	
	UN_ROLLBACK(1),
	ROLLBACKED(2);
	
	private Integer status;

	private RollbackRecordStatusEnum(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
