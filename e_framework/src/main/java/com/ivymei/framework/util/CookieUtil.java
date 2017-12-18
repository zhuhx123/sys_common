package com.ivymei.framework.util;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	private static final String CHARSET = "UTF-8";
	
	private static final String DOMAIN = "ivymei.com";

	/**
	 * 根据cookie的名称获取cookie
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null || name == null || name.length() == 0) {
			return null;
		}
		for (int i = 0; i < cookies.length; i++) {
			if (name.equals(cookies[i].getName())) {
				// && request.getServerName().equals(cookies[i].getDomain())) {
				return cookies[i];
			}
		}
		return null;
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie ck = getCookie(request, name);
		if (ck != null) {
			String value = null;
			try {
				value = URLDecoder.decode(ck.getValue(), CHARSET);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return value;
		} else {
			return null;
		}
	}

	/**
	 * 删除cookie
	 * 
	 * @param request
	 * @param response
	 * @param cookie
	 */
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, Cookie cookie) {
		if (cookie != null) {
			cookie.setPath(getPath(request));
			cookie.setValue("");
			cookie.setMaxAge(0);
			cookie.setDomain(getDomain(request));
			response.addCookie(cookie);
		}
	}

	/**
	 * 删除cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */
	public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie cookie = new Cookie(name, "");
		deleteCookie(request, response, cookie);
	}

	/**
	 * 设置cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 *            如果不设置时间，默认永久
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		setCookie(request, response, name, value, 0x278d00);
	}

	/**
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 * @param maxAge
	 *            设置cookie，设定时间
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
		int maxAge) {
		String cookieValue = null;
		try {
			cookieValue = URLEncoder.encode(value, CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Cookie cookie = new Cookie(name, cookieValue == null ? "" : cookieValue.replaceAll("\r\n", ""));
		cookie.setMaxAge(maxAge);
		cookie.setPath(getPath(request));
		cookie.setDomain(getDomain(request));
		response.addCookie(cookie);
	}

	private static String getPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return (path == null || path.length() == 0) ? "/" : path;
	}

	private static String getDomain(HttpServletRequest request){
		String serverName = request.getServerName();
		String domain = getTopDomain(serverName);
		return domain;
	}

	/**
	 * 获取一级域名
	 * @param url
	 * @return
     */
	public static String getTopDomain(String url) {
		if(!StringUtil.isNullOrBlank(url)){
			String host = url.toLowerCase();// 此处获取值转换为小写
			Pattern pattern = Pattern.compile("[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)");
			Matcher matcher = pattern.matcher(host);
			while (matcher.find()) {
				return matcher.group();
			}
		}
		return null;
	}


	public static void main(String[] args) {

	}
}
