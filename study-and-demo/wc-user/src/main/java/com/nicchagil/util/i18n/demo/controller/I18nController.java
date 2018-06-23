package com.nicchagil.util.i18n.demo.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.nicchagil.util.i18n.demo.service.I18nService;

@RestController
@RequestMapping("/i18n")
public class I18nController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private I18nService i18nService;
	
	/**
	 * 切换系统语言
	 */
	@GetMapping("/switchLanguage") // http://127.0.0.1/i18n/switchLanguage?language=zh
	public void switchLanguage(HttpServletRequest request, HttpServletResponse response, String language) {
		this.i18nService.switchLanguage(request, response, language);
	}
	
	@GetMapping("/getLocale") // http://127.0.0.1/i18n/getLocale
    public String getLocale(HttpServletRequest request) {
		Locale locale = RequestContextUtils.getLocale(request);
		logger.info("current locale : {}", locale.getLanguage());
		return locale.getLanguage();
    }
	
	@GetMapping("/getI18nMsg") // http://127.0.0.1/i18n/getI18nMsg?code=MSG_00001
	public String getI18nMsg(HttpServletRequest request, String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}
		
		Locale locale = RequestContextUtils.getLocale(request);
		logger.info("current locale : {}", locale.getLanguage());
		
		String msg = this.messageSource.getMessage(code, null, locale);
		logger.info("msg : {}", msg);
		return msg;
	}
	
	@GetMapping("/getSupportLocaleList") // http://127.0.0.1/i18n/getSupportLocaleList
    public List<String> getSupportLocaleList(HttpServletRequest request) {
		return this.i18nService.getSupportLocaleList();
    }
	
}
