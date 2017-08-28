package com.nicchagil.springbootexercise.controller;

public class Page {

	private Integer page = 1;
	private Integer pageSize = 20;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "Page [page=" + page + ", pageSize=" + pageSize + "]";
	}
	
}
