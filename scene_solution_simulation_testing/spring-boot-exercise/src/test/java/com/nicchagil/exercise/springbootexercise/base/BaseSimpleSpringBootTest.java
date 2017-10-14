package com.nicchagil.exercise.springbootexercise.base;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 简单的基础测试类（集成Spring容器环境、Logger）
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class BaseSimpleSpringBootTest {
	
	protected Logger logger = LoggerFactory.getLogger(BaseSimpleSpringBootTest.class);
	
}
