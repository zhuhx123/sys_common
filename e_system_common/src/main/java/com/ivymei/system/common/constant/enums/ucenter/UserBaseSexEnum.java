package com.ivymei.system.common.constant.enums.ucenter;

public enum UserBaseSexEnum {
	
// 	0: 未知
// 	1: 男
// 	2: 女
	
	UNKNOWN(0,"未知"),
	MALE(1,"男"),
	FEMALE(2,"女")
	
	;
	
	private Integer value;
	private String name;
	private UserBaseSexEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static UserBaseSexEnum getByValue(Integer value){
		if(value != null){
			for(UserBaseSexEnum m : UserBaseSexEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
