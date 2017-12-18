package com.ivymei.system.common.constant.enums.ucenter;

public enum PeaceTimesplotDaytypeEnum {

	MONDAY(1,"周一"),
	TUESDAY(2,"周二"),
	WEDNESDAY(3,"周三"),
	THURSDAY(4,"周四"),
	FRIDAY(5,"周五"),
	SATURDAY(6,"周六"),
	SUNDAY(7,"周日"),
	ALL(100,"全部"),
	WORKDAY(101,"周一至周五"),
	WEEKEND(102,"周六及周日")

	;

	private Integer value;
	private String name;
	private PeaceTimesplotDaytypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static PeaceTimesplotDaytypeEnum getByValue(Integer value){
		if(value != null){
			for(PeaceTimesplotDaytypeEnum m : PeaceTimesplotDaytypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
