package com.ivymei.system.common.constant.enums.ucenter.serviceApply;

public enum ServiceApplyStatusEnum {

	/**
	 * 已提交
	 */
	COMMITTED(1,"已提交"),
	/**
	 * 已通过
	 */
	APPROVED(2,"已通过"),
	/**
	 * 已拒绝
	 */
	REFUSED(3,"已拒绝")
	;

	private Integer value;
	private String name;
	private ServiceApplyStatusEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static ServiceApplyStatusEnum getByValue(Integer value){
		if(value != null){
			for(ServiceApplyStatusEnum m : ServiceApplyStatusEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
