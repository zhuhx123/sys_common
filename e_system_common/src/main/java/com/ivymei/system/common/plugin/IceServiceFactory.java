package com.ivymei.system.common.plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.ivymei.system.common.web.exception.ImeiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.ivymei.framework.util.StringUtil;
import com.ivymei.system.common.plugin.ice.server.IceResponseHelper;
import com.ivymei.system.common.plugin.ice.server.MethodParam;
import common.business.commandice.model.ResponseMessage;

public abstract class IceServiceFactory {
	private static Logger logger = LoggerFactory.getLogger(IceServiceFactory.class);

	/**
	 * ice接口方法调度
	 * 
	 * @param method
	 *            method 方法昵称
	 * @param paramMap
	 *            <String,Object> paramMap 参数
	 * @return String
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public ResponseMessage execute(String method, Map<String, Object> paramMap) {
		ResponseMessage rm = null;
		long start = System.currentTimeMillis();
		try {
			
			logger.info("================请求接口开始【" + this.getClass().getName() + "." + method + "】================");
			logger.info("【请求参数】:" + (paramMap == null ? "null" : paramMap.toString()));

			boolean isAnnoation = false;
			Method methods[] = this.getClass().getMethods();
			for (Method m : methods) {
				isAnnoation = m.isAnnotationPresent(MethodParam.class);
				if (isAnnoation) {
					MethodParam p = m.getAnnotation(MethodParam.class);
					if (p.value().equals(method)) {
						rm = (ResponseMessage) m.invoke(this, paramMap);
					}
				}
			}
			if (null == rm) {
				rm = IceResponseHelper.buildFaileResult("抱歉，没有此方法！");
			}
		} catch (Exception e) {
			logger.error("【接口：" + this.getClass().getName() + "." + method + "】发生异常！", e);
			if (e.getCause() != null && e.getCause() instanceof ImeiException) {
				if (StringUtil.isNotBlank(e.getCause().getMessage())) {
					rm = JSONObject.parseObject(e.getCause().getMessage(), ResponseMessage.class);
				}
			} else {
			
				rm = IceResponseHelper.buildExceptionResult();
			}
			//rm = IceResponseHelper.buildExceptionResult("抱歉，网络异常，请稍候再试");
		}
		long end = System.currentTimeMillis();
		logger.info("【调用时长】:" + (end - start) + "ms;【返回数据】:" + (rm == null ? "null" : JSONObject.toJSONString(rm)));
		logger.info("================请求接口结束【" + this.getClass().getName() + "." + method + "】===========================================");
		return rm;
	}

}
