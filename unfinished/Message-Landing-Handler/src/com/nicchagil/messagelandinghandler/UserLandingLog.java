package com.nicchagil.messagelandinghandler;

import java.util.Date;

public class UserLandingLog {

	private Integer id;
	private String message;
	private Integer status;
	private String exceptionMsg;
	private Date createTime;
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	@Override
	public String toString() {
		return "UserLandingLog [id=" + id + ", message=" + message + ", status=" + status + ", exceptionMsg="
				+ exceptionMsg + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
