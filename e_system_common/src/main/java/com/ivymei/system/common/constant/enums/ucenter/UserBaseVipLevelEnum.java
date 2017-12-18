package com.ivymei.system.common.constant.enums.ucenter;

public enum UserBaseVipLevelEnum {
	
	
//	0、普通用户
//	1、至尊卡
//	2、白金卡
//	3、金卡
//	4、银卡
	
	PU_TONG_YONG_HU(0,"普通用户"),
	ZHI_ZUN_KA(1,"至尊卡"),
	BAI_JIN_KA(2,"白金卡"),
	JIN_KA(3,"金卡"),
	YIN_KA(4,"银卡")
	
	;
	
	private Integer value;
	private String name;
	private UserBaseVipLevelEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static UserBaseVipLevelEnum getByValue(Integer value){
		if(value != null){
			for(UserBaseVipLevelEnum m : UserBaseVipLevelEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
