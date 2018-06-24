package com.nicchagil.util.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.nicchagil.util.spring.ApplicationContextUtils;

public class GlobalHttpReturn <T> {
	
	/** 代码 **/
	private String code;
	
	/** 提示信息 **/
	private String msg;
	
	/** 数据 **/
	private T data;

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	public T getData() {
		return data;
	}

	public GlobalHttpReturn setData(T data) {
		this.data = data;
		return this;
	}

	/**
	 * 通过指定枚举设置代码和提示信息
	 */
	public GlobalHttpReturn setExceptionCodeEnum(ExceptionCodeEnum exceptionCodeEnum) {
		this.code = exceptionCodeEnum.name();
		
		/* 设置系统语言的提示信息 */
		Locale locale = LocaleContextHolder.getLocale();
		if (locale != null) {
			MessageSource messageSource = ApplicationContextUtils.getBean(MessageSource.class);
			this.msg = messageSource.getMessage(this.code, null, locale);
		}
		
		return this;
	}

}
