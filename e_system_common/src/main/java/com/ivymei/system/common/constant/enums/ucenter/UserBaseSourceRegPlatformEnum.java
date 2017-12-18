package com.ivymei.system.common.constant.enums.ucenter;

public enum UserBaseSourceRegPlatformEnum {

	// 同用PlatformIdEnum
	UNKNOW(0, "未知"),
	M_WEBSITE(1, "M站"),
	ANDROID(2, "android端"),
	IOS(3, "IOS端"),
	ADMIN(4, "站方后台"),
	HIS(5, "HIS后台"),
	CHANNEL(6, "mall-third第三方合作平台"),
	SAAS(7, "SAAS系统"),


	;
	
	private Integer value;
	private String name;
	private UserBaseSourceRegPlatformEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static UserBaseSourceRegPlatformEnum getByValue(Integer value){
		if(value != null){
			for(UserBaseSourceRegPlatformEnum m : UserBaseSourceRegPlatformEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
