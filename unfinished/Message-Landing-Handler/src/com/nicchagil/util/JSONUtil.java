package com.nicchagil.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
	
	/* 公共的ObjectMapper */
    private static ObjectMapper commonObjectMapper = new ObjectMapper();
    
    /**
     * 转JSON
     */
    public static String toJSON(Object obj) {
        String json = null;
        try {
            json = commonObjectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Bean->JSON转换失败");
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
            throw new RuntimeException("JSON->Bean转换失败");
        }
        return t;
    }
    
    @Test
    public void toJSONTest() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "123");
        map.put("name", "Nick Huang");
        
        System.out.println(JSONUtil.toJSON(map));
    }
    
    @Test
    public void toBeanTest() {
        System.out.println(JSONUtil.toBean("{\"name\":\"Nick Huang\",\"id\":\"123\"}",  new TypeReference<Map<String, String>>() {}));
    }

}
