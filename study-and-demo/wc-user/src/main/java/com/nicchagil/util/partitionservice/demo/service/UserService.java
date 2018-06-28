package com.nicchagil.util.partitionservice.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<User> getByIdList(List<Integer> idList) {
		this.logger.info("模拟查询：{}", idList);
		return Lists.newArrayList();
	}

	public static class User {
		private Integer id;
		private String name;
	}
	
}
