package com.ivymei.framework.util;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * 用于http请求的工具类
 */
public class HttpUtil {

	private static final String CHARSET = "UTF-8";
	private static final int CONNECT_TIME_OUT = 20000;
	private static final int SOCKET_TIME_OUT = 20000;

	// 禁止实例化和继承
	private HttpUtil() {
	}

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	public static String httpGet(String url) {
		return httpRequest(false, "GET", null, url);
	}

	public static String httpGet(String url, Map<String, String> params) {
		return httpRequest(false, "GET", params, url);
	}

	public static String httpsGet(String url) {
		return httpRequest(true, "GET", null, url);
	}

	public static String httpsGet(String url, Map<String, String> params) {
		return httpRequest(true, "GET", params, url);
	}

	public static String httpPost(String url) {
		return httpRequest(false, "POST", null, url);
	}

	public static String httpPost(String url, Map<String, String> params) {
		return httpRequest(false, "POST", params, url);
	}

	public static String httpsPost(String url) {
		return httpRequest(true, "POST", null, url);
	}

	public static String httpsPost(String url, Map<String, String> params) {
		return httpRequest(true, "POST", params, url);
	}

	private static String httpRequest(Boolean isHttps, String method, Map<String, String> params, String url) {
		return baseHttpRequest(isHttps, method, params, url, "application/json");
	}

	private static String baseHttpRequest(Boolean isHttps, String method, Map<String, String> params, String url,
		String accept) {

		long time = System.currentTimeMillis();
		logger.info("========================HTTP REQUEST START=====================");

		String result = null;
		try {
			CloseableHttpClient client = getClient(isHttps);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIME_OUT)
					.setConnectTimeout(CONNECT_TIME_OUT).build();
			CloseableHttpResponse response = null;
			if ("GET".equals(method)) {
				String uri = getUrl(url, params);
				HttpGet get = new HttpGet(uri);
				get.setConfig(requestConfig);
				get.setHeader("Accept", accept);
				response = client.execute(get);
			} else {
				HttpPost post = new HttpPost(url);
				post.setConfig(requestConfig);
				post.setHeader("Accept", accept);

				List<NameValuePair> list = new ArrayList<NameValuePair>();
				if (params != null && !params.isEmpty()) {
					for (Entry<String, String> entry : params.entrySet()) {
						list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
					}
				}

				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, CHARSET);

				// HttpEntity postEntity = new StringEntity(params,
				// contentType);
				post.setEntity(entity);
				response = client.execute(post);
			}
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
					logger.info("url==> " + url + " result==> " + result);
				} else {
					logger.error("Method:" + method + " isHttps: " + isHttps + " params: " + params + " url: " + url
							+ " response is null");
				}
			} finally {
				response.close();
				client.close();
			}
		} catch (Exception e) {
			logger.error("Method:" + method + " isHttps: " + isHttps + " params: " + params + " url: " + url
					+ " request failed", e);
		}

		time = System.currentTimeMillis() - time;
		logger.info("========================HTTP REQUEST END（用时：" + time + "ms）=====================\n");

		return result;
	}

	private static CloseableHttpClient getClient(boolean isHttps) {
		if (isHttps) {
			return Objects.requireNonNull(createSSLInsecureClient());
		}
		return HttpClients.createDefault();
	}

	private static CloseableHttpClient createSSLInsecureClient() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有证书
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (Exception e) {
			logger.error("get ssl client failed", e);
		}
		return null;
	}

	private static String getUrl(String url, Map<String, String> params) {
		StringBuilder requestUrl = new StringBuilder(url);
		if (params != null) {
			int i = 0;
			for (Entry<String, String> entry : params.entrySet()) {
				if (entry.getValue() == null || "".equals(entry.getValue())) {
					continue;
				}
				if (i == 0) {
					if (url.matches(".*\\?.*")) {
						requestUrl.append("&");
					} else {
						requestUrl.append("?");
					}
					requestUrl.append(entry.getKey()).append("=").append(entry.getValue());
				} else {
					requestUrl.append("&").append(entry.getKey()).append("=").append(entry.getValue());
				}
				i++;
			}
		}
		return requestUrl.toString();
	}


	public static void main(String[] args) {
		Object o = null;
		System.out.println(String.valueOf(o));
	}
}
