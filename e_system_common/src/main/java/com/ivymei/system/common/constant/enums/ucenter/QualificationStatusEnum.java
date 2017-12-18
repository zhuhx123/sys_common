package com.ivymei.system.common.constant.enums.ucenter;

public enum QualificationStatusEnum {
//	1. 医生提交
//	200. 通过
//	-200 未通过

	DOCTOR_SUBMIT(1,"医生提交"),
	APPROVED(200,"通过"),
	REFUSED(-200,"未通过")
	;

	private Integer value;
	private String name;
	private QualificationStatusEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static QualificationStatusEnum getByValue(Integer value){
		if(value != null){
			for(QualificationStatusEnum m : QualificationStatusEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
