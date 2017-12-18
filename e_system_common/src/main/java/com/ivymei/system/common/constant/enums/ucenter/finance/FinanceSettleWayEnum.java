package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceSettleWayEnum {
//	100、现金
//	200、银行
//	300、微信
//	400、支付宝
//	500、原始微信
//	600、POS
//	700、业务核销
//	800、后台充值

	CASH(100,"现金"),
	BANK(200,"银行"),
	WECHAT(300,"微信"),
	ALIPAY(400,"支付宝"),
	ORIGIN_WECHAT(500,"原始微信"),
	POS(600,"POS"),
	YE_WU_HE_XIAO(700,"业务核销"),
	HOU_TAI_CHONG_ZHI(800,"后台充值"),

	;

	private Integer value;
	private String name;
	private FinanceSettleWayEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static FinanceSettleWayEnum getByValue(Integer value){
		if(value != null){
			for(FinanceSettleWayEnum m : FinanceSettleWayEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
