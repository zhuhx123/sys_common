package com.ivymei.system.common.web.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ivymei.system.common.constant.Constant;
import com.ivymei.system.common.constant.enums.common.MsgCode;
import com.ivymei.system.common.web.exception.ImeiException;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by 20170331 on 2017/7/13.
 */
public class CommonApiResponseAopHelper {

    private static Logger log = Logger.getLogger(CommonApiResponseAopHelper.class);

    private static SerializerFeature[] serializerFeatures = null;

    static {
        serializerFeatures = new SerializerFeature[]{
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteSlashAsSpecial};
    }

    public static Object point(ProceedingJoinPoint pjp) {
        Object res = null;
        try {
            res = pjp.proceed();
            if (res instanceof String) {
                JSONObject obj = JSONObject.parseObject((String) res);
                ((JSONObject) obj).put(Constant.ERROR_CODE_KEY, "0");// 0主要是为了兼容旧版本的定义
                ((JSONObject) obj).put(Constant.ERROR_MSG_KEY, MsgCode.SUCCESSFUL.getMessage());
                String result = JSONObject.toJSONString(obj, serializerFeatures);
                return result;
            }

        } catch (Throwable ex) {
            long timstamp = System.currentTimeMillis() / 1000;
            JSONObject obj = new JSONObject();
            if (ex instanceof ImeiException) {
                obj.put(Constant.ERROR_CODE_KEY, ((ImeiException) ex).getMsgCode().getMsgCode());
                obj.put(Constant.ERROR_MSG_KEY, "[" + timstamp + "]" + ((ImeiException) ex).getMsg());
                //obj.put(Constant.DATA_KEY, ((ImeiException) ex).getResult());
            } else {
                obj.put(Constant.ERROR_CODE_KEY, MsgCode.SYSTEM_ERROR.getMsgCode());
                obj.put(Constant.ERROR_MSG_KEY, "[" + timstamp + "]" + MsgCode.SYSTEM_ERROR.getMessage());
            }

            String className = pjp.getSignature().getDeclaringTypeName();
            String methodName = pjp.getSignature().getName();

            String args = formatArgs(pjp);
            StringBuffer errorLog = new StringBuffer();
            errorLog.append("\n");
            errorLog.append("================================================【接口异常】================================================");
            errorLog.append("\n");
            errorLog.append("【异常时间】：" + timstamp);
            errorLog.append("\n");
            errorLog.append("【方法】：" + className + "." + methodName);
            errorLog.append("\n");
            errorLog.append("【参数】：\n" + args);

            log.error(errorLog, ex);

            return obj.toJSONString();
        }
        return res;
    }

    private static String formatArgs(ProceedingJoinPoint pjp) {
        Object[] obj = pjp.getArgs();
        StringBuffer buf = new StringBuffer();
        for (Object o : obj) {
            if (o != null) {
                buf.append("【TYPE】:" + o.getClass().getName() + "\n【VALUE】:" + o.toString() + "\n");
            }
        }
        return buf.toString();
    }
}
