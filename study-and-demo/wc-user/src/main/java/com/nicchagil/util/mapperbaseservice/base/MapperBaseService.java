package com.nicchagil.util.mapperbaseservice.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.util.clazz.GenericsClassUtils;
import com.nicchagil.util.spring.ApplicationContextUtils;

/**
 * 基础Service
 *
 * @param <T> 实体类型
 * @param <E> 实体Example类型
 * @param <M> Mapper类型
 */
public class MapperBaseService <T, E, M> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public M getMapper() {
		Class<?> clazz = GenericsClassUtils.getGenericsTypeByClassAndIndex(this.getClass(), 2);
		this.logger.info("Generics class : {}", clazz);
		
		M m = (M)ApplicationContextUtils.getBean(clazz);
		return m;
	}
	
}
