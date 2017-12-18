package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceMarkBusinessTypeEnum {

//	1. 在线咨询
//	2. 电话咨询
//	3. 线下预约

	ZAI_XIAN_ZI_XUN(1,"在线咨询"),
	DIAN_HUA_ZI_XUN(2,"电话咨询"),
	XIAN_XIA_YU_YUE(3,"线下预约"),

	;

	private Integer value;
	private String name;
	private FinanceMarkBusinessTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static FinanceMarkBusinessTypeEnum getByValue(Integer value){
		if(value != null){
			for(FinanceMarkBusinessTypeEnum m : FinanceMarkBusinessTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
