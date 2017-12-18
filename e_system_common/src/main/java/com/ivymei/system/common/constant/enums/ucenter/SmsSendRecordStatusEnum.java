package com.ivymei.system.common.constant.enums.ucenter;

public enum SmsSendRecordStatusEnum {

	/**
	 * 已提交
	 */
	ALREADY_SUBMIT(0,"已提交"),
	/**
	 * 发送成功
	 */
	SEND_SUCCESS(1,"发送成功"),
	/**
	 * 发送失败
	 */
	SEND_FAILURE(2,"发送失败")

	;

	private Integer value;
	private String name;
	private SmsSendRecordStatusEnum(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}

	public static SmsSendRecordStatusEnum getByValue(Integer value){
		if(value != null){
			for(SmsSendRecordStatusEnum m : SmsSendRecordStatusEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
