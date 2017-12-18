package com.ivymei.system.common.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ivymei.framework.util.StringUtil;

/**
 * 重写MappingJacksonJsonView方法. MappingJacksonJsonView会返回 {model类名:{内容}}
 * 重写后返回{内容}
 * 
 * @author Administrator
 *
 *         配置： <property name="mediaTypes"> <map> <entry key="jsonp"
 *         value="application/javascript"/> </map> </property> <property
 *         name="defaultViews"> <list> <bean
 *         class="com.ehaoyao.framework.web.view.CustomMappingJacksonJsonpView"
 *         ></bean> </list> </property> 访问例子：
 *         http://xxxx.39.net/yy.jsonp?callback=aaa
 */
public class CustomMappingJacksonJsonpView extends CustomMappingJacksonJsonView {

	private Logger log = Logger.getLogger(getClass());
	public static final String DEFAULT_CONTENT_TYPE = "application/javascript";

	private String charset = "UTF-8";

	@Override
	public String getContentType() {
		return DEFAULT_CONTENT_TYPE;
	}

	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		super.prepareResponse(request, response);

		response.setContentType(getContentType());
		response.setCharacterEncoding(this.getCharset());
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "no-cache, no-store, max-age=0");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type, Accept-Language, Origin, Accept-Encoding");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		response.addDateHeader("Expires", 1L);
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("GET".equals(request.getMethod().toUpperCase())) {
			String callback = request.getParameter("callback");
			if (!StringUtil.isEmpty(callback)) {
				log.debug("callback>>" + callback);

				response.setContentType(DEFAULT_CONTENT_TYPE);
				response.setCharacterEncoding(charset);

				response.getOutputStream().write(new String(callback + "(").getBytes());
				super.render(model, request, response);
				response.getOutputStream().write(new String(");").getBytes());
			} else {
				super.render(model, request, response);
			}
		} else {
			super.render(model, request, response);
		}
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}
}
