package com.nicchagil.myjwtexercise.util;
import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
	
	/**
	 * 获取指定请求头
	 * @param request 请求
	 * @param name 请求头名
	 * @return 请求头值
	 */
	public static String getHeader(HttpServletRequest request, String name) {
		if (request == null) {
			return null;
		}
		
		String value = request.getHeader(name);
		return value;
	}

}
