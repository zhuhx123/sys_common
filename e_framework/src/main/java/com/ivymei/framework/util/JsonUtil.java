package com.ivymei.framework.util;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;

public final class JsonUtil {
 	 
	private static JsonFactory jsonFactory = new JsonFactory();
	
	private static ObjectMapper mapper = null;
	
	static {
		jsonFactory.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		jsonFactory.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper = new ObjectMapper(jsonFactory);
	}
	
	public static ObjectMapper getMapper() {
		return mapper;
	}

	public static JsonFactory getJsonFactory() {
		return jsonFactory;
	}
 
	public static <T> T toBean(String json, Class<T> clazz) {
		
		T rtv = null;
		try {
			rtv = mapper.readValue(json, clazz); 
		} catch (Exception ex) {  
			throw new IllegalArgumentException("json将json字符串转化成对象出错", ex);
		}
		return rtv;		
	}
	
	public static String toJson(Object bean) {
		
		String rtv = null;
		try {
			rtv = mapper.writeValueAsString(bean);
		} catch (Exception ex) {  
			throw new IllegalArgumentException("java bean将bean转化成json字符串出错", ex);
		}
		return rtv;		
	}
	public static void main(String[] args) {
		long mm=System.currentTimeMillis();
		System.out.println(mm);
	}
}