package com.nicchagil.hahcsceneexercise.util.jdbc;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryTest {
	
	private static Logger LOG = LoggerFactory.getLogger(QueryTest.class);

	@Test
	public void test() {
		List<Map<String, String>> list = JDBCUtil.query("select * from t_user limit 1");
		LOG.info("list -> " + list);
	}

}
