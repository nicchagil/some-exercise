package com.nicchgail.rollbackrecord;

public class RollbackRecord {
	
	private Integer id;
	private String json; // 传入回滚接口的JSON
	private Integer status; // 状态
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public RollbackRecord(String json, Integer status) {
		super();
		this.json = json;
		this.status = status;
	}

	public RollbackRecord(Integer id, String json, Integer status) {
		super();
		this.id = id;
		this.json = json;
		this.status = status;
	}

	@Override
	public String toString() {
		return "RollbackRecord [id=" + id + ", json=" + json + ", status=" + status + "]";
	}
	
}
