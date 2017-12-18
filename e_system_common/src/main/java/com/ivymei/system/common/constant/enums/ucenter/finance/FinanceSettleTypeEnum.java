package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceSettleTypeEnum {

	/**
	 * 充值
	 */
	RECHARGE(1,"充值"),
	/**
	 * 提现
	 */
	WITHDRAW(2,"提现"),
	/**
	 * 退款
	 */
	REFUND(3, "退款"),
	/**
	 * 业务支付
	 */
	BUSINESS_PAYMENT(4, "业务支付")

	;

	private Integer value;
	private String name;
	private FinanceSettleTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}

	
	public static FinanceSettleTypeEnum getByValue(Integer value){
		if(value != null){
			for(FinanceSettleTypeEnum m : FinanceSettleTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
