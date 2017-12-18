package com.ivymei.system.common.constant.enums.ucenter;

public enum RegScheduleTypeEnum {
	
//	1、医生排班
//	2、套餐排班
	DOCTOR_SCHEDULE(1,"医生排班"),
	PROMOTE_SCHEDULE(2,"套餐排班")
	
	;
	
	private Integer value;
	private String name;
	private RegScheduleTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static RegScheduleTypeEnum getByValue(Integer value){
		if(value != null){
			for(RegScheduleTypeEnum m : RegScheduleTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
