package com.nicchgail.rollbackrecord.biz;

public class TransferRollbackVO {
	
	private Integer id;
	private Integer amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "TransferRollbackVO [id=" + id + ", amount=" + amount + "]";
	}
	
}
