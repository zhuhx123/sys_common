package com.ivymei.system.common.constant.enums.ucenter;

public enum RegBaseScheduleTypeEnum {
	
//	1、医生排班
//	2、套餐排班
//	3、排班模板
	
	
	DOCTOR_SCHEDULE(1,"医生排班"),
	PROMOTE_SCHEDULE(2,"套餐排班"),
	TEMPLATE_SCHEDULE(2,"排班模板")
	;
	
	private Integer value;
	private String name;
	private RegBaseScheduleTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static RegBaseScheduleTypeEnum getByValue(Integer value){
		if(value != null){
			for(RegBaseScheduleTypeEnum m : RegBaseScheduleTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
