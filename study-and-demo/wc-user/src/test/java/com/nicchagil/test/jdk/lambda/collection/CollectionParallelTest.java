package com.nicchagil.test.jdk.lambda.collection;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class CollectionParallelTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void parallelTestx1() {
		List<User> list = Lists.newArrayList();
		for (int i = 1; i <= 4; i++) {
			list.add(new User("Nick Huang " + i));
		}
		
		logger.info("开始设置时间 : {}", list);
		list.stream().parallel().forEach(i -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			i.setCreateTime(new Date());
			logger.info("正在设置 : {}", i.getName());
		});
		logger.info("结果 : {}", list);
	}

	@Test
	public void parallelTestx2() {
		List<User> list = Lists.newArrayList();
		for (int i = 1; i <= 5; i++) {
			list.add(new User("Nick Huang " + i));
		}

		logger.info("开始设置时间 : {}", list);
		list.stream().parallel().forEach(i -> {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			i.setCreateTime(new Date());
			logger.info("正在设置 : {}", i.getName());
		});
		logger.info("结果 : {}", list);
	}

	public class User {
		private String name;
		private Date createTime;
		
		public User(String name) {
			super();
			this.name = name;
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
			return "User [name=" + name + ", createTime=" + createTime + "]";
		}
	}

}
