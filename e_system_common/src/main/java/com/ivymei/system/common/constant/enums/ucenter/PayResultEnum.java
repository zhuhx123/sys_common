package com.ivymei.system.common.constant.enums.ucenter;

public enum PayResultEnum {

	SUCCESS(1),
	FAILURE(2),
	;

	private Integer value;
	private PayResultEnum(Integer value){
		this.value = value;
	}
	public Integer getValue() {
		return value;
	}

	public static PayResultEnum getByValue(Integer value){
		if(value != null){
			for(PayResultEnum m : PayResultEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
