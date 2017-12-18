package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceTransferMoneyBusinessTypeEnum {

	THANKS_MONEY(1,"感谢金"),

	;

	private Integer value;
	private String name;
	private FinanceTransferMoneyBusinessTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static FinanceTransferMoneyBusinessTypeEnum getByValue(Integer value){
		if(value != null){
			for(FinanceTransferMoneyBusinessTypeEnum m : FinanceTransferMoneyBusinessTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
