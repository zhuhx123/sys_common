package com.ivymei.system.common.constant.enums.ucenter.doctor;

public enum DoctorQualificationStatusEnum {
	/**
	 * 未认证
	 */
	UNAUTHERIZED(0,"未认证"),
	/**
	 * 认证中
	 */
	AUTHENTICATING(1,"认证中"),
	/**
	 * 通过
	 */
	APPROVED(200,"通过"),
	/**
	 * 不通过
	 */
	DISAPPROVED(-200,"不通过"),
	
	;
	
	private Integer value;
	private String name;
	private DoctorQualificationStatusEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static DoctorQualificationStatusEnum getByValue(Integer value){
		if(value != null && value.intValue() !=0){
			for(DoctorQualificationStatusEnum m : DoctorQualificationStatusEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
