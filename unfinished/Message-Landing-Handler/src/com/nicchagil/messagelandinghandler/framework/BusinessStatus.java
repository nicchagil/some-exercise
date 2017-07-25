package com.nicchagil.messagelandinghandler.framework;

public enum BusinessStatus {
	
	UNDO(0), EXCEPTION(1), COMPLATE(2);
	
	private Integer code;

	private BusinessStatus(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
