package com.ivymei.system.common.constant.enums.ucenter;

public enum DoctorServiceServiceTypeEnum {
//	0：表示开通免费服务
//	1:表示开通收费服务
	ENABLE_FREE(0,"开通免费服务"),
	ENABLE_SERVICE(1,"开通收费服务")

	;

	private Integer value;
	private String name;
	private DoctorServiceServiceTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static DoctorServiceServiceTypeEnum getByValue(Integer value){
		if(value != null){
			for(DoctorServiceServiceTypeEnum m : DoctorServiceServiceTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
