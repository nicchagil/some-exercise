package com.nicchagil.jdbcexercise;

import java.util.List;
import java.util.Map;

public class QueryTest {

	public static void main(String[] args) throws Exception {
		List<Map<String, String>> list = JDBCTools.query("select * from t_user limit 1");
		System.out.println(list);
	}

}
