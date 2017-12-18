package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceCategoryEnum {

	LAO_YONG_HU_XIAN_JIN_HUI_KUI(1000,"老用户现金回馈"),
	YI_SHENG_TI_XIAN(100001,"医生提现"),
	CHONG_ZHI(100002,"充值"),
	TUI_KUAN(100003,"退款"),
	GAN_XIE_JIN(100009,"免费咨询感谢金"),

	;

	private Integer value;
	private String name;
	private FinanceCategoryEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static FinanceCategoryEnum getByValue(Integer value){
		if(value != null){
			for(FinanceCategoryEnum m : FinanceCategoryEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
