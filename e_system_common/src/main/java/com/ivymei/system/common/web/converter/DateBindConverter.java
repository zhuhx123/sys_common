package com.ivymei.system.common.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

/**
 * 将 yyyy-MM-dd HH:mm:ss 格式的字符串解析为时间
 */
public class DateBindConverter implements Converter<String, Date>{
	
	
	private final Logger logger = Logger.getLogger(DateBindConverter.class);
	
	public static String[] PARSEABLE_DATE_FORMATS = {
			"yyyy-MM-dd HH:mm:ss",
			"yyyy-MM-dd HH:mm",
			"yyyy-MM-dd",
			"HH:mm:ss",
			"HH:mm"
	};
	
	@Override
	public Date convert(String source) {
		if(source !=null && source.trim().length() > 0){
			
			
			SimpleDateFormat sdf = new SimpleDateFormat();
			Date date = null;
			
			
			/*
			 * 将字符串转换为 时间
			 * */
			for(String PARSEABLE_DATE_FORMAT : PARSEABLE_DATE_FORMATS){
				if(date != null){ // 如果时间已经解析，跳出循环
					break;
				}
				
				try {
					sdf.applyPattern(PARSEABLE_DATE_FORMAT);
					date = sdf.parse(source);
				} catch (ParseException e) {
					// e.printStackTrace();
				}
			}
			
			
			/*
			 * 处理结果
			 * */
			if(date == null){ // 如果时间仍未空，抛异常或输出错误信息
				logger.info("【"+ source +"】时间格式错误！");
				return null;
				// throw new ImeiException(MsgCode.BAD_REQUEST, "【"+ source +"】时间格式错误！");
			}else{ // 如果时间已解析，响应时间
				return date;
			}
			
		}else{
			return null;
		}
	}
}
