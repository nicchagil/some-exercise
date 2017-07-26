package com.nicchagil.messagelandinghandler.framework.constant;

public enum BusinessStatusEnum {
	
	UNDO("待处理"), EXCEPTION("处理异常"), COMPLATE("处理完成");
	
	private String code;

	private BusinessStatusEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
