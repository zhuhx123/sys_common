package com.ivymei.system.common.constant.enums.ucenter;

public enum UserBaseTypeEnum {
//	1、医护人员
//	2、患者
//	3、医馆管理员

	/**
	 * 医护人员
	 */
	MEDICAL_STAFF(1,"医护人员"),

	/**
	 * 患者
	 */
	PATIENT(2,"患者"),

	/**
	 * 医馆管理员
	 */
	CLINIC_MANAGER(3,"医馆管理员")

	;

	private Integer value;
	private String name;
	private UserBaseTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static UserBaseTypeEnum getByValue(Integer value){
		if(value != null){
			for(UserBaseTypeEnum m : UserBaseTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
