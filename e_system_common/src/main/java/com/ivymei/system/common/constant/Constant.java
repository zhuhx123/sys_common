package com.ivymei.system.common.constant;

import com.ivymei.framework.util.PropertiesUtil;
import com.ivymei.framework.util.clazz.ClasspathPackageScanner;
import com.ivymei.system.common.constant.enums.common.EnabledEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统常量
 * 
 * @author show
 *
 */
public class Constant {

	private static Logger log = LoggerFactory.getLogger(Constant.class);

	public static final Integer DEFAULT_PAGE_NO = 1;// 默认页码
	public static final Integer DEFAULT_PAGE_SIZE = 20;// 默认分页大小

	public static final Integer EXPORT_PAGE_SIZE = 500;// 导出默认分页大小
	public static final Integer EXPORT_MAX_SIZE = 20000;// 导出最大数据
	public static final Integer EXPORT_ThREAD_SIZE = 5000;// 线程查询最大数据

	public static final String PAGE_NO_STR = "pageNo";// 默认页码
	public static final String PAGE_SIZE_STR = "pageSize";// 默认分页大小

	public static final String DEFAULT_PAGE_NO_STR = "1";// 默认页码
	public static final String DEFAULT_PAGE_SIZE_STR = "10";// 默认分页大小

	public static final String ERROR_CODE_KEY = "imei_error_code";
	public static final String ERROR_MSG_KEY = "imei_error_msg";
	public static final String DATA_KEY = "data";
	public static final String STATUS = "status";
	public static final String URL = "url";
	public static final String DATA_TOTAL = "total";
	public static final String SUMMATION_KEY = "summation";//合计
	public static final int CACHE_EXPIRE_SECONDS = 60 * 60;// 缓存有效时间

	public static final Integer SESSION_VO_EXPIRE_SECONDS = 48 * 60 * 60;// resids中sessionVo的过期时间（48小时），只要比token刷新间隔时间长就行了
	public static final Long TOKEN_REFRESH_INTEVAL_TIME = 2 * 60 * 60L;// 刷新token的间隔时间（2小时）
	// public static final Long TOKEN_REFRESH_INTEVAL_TIME = 60L;//
	// 刷新token的间隔时间（1分钟，测试）
	public static final Long TOKEN_COOKIE_EXPIRE_TIME = 48 * 60 * 60L;// token在cookies中的过期时间，指在长期没操作时的时间（48小时）
	// public static final Long TOKEN_COOKIE_EXPIRE_TIME = 60L;//
	// token在cookies中的过期时间，指在长期没操作时的时间（1分钟，测试）

	public static final String COOKIE_KEY_USERID = "IMEI_USER_ID";
	public static final String COOKIE_KEY_TOKEN = "IMEI_TOKEN";
	public static final String COOKIE_KEY_TIMESTAMP = "IMEI_TIMESTAMP";

	//三大中心配置信息
	public static final String  UCENTER_IPSTR = PropertiesUtil.getStringValue("API.ice.usercenter.address");
	public static final String  GOODSCENTER_IPSTR = PropertiesUtil.getStringValue("API.ice.goodscenter.address");
	public static final String  ORDERCENTER_IPSTR = PropertiesUtil.getStringValue("API.ice.ordercenter.address");

	public static final String COOKIE_KEY_HIS_USERID = "IMEI_HIS_USER_ID";
	public static final String COOKIE_KEY_HIS_USERNAME = "IMEI_HIS_USER_NAME";
	public static final String COOKIE_KEY_HIS_TIMESTAMP = "IMEI_HIS_TIMESTAMP";
	public static final String COOKIE_KEY_HIS_TOKENE = "IMEI_HIS_USER_TOKEN";
	
	public static final String PARAM_KEY_TOKOEN = "token";
	public static final String PARAM_KEY_USERID = "loginUserId";
	public static final String PARAM_KEY_TIMESTAMP = "timestamp";

	public static final String HTTPREQUER_HEADER_USERAGENT = "User-Agent";// request请求的Header

	public static final String REDIS_SUB_CHANNEL_REPORT_RUN_STATUS = "REDIS_SUB_CHANNEL_REPORT_RUN_STATUS";

	public static String ENVIRONMENT = PropertiesUtil.getStringValue("environment");// 系统环境
	public static String PERMISSION_ADDR = PropertiesUtil.getStringValue("permission.addr");// 权限后台访问地址
	public static String SAAS_ADDR = PropertiesUtil.getStringValue("imei.saas.domain");
	public static String VOTE_NOTIFY_URL = PropertiesUtil.getStringValue("imei.vote.domain");
	public static String BASE_URL_WEB = PropertiesUtil.getStringValue("imei.web.domain");
	public static String URL_WEB = PropertiesUtil.getStringValue("url.web");
	public static String IMEI_SHOPSYS_DOMAIN = PropertiesUtil.getStringValue("imei.shopsys.domain");
	public static String IMEI_EXPORT_DOMAIN = PropertiesUtil.getStringValue("imei.export.domain");
	public static String IMEI_WEBURL_DOMAIN = PropertiesUtil.getStringValue("imei.weburl.domain");
	private static Map<String, Object> enums = null;

	/**
	 * 加载所有枚举类，返回格式为：
	 * 
	 * <pre>
	 * key：EnumName_name，value：Enum<br/>
	 * 如：{@link EnabledEnum}，则会返回：
	 * <ul>
	 * 	<li>EnabledEnum_YES:{@link EnabledEnum#YES}</li>
	 * 	<li>EnabledEnum_NO:{@link EnabledEnum#NO}</li>
	 * 	<li>EnabledEnum:{@link EnabledEnum#values()}</li>
	 * </ul>
	 * </pre>
	 * 
	 * @return
	 * @author show.so
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, Object> loadEnums() {
		if (enums != null) {
			return enums;
		} else {
			enums = new HashMap<String, Object>();
		}

		try {
			ClassLoader classLoader = Constant.class.getClassLoader();
			String basePackage = "com.ivymei.system.common.constant.enums";

			ClasspathPackageScanner scanner = new ClasspathPackageScanner(basePackage, classLoader);

			List<Class> enumsClass = scanner.scanPackageClass();

			for (Class clazz : enumsClass) {
				String clazzName = clazz.getSimpleName();

				Method[] methods = clazz.getMethods();
				if (clazz.isEnum()) {
					for (Method method : methods) {
						if (method.getName().equals("values")) {
							Object obj = method.invoke(clazz, null);
							if (obj instanceof Object[]) {
								for (Object objj : (Object[]) obj) {
									Class cls = objj.getClass();

									if (cls.isEnum()) {
										Method m = cls.getMethod("name", null);
										Object name = m.invoke(objj, null);
										enums.put(clazzName + "_" + name, objj);
									}
								}
							}
							// 把Enum.value()也加上
							enums.put(clazzName, obj);
						}
					}

				}
			}
		} catch (Exception e) {
			log.error("加载枚举类异常：", e);
		}

		return enums;
	}
}
