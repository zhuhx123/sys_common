package com.ivymei.system.common.constant.enums.ucenter;

public enum RegScheduleStatusEnum {
	
//	1、待发布
//	2、正常
//	3、暂停
//	4、取消
//	5、满号
	
	TO_BE_PUBLISHED(1,"待发布"),
	NORMAL(2,"正常"),
	PAUSE(3,"暂停"),
	CANCEL(4,"取消"),
	FULL_REGISTERED(5,"满号")
	
	;
	
	private Integer value;
	private String name;
	private RegScheduleStatusEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static RegScheduleStatusEnum getByValue(Integer value){
		if(value != null){
			for(RegScheduleStatusEnum m : RegScheduleStatusEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
