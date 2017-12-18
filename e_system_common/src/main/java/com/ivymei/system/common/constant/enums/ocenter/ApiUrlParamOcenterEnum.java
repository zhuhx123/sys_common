package com.ivymei.system.common.constant.enums.ocenter;

public enum ApiUrlParamOcenterEnum {
	CLINIC_ID("clinicId"),
	ORDER_CREATE_KEY("orderCreateKey"),
	DOCTOR_USER_ID("doctorUserId"),
	/*患者用户id*/
	PATIENT_USER_ID("patientUserId"),
	/*患者手机*/
	PATIENT_PHONE("patientPhone"),
	/*省份*/
	PROVINCE_ID("provinceId"),
	/*城市id*/
	CITY_ID("cityId"),
	/*收货地址*/
	RECEIPT_ADDRESS("receiptAddress"),
	/*支付方式 ，在线支付or货到付款*/
	PAY_MODE("payMode"),
	/*咨询费*/
	CONSULT_FEE("consultFee"),
	/*年龄*/
	AGE("age"),
	/*性别*/
	SEX("sex"),
	
	/*患者名称*/
	PATIENT_NAME("patientUserName"),
	/*来源平台*/
	SOURCE_PLATFORM("sourcePlatform"),
	/**
	 * 订单状态
	 */
	ORDER_STATUS("status"),
	
	/** 
	 * 订单编号  
	 * */
	ORDER_NO("orderNo"),
	
	/**
	 * 来源订单号
	 */
	SOURCE_ORDER_NO("sourceOrderNo"),
	
	/**
	 * 患者用户ID
	 */
	ORDER_PU_ID("puId"),
	
	/**
	 * 医生用户ID
	 */
	ORDER_DU_ID("duId"),
	
	/**
	 * 所属订单类型
	 */
	ORDER_BELONG_TYPE("bType"),
	
	/**
	 * 订单子类型
	 */
	ORDER_SUB_TYPE("sType"),
	
	/**
	 * 通用订单提交参数
	 */
	ORDERS_PARAM_VO("ordersParamVo"),
	
	/**
	 * 支付前参数
	 */
	PRE_PAY_PARAM_VO("prePayParamVo"),
	
	/**
	 * 预约挂号订单提交参数
	 */
	RESERVATION_ORDERS_PARAM_VO("reservationOrdersParamVo"),
	
	/**
	 * 诊断结果
	 */
	ORDER_DIAGNOSE_RESULT("diagnoseResult"),
	
	/**
	 * 症状标签
	 */
	ORDER_COMPLAINT_TAG("complaintTag"),
	
	/**
	 * 自述症状
	 */
	ORDER_COMPLAINT("complaint"),
	
	/**
	 * 订单退款原因，个人填写
	 */
	ORDER_DRAW_BACK_REASON("reason"),
	
	/**
	 * 订单退款类型
	 */
	ORDER_DRAW_BACK_TYPE("drawbackType"),
	
	/**
	 * 退款方式，取值于：OrderRefundModeEnum
	 */
	ORDER_DRAW_BACK_REFUND_MODE("refundMode"),
	
	/**
	 * 订单取消方式，取值于：OrderCancelModeEnum
	 */
	ORDER_DRAW_BACK_CANCEL_MODE("cancelMode"),
	
	/**
	 * 订单操作人， OperationVo
	 */
	ORDER_OPERATORVO("operationVo"),
	
	/**
	 * 日期
	 */
	DATE_TIME("dateTime"),
	
	/**
	 * 订单患者信息参数
	 */
	ORDER_PATIENT_INFO_PARAM("patientInfoParam"),
	
	/**
	 * 病历主诉【证型】
	 */
	ORDER_SYNDROME_TYPE("syndromeType"),
	
	
	/**
	 * 病历主诉诊断信息， medicalMain
	 */
	MEDICAL_MAIN("medicalMain"),
	
	/**
	 * 保存主诉基础信息
	 */
	MEDICAL_MAIN_VO("medicalMainVo"),
	
	/**
	 * 患者基础信息
	 */
	PATIENT_BASE_INFO_VO("patientInfoVo"),
	
	;
	
	private String name;


	private ApiUrlParamOcenterEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
