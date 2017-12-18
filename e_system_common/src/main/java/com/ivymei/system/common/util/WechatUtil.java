package com.ivymei.system.common.util;

import java.io.InputStream;
import java.security.KeyStore;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.ivymei.framework.util.Config;
import com.ivymei.framework.util.HttpUtil;
import com.ivymei.framework.util.IpUtil;
import com.ivymei.framework.util.SecurityUtil;
import com.ivymei.framework.util.StringUtil;

public class WechatUtil {

	private final static Logger log = LoggerFactory.getLogger(WechatUtil.class);
	private final static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
	private static JSONObject tokenNode = null;

	/** 根据code获取openid链接 */
	private static final String OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
	/** 获取微信用户信息链接 */
	private static final String USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN";

	/* 字段名 */
	public final static String ACCESS_TOKEN_NAME = "access_token";
	public final static String EXPIRES_IN_NAME = "expires_in";
	public final static String UPDATE_TIME_NAME = "updateTime";

	/**
	 * 发送微信红包接口
	 */
	private static final String SEND_WX_REDPACKAGE_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";

	/**
	 * 获取全局access_token
	 * 
	 * @return
	 * @throws PlatenoException
	 */
	public static String getAccessToken() {

		String accessToken = null;

		if (tokenNode == null) {
			tokenNode = new JSONObject();
			// 初始化刷新
			flushAccessToken();
		} else {
			long updateTime = tokenNode.getLongValue(UPDATE_TIME_NAME);
			long nowTime = new Date().getTime();
			int expires_in = tokenNode.getIntValue(EXPIRES_IN_NAME);
			// 超时刷新
			if ((nowTime - updateTime) / 1000 > expires_in) {
				flushAccessToken();
			}
		}
		accessToken = tokenNode.getString(ACCESS_TOKEN_NAME);

		return accessToken;
	}

	/**
	 * 刷新全局access_token
	 * 
	 * @throws PlatenoException
	 */
	private static void flushAccessToken() {
		String url = MessageFormat.format(ACCESS_TOKEN_URL, Config.WX_APP_ID, Config.WX_APP_SECRET);
		String resp = HttpUtil.httpsGet(url);

		JSONObject node = JSONObject.parseObject(resp);
		String accessToken = node.getString(ACCESS_TOKEN_NAME);
		if (!"".equals(accessToken)) {
			int expires_in = node.getIntValue(EXPIRES_IN_NAME);
			tokenNode.put(UPDATE_TIME_NAME, new Date().getTime());
			tokenNode.put(ACCESS_TOKEN_NAME, accessToken);
			tokenNode.put(EXPIRES_IN_NAME, expires_in);
		}
	}


	/**
	 * 发送红包
	 *
	 * @param openId
	 * @param billNo
	 * @param amount
	 * @param sendName 发送者名称
	 * @param wishing 祝福语
	 * @param actName 活动名称
	 * @param remark 备注
	 * @return
	 * @throws Exception
	 */
	public static String sendRedPackage(String openId, String billNo,int amount,String sendName,String wishing,String actName,String remark) throws Exception {
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("nonce_str", getNonceStr());
		map.put("mch_billno", billNo);
		map.put("mch_id", Config.WX_CH_ID);
		map.put("wxappid", Config.WX_APP_ID);
		map.put("send_name", sendName);// 发送者名称
		map.put("re_openid", openId);
		map.put("total_amount", amount + "");
		map.put("total_num", 1 + "");
		map.put("wishing", wishing);
		map.put("client_ip", IpUtil.getLocalIP());
		map.put("act_name", actName);
		map.put("remark", remark);

		String sign = signMD5(map);
		map.put("sign", sign);

		String result = "";// 请求发送红包的返回值

		String reuqestXml = XmlUtil.getRequestXml(map);

		log.debug("【发送红包】reuqestXml--->" + reuqestXml);
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		InputStream instream = null;
		try {
			instream = WechatUtil.class.getClassLoader().getResourceAsStream(Config.WX_PAY_CERT);// 放证书的路径
			keyStore.load(instream, Config.WX_CH_ID.toCharArray());

		} finally {
			instream.close();
		}
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, Config.WX_CH_ID.toCharArray()).build();

		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		try {
			HttpPost httpPost = new HttpPost(SEND_WX_REDPACKAGE_URL);// 红包接口
			log.info("executing request" + httpPost.getRequestLine());
			// 一定要用utf-8
			StringEntity reqEntity = new StringEntity(reuqestXml, "UTF-8");
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded");
			httpPost.setEntity(reqEntity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				log.info("getStatusLine--->" + JSONObject.toJSONString(response.getStatusLine()));
				if (entity != null) {
					log.info("Response content length--->" + entity.getContentLength());
					result = EntityUtils.toString(entity, "UTF-8");
					log.debug("【微信红包】result--->" + result);
				}
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}

		return result;

	}

	/**
	 * MD5签名
	 * 
	 * @param params
	 * @return
	 */
	public static String signMD5(SortedMap<String, String> params) {
		// 拼接参数字符串
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			// 如果参数的值为空不参与签名；
			if (!StringUtil.isEmpty(value)) {
				sb.append(key + "=" + value + "&");
			}
		}
		sb.append("key=" + Config.WX_APP_SECRET);
		String s = SecurityUtil.MD5(sb.toString()).toUpperCase();
		System.out.println(s);
		return s;
	}

	/**
	 * 获取随机字符串
	 * 
	 * @return
	 */
	public static String getNonceStr() {
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		int len = str.length();
		Random rd = new Random();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 32; i++) {
			int index = rd.nextInt(len);
			sb.append(str.charAt(index));
		}
		return sb.toString();
	}

}
