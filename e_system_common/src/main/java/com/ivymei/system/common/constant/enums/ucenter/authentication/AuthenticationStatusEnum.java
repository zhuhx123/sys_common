package com.ivymei.system.common.constant.enums.ucenter.authentication;

public enum AuthenticationStatusEnum {

	/**
	 * 医生提交
	 */
	DOCTOR_SUBMIT(1,"医生提交"),

	/**
	 * 初审通过
	 */
	APPROVED(100,"初审通过"),

	/**
	 * 复审通过
	 */
	REVIEW_APPROVED(200,"复审通过"),

	/**
	 * 初审失败
	 */
	REFUSED(-100,"初审失败"),

	/**
	 * 复审失败
	 */
	REVIEW_REFUSED(-200,"复审失败")

	;

	private Integer value;
	private String name;
	private AuthenticationStatusEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static AuthenticationStatusEnum getByValue(Integer value){
		if(value != null){
			for(AuthenticationStatusEnum m : AuthenticationStatusEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
