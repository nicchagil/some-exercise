package com.nicchagil.jdbcexercise;

import java.sql.Date;

public class ExecuteUpdateTest {

	public static void main(String[] args) throws Exception {
		int opsNum = JDBCTools.execute("update t_transaction set update_time = ? where id = ? ", new Object[] {new Date(new java.util.Date().getTime()), Integer.valueOf("1")});
		System.out.println("opsNum -> " + opsNum);
	}

}
