package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceWithdrawWayEnum {

	BANK_TRANSFER(1,"银行转账"),

	;

	private Integer value;
	private String name;
	private FinanceWithdrawWayEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static FinanceWithdrawWayEnum getByValue(Integer value){
		if(value != null){
			for(FinanceWithdrawWayEnum m : FinanceWithdrawWayEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
