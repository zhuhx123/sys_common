package com.ivymei.system.common.constant.enums.ucenter.serviceApply;

public enum ServiceApplyTypeEnum {
	/**
	 * 开通预约挂号
	 */
	KAI_TONG_YU_YUE_GUA_HAO(1,"开通预约挂号"),
	/**
	 * 开通免费咨询
	 */
	KAI_TONG_MIAN_FEI_ZI_XUN(2,"开通免费咨询"),
	/**
	 * 申请义诊任务
	 */
	SHEN_QING_YI_ZHEN_REN_WU(3,"申请义诊任务")
	;

	private Integer value;
	private String name;
	private ServiceApplyTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static ServiceApplyTypeEnum getByValue(Integer value){
		if(value != null){
			for(ServiceApplyTypeEnum m : ServiceApplyTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
