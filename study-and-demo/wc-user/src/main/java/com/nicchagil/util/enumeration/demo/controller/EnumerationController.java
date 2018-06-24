package com.nicchagil.util.enumeration.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.nicchagil.util.enumeration.EnumUtils;
import com.nicchagil.util.enumeration.StatusEnum;
import com.nicchagil.util.exception.BusinessException;
import com.nicchagil.util.exception.ExceptionCodeEnum;
import com.nicchagil.util.exception.GlobalHttpReturn;
import com.nicchagil.util.map.NameClassMapUtils;

@RestController
@RequestMapping("/enum")
public class EnumerationController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 根据枚举名称获取枚举所有值
	 */
	@GetMapping("/getEnum") // http://127.0.0.1/enum/getEnum?enumName=
	public GlobalHttpReturn<?> getEnum(HttpServletRequest request, HttpServletResponse response, String enumName) {
		/* 构建<Class的简单名称,Class>的Map */
		List<Class<?>> classList = Lists.newArrayList(StatusEnum.class);
		Map<String, Class<?>> map = NameClassMapUtils.generateClassNameAndClassMap(classList);
		
		if (StringUtils.isBlank(enumName) || !map.containsKey(enumName)) {
			throw new BusinessException(ExceptionCodeEnum.MSG_00003);
		}
		
		Class<?> clazz = map.get(enumName);
		Object[] objs = EnumUtils.getEnumValuesByEnumClass(clazz);
		
		return new GlobalHttpReturn().setData(objs);
	}
	
}
