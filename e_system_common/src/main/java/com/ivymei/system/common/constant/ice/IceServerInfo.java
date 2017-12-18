package com.ivymei.system.common.constant.ice;

/**
 * ICE服务器信息，包括IP与端口。
 * @author HPPC
 *
 */
public class IceServerInfo {
	private String ip;//ICE服务器IP
	private int port;//ICE服务器端口。
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
}
