package com.ivymei.system.common.constant.enums.ucenter;

public enum SystemMessageTargetTypeEnum {

	/**
	 * 全部
	 */
	ALL(0,"全部"),
	/**
	 * 医生
	 */
	DOCTOR(1,"医生"),
	/**
	 * 患者
	 */
	PATIENT(2,"患者"),

	;

	private Integer value;
	private String name;
	private SystemMessageTargetTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static String getNameByValue(Integer value){
		if(value != null){
			for(SystemMessageTargetTypeEnum m : SystemMessageTargetTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m.getName();
				}
			}
		}
		return null;
	}
	
	public static SystemMessageTargetTypeEnum getByValue(Integer value){
		if(value != null){
			for(SystemMessageTargetTypeEnum m : SystemMessageTargetTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
