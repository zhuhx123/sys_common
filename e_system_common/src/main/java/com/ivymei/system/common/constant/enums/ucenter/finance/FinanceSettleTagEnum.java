package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceSettleTagEnum {
//	1. 正常结算
//	2. 冲账结算

	ZHENG_CHANG(1,"正常结算"),
	CHONG_ZHANG(2,"冲账结算"),

	;

	private Integer value;
	private String name;
	private FinanceSettleTagEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static FinanceSettleTagEnum getByValue(Integer value){
		if(value != null){
			for(FinanceSettleTagEnum m : FinanceSettleTagEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
