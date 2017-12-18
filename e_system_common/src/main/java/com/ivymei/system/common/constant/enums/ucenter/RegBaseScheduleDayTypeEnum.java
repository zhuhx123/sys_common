package com.ivymei.system.common.constant.enums.ucenter;

public enum RegBaseScheduleDayTypeEnum {
	
//	1：周一
//	2：周二
//	3：周三
//	4：周四
//	5：周五
//	6：周六
//	7：周日	
	
	
	MONDAY(1,"周一"),
	TUESDAY(2,"周二"),
	WEDNESDAY(3,"周三"),
	THURSDAY(4,"周四"),
	FRIDAY(5,"周五"),
	SATURDAY(6,"周六"),
	SUNDAY(7,"周日"),
	;
	
	private Integer value;
	private String name;
	private RegBaseScheduleDayTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static RegBaseScheduleDayTypeEnum getByValue(Integer value){
		if(value != null){
			for(RegBaseScheduleDayTypeEnum m : RegBaseScheduleDayTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
