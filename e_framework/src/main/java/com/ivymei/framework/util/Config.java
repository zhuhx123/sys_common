/**
 * 
 */
package com.ivymei.framework.util;

/**
 * 系统的相关配置项
 * 
 * @author luoxl
 */
public class Config {

	/**
	 * redis服务器地址
	 */
	public static final String REDIS_HOST = PropertiesUtil.getStringValue("REDIS_HOST");
	/**
	 * redis服务器端口
	 */
	public static final int REDIS_PORT = PropertiesUtil.getIntValue("REDIS_PORT");
	/**
	 * redis密码
	 */
	public static final String REDIS_PASSWORD = PropertiesUtil.getStringValue("REDIS_PASSWORD");

	/**
	 * 微信公众号的app_id
	 */
	public static final String WX_APP_ID = PropertiesUtil.getStringValue("WX.APP_ID");

	/**
	 * 微信支付的app_secret
	 */
	public static final String WX_APP_SECRET = PropertiesUtil.getStringValue("WX.APP_SECRET");

	/**
	 * 微信公众帐号的微信支付商户号
	 */
	public static final String WX_CH_ID = PropertiesUtil.getStringValue("WX.CH_ID");

	/**
	 * 微信证书地址
	 */
	public static final String WX_PAY_CERT = PropertiesUtil.getStringValue("WX_PAY_CERT");

	/**
	 * 红包领取结果
	 */
	public static final String REDPACKAGE_RESULT_URL = PropertiesUtil.getStringValue("redpackage.result.url");
	
	public static final String REDPACKAGE_SEND_URL = PropertiesUtil.getStringValue("redpackage.sendUrl");

	public static final String REDPACKAGE_SEND_NAME = PropertiesUtil.getStringValue("redpackage.sendName");
	public static final String REDPACKAGE_ACT_NAME = PropertiesUtil.getStringValue("redpackage.actName");
	public static final String REDPACKAGE_WISH = PropertiesUtil.getStringValue("redpackage.wish");
	public static final String REDPACKAGE_SUCCESS = PropertiesUtil.getStringValue("redpackage.success");
	public static final String REDPACKAGE_FAIL = PropertiesUtil.getStringValue("redpackage.fail");
	public static final String REDPACKAGE_REMARK = PropertiesUtil.getStringValue("redpackage.remark");
	
	/**
	 * 校招简历信息的文件路径
	 */
	public static final String RECRUIT_FILEPATH = PropertiesUtil.getStringValue("recruit.filePath");
}
