package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceRechargeWayEnum {

//	1、微信支付
//	2、原生微信支付
//	3、财付通支付
//	4、支付宝支付
//	5、快钱支付
//	10、后台充值

	WEI_XIN_ZHI_FU(1,"微信支付"),
	YUAN_SHENG_WEI_XIN_ZHI_FU(2,"原生微信支付"),
	CAI_WU_TONG_ZHI_FU(3,"财付通支付"),
	ZHI_FU_BAO_ZHI_FU(4,"支付宝支付"),
	KUAI_QIAN_ZHI_FU(5,"快钱支付"),
	HOU_TAI_CHONG_ZHI(10, "后台充值")


	;

	private Integer value;
	private String name;
	private FinanceRechargeWayEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static FinanceRechargeWayEnum getByValue(Integer value){
		if(value != null){
			for(FinanceRechargeWayEnum m : FinanceRechargeWayEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
