package com.ivymei.system.common.constant.ice;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ivymei.system.common.constant.enums.common.ApiUrlParamEnum;
import com.ivymei.system.common.constant.enums.common.PlatformIdEnum;

/**
 * ICE客户端必传通用对像
 * 
 * @author show
 * 
 */
public class ICEClientInfo {

	private int platformId = PlatformIdEnum.ADMIN.getId();// 平台ID
	private String ip;//
	private String deviceId;// 设备ID

	
	private String os;  // 操作系统"android"，"ios"
	private String systemVersion;  // 操作系统版本
	private String hardwareModel;  // 手机型号
	private String appVersion;  // app版本名称：4.4.1
	private int versionCode;  // app版本的内部编号，整型
	private String token; // 登录凭证
	
	
	public ICEClientInfo() {

	}

	public ICEClientInfo(int platformId, String ip) {
		this.platformId = platformId;
		this.ip = ip;
	}

	public int getPlatformId() {
		return platformId;
	}

	public ICEClientInfo setPlatformId(int platformId) {
		this.platformId = platformId;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public ICEClientInfo setIp(String ip) {
		this.ip = ip;
		return this;
	}
	

	public String getDeviceId() {
		return deviceId;
	}

	public ICEClientInfo setDeviceId(String deviceId) {
		this.deviceId = deviceId;
		return this;
	}

	/**
	 * 创建默认对像
	 * 
	 * @return
	 */
	public static ICEClientInfo createDefault() {
		ICEClientInfo client = new ICEClientInfo(-1, "127.0.0.1");
		return client;
	}

	/**
	 * 根据json解释对像
	 * 
	 * @param json
	 * @return
	 */
	public static ICEClientInfo parse(String json) {
		if (json != null && !"".equals(json)) {
			ICEClientInfo info = JSONObject.parseObject(json, ICEClientInfo.class);
			return info;
		}
		return null;
	}

	/**
	 * 返回json字符串
	 * 
	 * @return
	 */
	public String toJSONString() {
		return JSONObject.toJSONString(this);
	}
	
	/**
	 * 创建入参Map
	 * @return
	 */
	public Map<String, String> createParams(){
		Map<String, String> map = new HashMap<String, String>();
		map.put(ApiUrlParamEnum.ICE_CLINET_INFO.getName(), this.toJSONString());
		return map;
	}

	public static void main(String[] args) {
		ICEClientInfo info = ICEClientInfo.createDefault().setIp("192.168.0.1").setPlatformId(PlatformIdEnum.ADMIN.getId());

		ICEClientInfo info2 = ICEClientInfo.parse(info.toJSONString());
		System.out.println(info2.getIp());

		System.out.println(info.toJSONString());
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getSystemVersion() {
		return systemVersion;
	}

	public void setSystemVersion(String systemVersion) {
		this.systemVersion = systemVersion;
	}

	public String getHardwareModel() {
		return hardwareModel;
	}

	public void setHardwareModel(String hardwareModel) {
		this.hardwareModel = hardwareModel;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getToken() {
		return token;
	}

	public ICEClientInfo setToken(String token) {
		this.token = token;
		return this;
	}


	
	
	
}
