package com.ivymei.system.common.constant.enums.ucenter;

public enum ReservationPromoteTypeNameEnum {
	
	YI_GE_LIAO_CHENG("一个疗程","一个疗程"),
	DAN_CI("单次","单次")
	;
	
	private String value;
	private String name;
	private ReservationPromoteTypeNameEnum(String value, String name){
		this.value = value;
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static ReservationPromoteTypeNameEnum getByValue(String value){
		if(value != null && value.trim().length() > 0){
			for(ReservationPromoteTypeNameEnum m : ReservationPromoteTypeNameEnum.values()){
				if(m.getValue().equals(value)){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
