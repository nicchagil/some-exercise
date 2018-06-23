package com.nicchagil.util.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.nicchagil.util.spring.ApplicationContextUtils;

/**
 * 继承运行时异常的业务异常
 */
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private String code;
	
	private String msg;
	
	private ExceptionCodeEnum exceptionCodeEnum;

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	public ExceptionCodeEnum getExceptionCodeEnum() {
		return exceptionCodeEnum;
	}
	
	/**
	 * 通过指定枚举设置代码和提示信息
	 */
	public BusinessException setExceptionCodeEnum(ExceptionCodeEnum exceptionCodeEnum) {
		this.exceptionCodeEnum = exceptionCodeEnum;
		this.code = exceptionCodeEnum.name();
		
		/* 设置系统语言的提示信息 */
		Locale locale = LocaleContextHolder.getLocale();
		if (locale != null) {
			MessageSource messageSource = ApplicationContextUtils.getBean(MessageSource.class);
			this.msg = messageSource.getMessage(this.code, null, locale);
		}
		
		return this;
	}
	
	public BusinessException(ExceptionCodeEnum exceptionCodeEnum) {
		super();
		this.setExceptionCodeEnum(exceptionCodeEnum);
	}
	
}
