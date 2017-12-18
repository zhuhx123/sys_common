package com.ivymei.system.common.constant.enums.ucenter;

public enum UserBaseMobileNumberValidatedEnum {

	WEI_YAN_ZHENG(0,"未验证"),
	YI_YAN_ZHENG(1,"已验证")
	;

	private Integer value;
	private String name;
	private UserBaseMobileNumberValidatedEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static UserBaseMobileNumberValidatedEnum getByValue(Integer value){
		if(value != null){
			for(UserBaseMobileNumberValidatedEnum m : UserBaseMobileNumberValidatedEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
