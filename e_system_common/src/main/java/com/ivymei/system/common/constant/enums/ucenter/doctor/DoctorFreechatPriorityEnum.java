package com.ivymei.system.common.constant.enums.ucenter.doctor;

/**
 * 医生免费咨询优先级配置
 * 
 * @author zhongjl
 * @date 2016年5月20日下午5:13:17
 * @version 1.0
 */
public enum DoctorFreechatPriorityEnum {
	
	DOCTOR_REQUEST_PRIORITY(400, "医生请求优先"),
	BACK_GROUP_SET_PRIORITY(500, "后台设定优先")
	;
	
	private Integer value;
	private String name;
	
	private DoctorFreechatPriorityEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	
	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
}
