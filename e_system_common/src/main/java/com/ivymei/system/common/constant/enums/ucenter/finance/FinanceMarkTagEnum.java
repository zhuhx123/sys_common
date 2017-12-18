package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceMarkTagEnum {

	XIN_DENG_JI(0,"新登记"),
	YI_HE_XIAO(1,"已核销"),
	YI_QU_XIAO(2,"已取消")

	;

	private Integer value;
	private String name;
	private FinanceMarkTagEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static FinanceMarkTagEnum getByValue(Integer value){
		if(value != null){
			for(FinanceMarkTagEnum m : FinanceMarkTagEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
