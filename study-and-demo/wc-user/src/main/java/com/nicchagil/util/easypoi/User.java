package com.nicchagil.util.easypoi;

import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class User {
	
	@Excel(name = "ID")
	private Integer id;
	
	@Excel(name = "姓名")
	private String name;
	
	@Excel(name = "出生年月", format = "yyyy-MM-dd HH:mm:ss")
	private Date birthday;
	
	@Excel(name = "状态", replace = { "生效_1", "失效_2" })
	private Integer status;

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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthday=" + birthday + ", status=" + status + "]";
	}
	
}
