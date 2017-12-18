package com.ivymei.system.common.constant.enums.ucenter;

public enum VipcardStatusEnum {

	/**
	 * 已禁用
	 */
	ALREDAY_DISABLE(-2,"已禁用"),
	/**
	 * 正常
	 */
	COMMON(1,"正常"),
	/**
	 * 已下发
	 */
	ALREDAY_GRANT(2,"已下发"),
	/**
	 * 已绑定
	 */
	ALREDAY_BIND(3,"已绑定"),
	/**
	 * 已挂失
	 */
	ALREDAY_REPORT(4,"已挂失")

	;

	private Integer value;
	private String name;
	private VipcardStatusEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static String getNameByValue(Integer value){
		if(value != null){
			for(VipcardStatusEnum m : VipcardStatusEnum.values()){
				if(m.getValue().intValue() == value){
					return m.getName();
				}
			}
		}
		return null;
	}
	
	public static VipcardStatusEnum getByValue(Integer value){
		if(value != null){
			for(VipcardStatusEnum m : VipcardStatusEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
