package com.nicchagil.util.i18n.demo.controller;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/i18n")
public class I18nController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/getLocale") // http://127.0.0.1/i18n/getLocale
    public String getLocale() {
		Locale locale = LocaleContextHolder.getLocale();
		logger.info("current locale : {}", locale.getLanguage());
		return locale.getLanguage();
    }
	
	@GetMapping("/getI18nMsg") // http://127.0.0.1/i18n/getI18nMsg?code=MSG_00001
	public String getI18nMsg(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}
		
		String msg = this.messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
		return msg;
	}
	
}
