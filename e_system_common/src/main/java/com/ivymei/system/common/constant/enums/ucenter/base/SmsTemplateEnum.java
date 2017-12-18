package com.ivymei.system.common.constant.enums.ucenter.base;

public enum SmsTemplateEnum {

	/**
	 * 【金华佗】验证码：{1}，10分钟内有效，请注意保密。
	 */
	B_JIN_HUA_TUO_YAN_ZHENG_MA(1, "65829","金华佗验证码"),
//	/**
//	 * 患者获得优惠券通知
//	 */
//	HUAN_ZHE_HUO_DE_YOU_HUI_QUAN_TONG_ZHI("456","患者获得优惠券通知"),
//
//	/**
//	 * 电话咨询通知医生
//	 */
//	PHONE_CALL_NOTIFY_DOCTOR("456", "电话咨询通知医生");




	;

	private Integer templateId;
	private String templateNo;
	private String name;

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getTemplateNo() {
		return templateNo;
	}

	public void setTemplateNo(String templateNo) {
		this.templateNo = templateNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private SmsTemplateEnum(Integer templateId, String templateNo, String name) {
		this.templateId = templateId;
		this.templateNo = templateNo;
		this.name = name;
	}

	public static SmsTemplateEnum getByTemplateId(Integer templateId){
		if(templateId!=null){
			for(SmsTemplateEnum m : SmsTemplateEnum.values()){
				if(m.getTemplateId().equals(templateId)){
					return m;
				}
			}
		}
		return null;
	}


}
