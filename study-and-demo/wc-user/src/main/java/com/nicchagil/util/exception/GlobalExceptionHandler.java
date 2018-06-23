package com.nicchagil.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value = Exception.class)  
    @ResponseBody
    public GlobalHttpReturn handle(Exception e) {
		return new GlobalHttpReturn().setExceptionCodeEnum(ExceptionCodeEnum.MSG_00002);
    }

}
