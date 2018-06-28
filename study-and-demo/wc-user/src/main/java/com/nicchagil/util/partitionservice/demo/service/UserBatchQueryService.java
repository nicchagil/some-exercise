package com.nicchagil.util.partitionservice.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicchagil.util.partitionservice.AbstractPartitionService;
import com.nicchagil.util.partitionservice.demo.service.UserService.User;

@Service
public class UserBatchQueryService extends AbstractPartitionService<Integer, User> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	/**
	 * 根据ID集合查询记录（实际底层是分组查询）
	 * @return
	 */
	public List<User> getByIdListWithPartitionQuery(List<Integer> inputList) {
		return this.service(inputList);
	}
	
	@Override
	public int getPartitionNum() {
		return 200;
	}

	@Override
	public List<User> doService(List<Integer> inputList) {
		return this.userService.getByIdList(inputList);
	}
	
}
