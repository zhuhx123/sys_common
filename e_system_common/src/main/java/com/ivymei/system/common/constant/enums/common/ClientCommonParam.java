package com.ivymei.system.common.constant.enums.common;

import java.util.HashMap;
import java.util.Map;


import com.alibaba.fastjson.JSONObject;

/**
 * ICE客户端必传通用对像
 * 
 * @author show
 * 
 */
public class ClientCommonParam {

	private int platformId = PlatformIdEnum.UNKNOW.getId();// 自有平台ID
	private String deviceId;// 设备ID

	
	private String os;  // 操作系统"android"，"ios"
	private String systemVersion;  // 操作系统版本
	private String hardwareModel;  // 手机型号
	private String appVersion;  // app版本名称：4.4.1
	private int versionCode;  // app版本的内部编号，整型
	private String token; // 登录凭证
	
	/* 客户端实时信息   */
	private String ip;			//ip地址
	
	private String address;     //所在地址
	
	private String longitude;   //经度
	
	private String latitude;	  //纬度
	
	private String channel;		//第三方渠道
	
	
	public ClientCommonParam() {

	}

	public ClientCommonParam(int platformId, String ip) {
		this.platformId = platformId;
		this.ip = ip;
	}

	public int getPlatformId() {
		return platformId;
	}

	public ClientCommonParam setPlatformId(int platformId) {
		this.platformId = platformId;
		return this;
	}

	public String getIp() {
		return ip;
	}

	public ClientCommonParam setIp(String ip) {
		this.ip = ip;
		return this;
	}
	

	public String getDeviceId() {
		return deviceId;
	}

	public ClientCommonParam setDeviceId(String deviceId) {
		this.deviceId = deviceId;
		return this;
	}

	/**
	 * 创建默认对像
	 * 
	 * @return
	 */
	public static ClientCommonParam createDefault() {
		ClientCommonParam client = new ClientCommonParam(-1, "127.0.0.1");
		return client;
	}

	/**
	 * 根据json解释对像
	 * 
	 * @param json
	 * @return
	 */
	public static ClientCommonParam parse(String json) {
		if (json != null && !"".equals(json)) {
			ClientCommonParam info = JSONObject.parseObject(json, ClientCommonParam.class);
			return info;
		}
		return null;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
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
		ClientCommonParam info = ClientCommonParam.createDefault().setIp("192.168.0.1").setPlatformId(PlatformIdEnum.ADMIN.getId());

		ClientCommonParam info2 = ClientCommonParam.parse(info.toJSONString());
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

	public ClientCommonParam setToken(String token) {
		this.token = token;
		return this;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}


	
	
	
}
