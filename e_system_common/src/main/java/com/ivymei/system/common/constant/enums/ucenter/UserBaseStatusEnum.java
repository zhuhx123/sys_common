package com.ivymei.system.common.constant.enums.ucenter;

public enum UserBaseStatusEnum {
	STOPPED(0,"禁用"),
	ENABLED(1,"启用")
	;
	private Integer value;
	private String name;
	private UserBaseStatusEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
}
