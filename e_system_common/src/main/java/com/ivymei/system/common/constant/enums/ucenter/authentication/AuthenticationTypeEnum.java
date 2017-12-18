package com.ivymei.system.common.constant.enums.ucenter.authentication;

public enum AuthenticationTypeEnum {

	/**
	 * 资格认证
	 */
	ZI_GE_REN_ZHENG(1,"资格认证"),

	/**
	 * 职称认证
	 */
	ZHI_CHENG_REN_ZHENG(2,"职称认证")

	;

	private Integer value;
	private String name;
	private AuthenticationTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static AuthenticationTypeEnum getByValue(Integer value){
		if(value != null){
			for(AuthenticationTypeEnum m : AuthenticationTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
