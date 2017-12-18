package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceRechargeBusinessTypeEnum {

//	1、账户充值
//	2、感谢金
//	3、HIS支付


	ACCOUNT_RECHARGE(1,"账户充值"),
	THANKS_MONRY(2,"感谢金"),
	HIS_PAY(-1, "HIS支付")


	;

	private Integer value;
	private String name;
	private FinanceRechargeBusinessTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static FinanceRechargeBusinessTypeEnum getByValue(Integer value){
		if(value != null){
			for(FinanceRechargeBusinessTypeEnum m : FinanceRechargeBusinessTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
