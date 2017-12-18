package com.ivymei.system.common.constant.enums.ucenter;

public enum UserBaseSubTypeEnum {

	/**
	 * 医生
	 */
	DOCTOR(101, "医生"),

	/**
	 * 技师
	 */
	TECHNICIAN(102, "技师"),

	/**
	 * 护士
	 */
	NURSE(103, "护士"),

	/**
	 * 学徒
	 */
	APPRENTICE(104, "学徒"),

	/**
	 * 普通患者
	 */
	COMMON_PATIENT(201, "普通患者"),

	/**
	 * 评论患者
	 */
	COMMENT_PATIENT(202, "评论患者"),

	/**
	 * 家庭成员
	 */
	FAMILY_MEMBER(203, "家庭成员")

	;

	private Integer value;
	private String name;
	private UserBaseSubTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static UserBaseSubTypeEnum getByValue(Integer value){
		if(value != null){
			for(UserBaseSubTypeEnum m : UserBaseSubTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
