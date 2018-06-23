package com.nicchagil.util.i18n.demo.service;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.nicchagil.util.exception.BusinessException;
import com.nicchagil.util.exception.ExceptionCodeEnum;

@Service
public class I18nService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** 支持的系统语言 **/
	/* 请注意，Locale.US与Locale.ENGLISH的区别 */
	public static Locale[] SUPPORT_LOCALE_ARRAY = {Locale.SIMPLIFIED_CHINESE, Locale.US};
	
	/**
	 * 获取支持的系统语言
	 */
	public List<String> getSupportLocaleList() {
		List<String> supportLocaleList = Lists.newArrayList();
		
		for (Locale locale : SUPPORT_LOCALE_ARRAY) {
			supportLocaleList.add(locale.getLanguage());
		}
		
		return supportLocaleList;
	}
	
	/**
	 * 切换系统语言
	 */
	public void switchLanguage(HttpServletRequest request, HttpServletResponse response, String language) {
		Locale.SIMPLIFIED_CHINESE.getLanguage();
		
		if (StringUtils.isBlank(language)) {
			throw new BusinessException(ExceptionCodeEnum.MSG_00001);
		}
		
		LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
		for (Locale locale : SUPPORT_LOCALE_ARRAY) {
			if (locale.getLanguage().equalsIgnoreCase(language)) {
				HttpSession session = request.getSession(true);
				this.logger.info("session id : {}", session.getId());
				
				session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
				localeResolver.setLocale(request, response, locale);
				this.logger.info("the locale switch to : {}", locale);
				this.logger.info("current locale : {}", LocaleContextHolder.getLocale());
				return;
			}
		}
	}

}
