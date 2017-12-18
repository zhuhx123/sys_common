package com.ivymei.system.common.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 重写MappingJacksonJsonView方法. MappingJacksonJsonView会返回 {model类名:{内容}}
 * 重写后返回{内容}
 * 
 * @author Administrator
 *
 */
public class CustomMappingJacksonJsonView extends MappingJackson2JsonView {

	protected String CHARSET = "UTF-8";

	protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {
		super.prepareResponse(request, response);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type, Accept-Language, Origin, Accept-Encoding");
		response.setHeader("Access-Control-Allow-Credentials", "true");
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		super.render(model, request, response);
	}

	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		Object value = filterModel(model);
		response.setContentType("text/plain; charset=UTF-8");
		response.setCharacterEncoding(CHARSET);
		
		SerializerFeature[] serializer = new SerializerFeature[]{
				SerializerFeature.WriteMapNullValue, //是否输出值为null的字段,默认为false
				SerializerFeature.WriteNullListAsEmpty, //List字段如果为null,输出为[],而非null
				SerializerFeature.WriteNullStringAsEmpty,//字符类型字段如果为null,输出为”“,而非null 
				SerializerFeature.WriteNullNumberAsZero,//数值字段如果为null,输出为0,而非null
				SerializerFeature.WriteNullBooleanAsFalse //Boolean字段如果为null,输出为false,而非null
		}; 
		
		String json = JSONObject.toJSONString(value, serializer);
		response.getOutputStream().write(json.getBytes(CHARSET));
		response.getOutputStream().flush();
	}
}
