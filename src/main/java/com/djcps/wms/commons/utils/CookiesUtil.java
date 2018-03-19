package com.djcps.wms.commons.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @title:cook工具类
 * @description:
 * @company:djwms
 * @author:zdx
 * @date:2017年11月13日
 */
public class CookiesUtil{

	/**
	 * @title:根据名字获取cookie
	 * @description:
	 * @param request
	 * @param name
	 * @return
	 * @author:zdx
	 * @date:2017年11月13日
	 */
	public static String getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie.getValue();
		} else {
			return null;
		}

	}

	/**
	 * @title:将cookie封装到Map里面
	 * @description:
	 * @param request
	 * @return
	 * @author:zdx
	 * @date:2017年11月13日
	 */
	private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>(16);
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	/**
	 * @title:保存Cookies
	 * @description:
	 * @param response
	 * @param name
	 * @param value
	 * @param time
	 * @return
	 * @author:zdx
	 * @date:2017年11月13日
	 */
	public static HttpServletResponse setCookie(HttpServletResponse response, String name,
			String value, int time) {
		// new一个Cookie对象,键值对为参数
		Cookie cookie = new Cookie(name, value);
		// tomcat下多应用共享
		cookie.setPath("/");
		// 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
		cookie.setHttpOnly(true);
		try {
			URLEncoder.encode(value, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		cookie.setMaxAge(time);
		// 将Cookie添加到Response中,使之生效
		// addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
		response.addCookie(cookie); 
		return response;
	}
}