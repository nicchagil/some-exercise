import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	
	/**
	 * 根据key获取Cookie的值
	 * @param request 请求对象
	 * @param key 键
	 * @return Cookie的值
	 */
	public static String getValueByName(HttpServletRequest request, String key) {
		Cookie c = getCookieByName(request, key);
		
		if (c != null) {
			return c.getValue();
		}
		
		return null;
	}
	
	/**
	 * 根据key获取Cookie
	 * @param request 请求对象
	 * @param key 键
	 * @return Cookie对象
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String key) {
		if (request == null || key == null || key.length() == 0) {
			return null;
		}
		
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0) {
			return null;
		}
		
		for (Cookie c : cookies) {
			if (key.equals(c.getName())) {
				return c;
			}
		}
		
		return null;
	}
	
	/**
	 * 设置Cookie
	 * @param response 响应对象
	 * @param key 键
	 * @param value 值
	 * @param domain 域
	 * @param path 路径
	 * @param maxAge 存活时间
	 */
	public static void setCookie(HttpServletResponse response, String key, String value, String domain, String path, int maxAge) {
		Cookie c = new Cookie(key, value);
		c.setDomain(domain);
		c.setPath(path);
		c.setMaxAge(maxAge);
		
		response.addCookie(c);
	}
	
	/**
	 * 设置Cookie（根路径以下均有效、浏览器会话超时）
	 * @param response 响应对象
	 * @param key 键
	 * @param value 值
	 * @param domain 域
	 */
	public static void setCookieWithRootPathAndBrowerSession(HttpServletResponse response, String key, String value, String domain) {
		// 根路径以下均有效、浏览器会话超时
		setCookie(response, key, value, domain, "/", -1);
	}
	
}
