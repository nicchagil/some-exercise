package com.nicchagil.util.http;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class UrlUtils {

	/**
	 * 将KeyValue集合以Get请求的形式拼接到指定URL后面
	 */
	public static String concatParameter(String prefixUrl, List<KeyVal> keyValueList) {
		if (prefixUrl == null || prefixUrl.trim().length() == 0) {
			return prefixUrl;
		}
		
		if (keyValueList == null || keyValueList.size() == 0) {
			return prefixUrl;
		}
		
		String paramStringChain = Joiner.on("&").join(keyValueList);
		
		if (prefixUrl.contains("?")) {
			prefixUrl = new StringBuffer().append(prefixUrl).append("&").append(paramStringChain).toString();
		} else {
			prefixUrl = new StringBuffer().append(prefixUrl).append("?").append(paramStringChain).toString();
		}
		
		return prefixUrl;
	}

	public static class KeyVal {
		private String key;
		private String value;

		public KeyVal(String key, String value) {
			super();
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		@Override
		public String toString() {
			return new StringBuffer().append(key).append("=").append(value).toString();
		}
	}

	public static void main(String[] args) {
		System.out.println(UrlUtils.concatParameter("https://nicchagil.com", Lists.newArrayList(new KeyVal("name", "Nick"))));
		System.out.println(UrlUtils.concatParameter("https://nicchagil.com?1=1", Lists.newArrayList(new KeyVal("name", "Nick"))));
	}

}
