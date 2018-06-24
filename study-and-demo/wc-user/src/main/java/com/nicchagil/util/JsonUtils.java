package com.nicchagil.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicchagil.util.enumeration.StatusEnum;

public class JsonUtils {
	
	private Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    
    /* 公共的ObjectMapper */
    private static ObjectMapper commonObjectMapper = new ObjectMapper();
    
    static {
        commonObjectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * 转JSON
     */
    public static String toJson(Object obj) {
        String json = null;
        try {
            json = commonObjectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Bean->JSON转换失败：" + obj);
        }
        return json;
    }
    
    /**
     * 转Bean
     */
    public static <T> T toBean(String json, TypeReference<T> valueTypeRef) {
        T t = null;
        try {
            t = commonObjectMapper.readValue(json, valueTypeRef);
        } catch (IOException e) {
            throw new RuntimeException("JSON->Bean转换失败：" + json);
        }
        return t;
    }
    
    @Test
    public void toJsonTestx1() {
    	logger.info(JsonUtils.toJson(StatusEnum.ACTIVE));
    }
    
}