package com.ivymei.system.common.plugin.ice.server;

/**
 * ICE服务名称（这里定义的名字,在初始化ICE服务以及在调用ICE方法的时候会用到）
 * 
 * @author show
 * 
 */
public enum IceServerEnum {
	//商品中心
	GOODS_CENTER("goodscenter-ice-api"),
	//用户中心
	USER_CENTER("usercenter-ice-api"),
	//订单中心
	ORDER_CENTER("ordercenter-ice-api"),
	//权限中心
	PERMISSION("permission-ice-api"),
	;

	IceServerEnum(String name) {
		this.name = name;
	}

	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
