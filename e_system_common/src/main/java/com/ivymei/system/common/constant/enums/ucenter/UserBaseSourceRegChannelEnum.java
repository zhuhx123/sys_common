package com.ivymei.system.common.constant.enums.ucenter;

public enum UserBaseSourceRegChannelEnum {
	
//	0、测试1
//	1、测试2
//	2、测试3
	
	// 测试用，待改
	UNKNOWN(0,"测试1"),
	UNKNOWN2(1,"测试2"),
	UNKNOWN3(2,"测试3")
	;
	
	private Integer value;
	private String name;
	private UserBaseSourceRegChannelEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static UserBaseSourceRegChannelEnum getByValue(Integer value){
		if(value != null){
			for(UserBaseSourceRegChannelEnum m : UserBaseSourceRegChannelEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
