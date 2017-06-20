package com.nicchagil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class PreFilter
 */
public class PreFilter extends BusinessFilter {
	
	private List<String> excludedURLPrefixList = new ArrayList<String>();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("this is PreFilter.");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		System.out.println("URI : " + httpServletRequest.getRequestURI());
		System.out.println("URL : " + httpServletRequest.getRequestURL());
		System.out.println("ServletPath : " + httpServletRequest.getServletPath());
		
		if (this.isExcludeURL(httpServletRequest)) { // 是否被排除的URL
			chain.doFilter(httpServletRequest, response);
		} else {
			super.doFilter(request, response, chain);
		}
		
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		String excludedURLPrefixs = fConfig.getInitParameter("excludedURLPrefix");
		if (excludedURLPrefixs != null && excludedURLPrefixs.trim().length() > 0) {
			
			String[] excludedURLPrefixArray = excludedURLPrefixs.split(",");
			for (String t : excludedURLPrefixArray) {
				if (t != null && t.trim().length() > 0) {
					excludedURLPrefixList.add(t.trim());
				}
			}
		}
		
		super.init(fConfig);
	}
	
	/**
	 * 是否被排除的URL
	 * @param httpServletRequest 请求
	 * @return 是否被排除的URL
	 */
	private boolean isExcludeURL(HttpServletRequest httpServletRequest) {
		String servletPath = httpServletRequest.getServletPath();
		
		if (excludedURLPrefixList == null || excludedURLPrefixList.size() == 0) {
			return false;
		}
		
		for (String prefix : excludedURLPrefixList) {
			if (servletPath.startsWith(prefix)) {
				System.out.println(servletPath + " match " + prefix);
				return true;
			}
		}
		
		return false;
	}
	
}
