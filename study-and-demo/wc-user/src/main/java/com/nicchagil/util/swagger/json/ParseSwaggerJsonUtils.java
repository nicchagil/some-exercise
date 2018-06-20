package com.nicchagil.util.swagger.json;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.nicchagil.exercise.wcuser.thirdpartyframework.jackson.swagger.json.ParseSwaggerJsonTest;

public class ParseSwaggerJsonUtils {
	
	private static Logger logger = LoggerFactory.getLogger(ParseSwaggerJsonTest.class);
	
	/**
	 * 根据SWAGGER JSON获取请求URL和HTTP请求方法
	 */
	public static List<ApiVo> getHttpApiBySwaggerJson(String jsonString) {
		/* 加载JSON */
		ObjectMapper mapper = new ObjectMapper();  
		JsonNode node = null;
		try {
			node = mapper.readTree(jsonString);
		} catch (IOException e) {
			throw new RuntimeException("解析JSON异常", e);
		}
		
		/* 获取paths节点 */
		JsonNode pathsNode = node.get("paths");  
		logger.info("pathsJson : {}", pathsNode);
		
		List<ApiVo> apiVoList = Lists.newArrayList();
		
		/* 遍历paths节点，获取URL和HTTP请求方法 */
		Iterator<Entry<String, JsonNode>> iterator = pathsNode.fields();
		while (iterator.hasNext()) {
			Entry<String, JsonNode> entry = iterator.next();
			
			String url = entry.getKey();
			List<String> methodList = ParseSwaggerJsonUtils.getMethod(entry.getValue());
			
			if (CollectionUtils.isNotEmpty(methodList)) {
				for (String method : methodList) {
					apiVoList.add(new ApiVo(url, method));
				}
			}
			
			logger.info("URL : {}{}", entry.getKey(), methodList);
		}
		
		return apiVoList;
	}
	
	/**
	 * 获取SWAGGER JSON文档中请求方法节点的HTTP请求方法
	 */
	public static List<String> getMethod(JsonNode jsonNode) {
		if (jsonNode == null) {
			return null;
		}
		
		List<String> methodList = Lists.newArrayList();
		Iterator<Entry<String, JsonNode>> iterator = jsonNode.fields();
		while (iterator.hasNext()) {
			Entry<String, JsonNode> entry = iterator.next();
			String method = entry.getKey();
			methodList.add(method);
		}
		
		return methodList;
	}
	
	public static class ApiVo {
		private String url;
		private String httpRequestMethod;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getHttpRequestMethod() {
			return httpRequestMethod;
		}
		public void setHttpRequestMethod(String httpRequestMethod) {
			this.httpRequestMethod = httpRequestMethod;
		}
		public ApiVo(String url, String httpRequestMethod) {
			super();
			this.url = url;
			this.httpRequestMethod = httpRequestMethod;
		}
		@Override
		public String toString() {
			return "ApiVo [url=" + url + ", httpRequestMethod=" + httpRequestMethod + "]";
		}
	}

}
