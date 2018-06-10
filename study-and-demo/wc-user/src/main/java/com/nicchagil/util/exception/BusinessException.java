package com.nicchagil.util.exception;

/**
 * 继承运行时异常的业务异常
 */
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private String code;
	
	private String msg;

	public String getCode() {
		return code;
	}

	public BusinessException setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public BusinessException setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	
}
