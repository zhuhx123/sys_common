package com.ivymei.framework.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;

/**
 * Map工具类，将一个Map做转换格式化等操作
 *
 * @author hongyuhao
 * @version 2014年4月3日 - 下午11:54:52
 */
public class MapUtil {
	
	
	/** 
     * 将一个 JavaBean 对象转化为一个  Map 
     * @param bean 要转化的JavaBean 对象 
     * @return 转化出来的  Map 对象 
     * @throws IntrospectionException 如果分析类属性失败 
     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
     */  
    @SuppressWarnings({ "rawtypes", "unchecked" })  
    public static Map<String,String> convertBeanToMap(Object bean)  
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {  
        Class type = bean.getClass();  
        Map<String,String> returnMap = new HashMap<String,String>();  
        BeanInfo beanInfo = Introspector.getBeanInfo(type);  
  
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
        for (int i = 0; i< propertyDescriptors.length; i++) {  
            PropertyDescriptor descriptor = propertyDescriptors[i];  
            String propertyName = descriptor.getName();  
            if (!propertyName.equals("class")) {  
                Method readMethod = descriptor.getReadMethod();  
                Object result = readMethod.invoke(bean, new Object[0]);  
                if (result != null) {  
                    returnMap.put(propertyName, result.toString());  
                } else {  
                    returnMap.put(propertyName, "");  
                }  
            }  
        }  
        return returnMap;  
    }  
	/**
	 * 将map转换成数组对象
	 * @param map
	 * @return
	 */
	public static Object[] convertToArray(Map map) {
		int size = map.keySet().size();
		if(size > 0) {
			Iterator it = map.keySet().iterator();
			Object[] objArray = new Object[size*2]; 
			int i = 0;
			while(it.hasNext()) {
				Object key = it.next();
				objArray[i] = key;
				objArray[i+1] = map.get(key);
				i += 2;
			}
			return objArray;
		}
		return null;
	}
	
	/**
	 * 将map转换成字符串数组对象：对应的键值对都使用了toString(),空的对象值采用空字符串代替
	 * @param map
	 * @return
	 */
	public static String[] convertToStringArray(Map map) {
		int size = map.keySet().size();
		if(size > 0) {
			Iterator it = map.keySet().iterator();
			String[] objArray = new String[size*2]; 
			int i = 0;
			while(it.hasNext()) {
				Object key = it.next();
				objArray[i] = key.toString();
				if(null != map.get(key)) {
					objArray[i+1] = map.get(key).toString();
				} else {
					objArray[i+1] = "";
				}
				i += 2;
			}
			return objArray;
		}
		return null;
	}
	
	/**
	 * 将一个map列表按照某个key分成几个列表并存入一个新的map中
	 * @param list
	 * @return
	 */
	public static Map<String,Object> groupList(List<Map<String,Object>> list,String key) {
		Map<String,Object> resultMap = new LinkedHashMap<String,Object>(); 
		for(Map<String,Object> map : list) {
			String mapKey = String.valueOf(map.get(key));
			if(!resultMap.containsKey(mapKey)) {
				resultMap.put(mapKey, new ArrayList<Map<String,Object>>());
			}
			((List)resultMap.get(mapKey)).add(map);
		}
		
		return resultMap;
	}
	
	/**
	 * 通过list内对象属性值转换map
	 * @param list
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> convertToMap(List list,String key) throws Exception {
		Map<String,Object> resultMap = new LinkedHashMap<String,Object>(); 
		for(Object object : list) {
			Class clz = object.getClass();
			Method m = (Method) clz.getMethod(  
                 "get" + getMethodName(key)); 
			
			String mapKey = String.valueOf(m.invoke(object));
			
			resultMap.put(mapKey, object);
		}
		
		return resultMap;
	}
	
	/**
	 * 将一个map转换成一个list，元素为map，每个key跟value组成一个map
	 * @param list
	 * @return
	 */
	public static List<Map<String,Object>> convertToList(Map<String,Object> map) {
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		for(Entry<String,Object> entry : map.entrySet() ) {
			Map<String,Object> tmpMap = new HashMap<String,Object>();
			tmpMap.put(entry.getKey(), entry.getValue());
			resultList.add(tmpMap);
		}
		
		return resultList;
	}
	
	/**
	 * 将实体转换成map
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static Map<String,Object> transferFromObject(Object object) throws Exception {
		return JSONObject.parseObject(JSONObject.toJSONString(object), Map.class);
	}
	
	
	/**
	 * 创建map
	 * @param pros
	 * @author xiao
	 * @return
	 */
	public static Map<String,Object> asMap(Object ... pros){
		Map<String,Object> properties = new HashMap<String, Object>();
		if (pros.length % 2 == 1)
			throw new IllegalArgumentException("参数个数必须是偶数个！ ");

		int index = 0;
		String name = null;
		for (Object s : pros) {
			if (index % 2 == 0) {
				if (s == null || StringUtil.isNullOrBlank(s.toString()))
					throw new IllegalArgumentException("字段名称不能为空！ ");
				name = s.toString();
			} else {
				properties.put(name, s);
			}
			index++;
		}
		
		return properties;
	}
	
	/**
	 * 创建map
	 * @param pros
	 * @author xiao
	 * @return
	 */
	public static <T extends Map> T createMap(T properties, Object... params) {
		if (params.length % 2 == 1)
			throw new IllegalArgumentException("参数个数必须是偶数个！ ");

		int index = 0;
		Object name = null;
		for (Object s : params) {
			if (index % 2 == 0) {
				if (s == null || StringUtil.isNullOrBlank(s.toString()))
					throw new IllegalArgumentException("字段名称不能为空！ ");
				name = s;
			} else {
				properties.put(name, s);
			}
			index++;
		}
		
		return properties;
	}
	
	/**
	 * 首字母大写
	 * @param fildeName
	 * @return
	 * @throws Exception
	 */
	private static String getMethodName(String fildeName) throws Exception{  
        byte[] items = fildeName.getBytes();  
        items[0] = (byte) ((char) items[0] - 'a' + 'A');  
        return new String(items);  
    }  
}
