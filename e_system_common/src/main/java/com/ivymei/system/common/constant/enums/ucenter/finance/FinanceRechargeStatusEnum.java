package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceRechargeStatusEnum {

	PLACE(1,"下单"),
	SUCCESS(2,"确认"),
	FAILURE(-1, "失败")


	;

	private Integer value;
	private String name;
	private FinanceRechargeStatusEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static FinanceRechargeStatusEnum getByValue(Integer value){
		if(value != null){
			for(FinanceRechargeStatusEnum m : FinanceRechargeStatusEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
