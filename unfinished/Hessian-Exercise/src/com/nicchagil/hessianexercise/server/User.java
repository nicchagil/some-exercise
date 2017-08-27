package com.nicchagil.hessianexercise.server;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

	private Integer id;
	private String name;
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", createTime=" + createTime + "]";
	}
	
}
