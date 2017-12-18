package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceWithdrawStatusEnum {

	YI_XIA_DAN(1,"已下单"),
	YI_DAO_CHU(2,"已导出"),
	TI_XIAN_CHENG_GONG(0,"提现成功"),
	CHAO_SHI_SHI_BAI(-1,"超时失败"),
	SHEN_HE_SHI_BAI(-2,"审核失败"),

	;

	private Integer value;
	private String name;
	private FinanceWithdrawStatusEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static FinanceWithdrawStatusEnum getByValue(Integer value){
		if(value != null){
			for(FinanceWithdrawStatusEnum m : FinanceWithdrawStatusEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
