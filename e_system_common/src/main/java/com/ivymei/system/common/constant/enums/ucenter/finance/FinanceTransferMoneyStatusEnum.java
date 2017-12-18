package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceTransferMoneyStatusEnum {
//	1. 下单
//	2. 确认
//	-1. 失败

	PLACE(1,"下单"),
	SUCCESS(2,"确认"),
	FAILURE(-1, "失败")

	;

	private Integer value;
	private String name;
	private FinanceTransferMoneyStatusEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static FinanceTransferMoneyStatusEnum getByValue(Integer value){
		if(value != null){
			for(FinanceTransferMoneyStatusEnum m : FinanceTransferMoneyStatusEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
