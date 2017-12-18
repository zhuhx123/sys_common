package com.ivymei.framework.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 从HttpServletRequest中获取参数
 * Created by show on 2017/7/12.
 */
public class RequestUtil {

    public static String getString(String paramName, HttpServletRequest request) {
        String param = request.getParameter(paramName);
        return param;
    }

    public static Short getShort(String paramName, HttpServletRequest request) {
        Short value=null;
        String param = request.getParameter(paramName);
        if (!StringUtil.isNullOrBlank(param) && StringUtils.isNumeric(param)) {
            value = Short.parseShort(param);
        }
        return value;
    }

    public static Integer getInteger(String paramName, HttpServletRequest request) {
        Integer value = null;
        String param = request.getParameter(paramName);
        if (!StringUtil.isNullOrBlank(param) && StringUtils.isNumeric(param)) {
            value = Integer.parseInt(param);
        }
        return value;
    }

    public static Float getFloat(String paramName, HttpServletRequest request) {
        Float value = null;
        String param = request.getParameter(paramName);
        if (!StringUtil.isNullOrBlank(param) && StringUtils.isNumeric(param)) {
            value = Float.parseFloat(param);
        }
        return value;
    }

    public static Long getLong(String paramName, HttpServletRequest request) {
        Long value = null;
        String param = request.getParameter(paramName);
        if (!StringUtil.isNullOrBlank(param) && StringUtils.isNumeric(param)) {
            value = Long.parseLong(param);
        }
        return value;
    }
}
