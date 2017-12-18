/**
 * 
 */
package com.ivymei.system.common.util;

import java.io.StringReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ivymei.framework.exception.ServiceException;
import com.ivymei.system.common.constant.enums.common.MsgCode;

/**
 * 
 * @author luoxl
 */
public class XmlUtil {

	public static Logger log = LoggerFactory.getLogger(XmlUtil.class);

	/**
	 * XML转换对象
	 * 
	 * @param xml
	 * @param clazz
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T xmlToBean(String xml, Class clazz) throws Exception {
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(new StringReader(xml));
		} catch (JAXBException e) {
			log.error("XML转换对象异常", e);
			e.printStackTrace();
			throw new ServiceException(MsgCode.SYSTEM_ERROR.getMsgCode(),"转换xml异常");
		}
	}

	/**
	 * SortedMap转换成xml
	 * 
	 * @param parameters
	 * @return
	 */
	public static String getRequestXml(Map<String, String> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = entry.getKey() + "";
			String v = entry.getValue() + "";
			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
	
}
