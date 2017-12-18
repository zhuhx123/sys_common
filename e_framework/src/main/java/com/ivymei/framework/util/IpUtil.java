package com.ivymei.framework.util;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;



public class IpUtil {
	public static volatile String IP_SINA_API_URL="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";
	private static List<InetAddress> localAddressList;
	
	public static String getLocalIP(){
		return getLocalIP(false);
	}
	public static String getLocalIP(boolean isInter){
		if(localAddressList == null){
			localAddressList = getLocalAddresses();
		}
		String localIP="";
		for(InetAddress ia:localAddressList){
			String ip = ia.getHostAddress();
			if(ia instanceof Inet6Address || ip.startsWith("127")) {
				continue;
			}
			if(StringUtil.isNullOrBlank(localIP)){
				localIP = ip;
			}
			if(isInter && ip.startsWith("19.")){
				return ip;
			} 
			if(!isInter && !ip.startsWith("19.")){
				return ip;
			}
		}
		return localIP;
	}	
	public static List<InetAddress> getLocalAddresses(){
		if(localAddressList == null){
			localAddressList = new ArrayList<InetAddress>();
			try {
				Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
				while (interfaces != null && interfaces.hasMoreElements()) {
					NetworkInterface interfaceN = interfaces.nextElement();
					Enumeration<InetAddress> ienum = interfaceN.getInetAddresses();
					while (ienum.hasMoreElements()) {
						InetAddress ia = ienum.nextElement();
						localAddressList.add(ia);
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return localAddressList;
	}
	

	/**
	 * 请求客户端用户的真实 IP 地址,直接获取之前一个的IP则用request.getRemoteAddr().....
	 * @param request
	 * @return
	 */
	public static String getUserIp(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded-For");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Cdn-Src-Ip");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if(ip.indexOf(",") > -1){
			ip = ip.substring(0,ip.indexOf(","));
		}
		return ip;
	}
}
