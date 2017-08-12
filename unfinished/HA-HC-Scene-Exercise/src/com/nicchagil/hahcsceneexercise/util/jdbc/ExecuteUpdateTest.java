package com.nicchagil.hahcsceneexercise.util.jdbc;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteUpdateTest {
	
	private static Logger LOG = LoggerFactory.getLogger(QueryTest.class);

	public static void main(String[] args) throws Exception {
		int opsNum = JDBCUtil.execute("update t_transaction set update_time = ? where id = ? ", new Object[] {new Date(new java.util.Date().getTime()), Integer.valueOf("1")});
		LOG.info("opsNum -> " + opsNum);
	}

}
