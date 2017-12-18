package com.ivymei.system.common.plugin.ice.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ivymei.framework.util.StringUtil;
import com.ivymei.system.common.constant.ice.IceServerInfo;

public class IceMultiClientFactory {

	private static Map<String, List<IceServerInfo>> iceServerInfos = new HashMap<String, List<IceServerInfo>>();

	public static void init(String serverName, List<IceServerInfo> iceServerInfoList) {

		if (iceServerInfoList == null || iceServerInfoList.size() < 1) {
			throw new IllegalArgumentException("请配置IP与端口。");
		}

		iceServerInfos.put(serverName, iceServerInfoList);

		ICEMultiClient.init(serverName, iceServerInfoList);
	}

	public static void init(String serverName, String ipStr) {
		List<IceServerInfo> serverInfos = new ArrayList<IceServerInfo>();
		if (!StringUtil.isNullOrBlank(ipStr)) {
			serverInfos = new ArrayList<IceServerInfo>();
			String[] ips = ipStr.split(",");
			for (String ip : ips) {
				IceServerInfo iceServerInfo = new IceServerInfo();
				iceServerInfo.setIp(ip.substring(0, ip.indexOf(":")));
				iceServerInfo.setPort(Integer.parseInt(ip.substring(ip.indexOf(":") + 1, ip.length())));
				serverInfos.add(iceServerInfo);
			}
		}
		ICEMultiClient.init(serverName, serverInfos);
	}

}
