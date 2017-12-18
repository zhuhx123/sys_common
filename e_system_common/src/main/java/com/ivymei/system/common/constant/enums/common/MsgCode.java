/**
 * 
 */
package com.ivymei.system.common.constant.enums.common;

import java.util.HashMap;
import java.util.Map;


public enum MsgCode {
	
	SUCCESSFUL(100,"成功"),
	FAILURE(200, "失败"),
	FAILURE_NO_SHOP(2, "失败"),
	BAD_REQUEST(300,"请求参数有误"),
	CHANGE_ERROR(301, "JSON参数转换错误"),
	PARAMETER_MISSING(302, "参数缺失"),
	/**
	 * 非法请求参数
	 */
	ILLEGAL_REQUEST_PARAMETER(303, "非法请求参数"),
	PERMISSION_DENIED(400, "您的权限不足，无法进行此操作！"),
	ICE_SERVER_ERROR(500,"ICE服务异常"),
	REDIRECT_LOGIN_PAGE(600, "未登录，无法进行此操作！"),
	SYSTEM_ERROR(900,"系统繁忙，请稍后再试"),
	RQUEST_FORBIDDEN(800, "访问请求受限授权"),
	OTHER_ERROR(999,"其它错误"),
	
	OBJECT_NOT_FOUND(1000, "获取对象异常"),
	
	//*************商品中心（以100开头后加三位数，如：100XXX）************//
	G_OBJECT_NOT_EXIST(100001, "对象不存在"),
	G_BAD_STATUS(100002, "状态异常"),
	G_ALREADY_DEFAULT(100003, "已经是默认值"),
	G_SCHEDULED_IS_RUNNINT(100004, "任务还在运行中"),
	G_SCHEDULED_IS_NOT_RUNNINT(100005, "任务未运行"),
	G_IMPORT_FAIL_PART(100006, "部分数据导入失败，点击确定下载失败原因"),//这个代码一定不能改
	G_ERROR_TYPE(100007, "传入类型有误"),
	G_MEDICINE_CODE_ALREADY_EXIST(100008, "药品编码已存在"),
	G_LOAD_AUTOINC_ID_FAILED(100009, "获取自增ID失败"),
	G_SAVE_FAIL_PART(100010, "部分数据保存失败，点击确定下载失败原因"),//这个代码一定不能改
	G_PARSE_FAIL_PART(100011, "部分采购数据解析异常，点击确定下载失败原因"),//这个代码一定不能改
	
	/*************用户中心（以200开头后加三位数，如：200XXX）************/
	U_UPDATE_DATA_ERROR(200001, "数据更新异常"),
	U_STATUS_ERROR(200002, "状态异常"),
	U_GET_INFO_ERROR(200003, "获取信息失败"),
	U_USER_TYPE_ERROR(200004, "不是有效的用户类型"),
	U_NEED_IMG_VALIDATE_CODE(2000100, "需要图形验证码"),									//此代码不能改
	U_IMG_VALIDATE_CODE_ERROR(2000101, "图形验证码输入错误或已过期"),						//此代码不能改
	U_MOBILE_VALIDATE_CODE_ERROR(2000102, "短信验证码输入错误或已过期"),					//此代码不能改
	U_SMS_TEMPLATE_FLAG_ILLEGAL(2000103, "短信模板状态非法"),
	U_USER_FLAG_ERROR(2000104, "该用户已被禁用，请联系客服"),								//此代码不能改
	U_MOBILE_ALREADY_EXIST(2000105, "该手机号码已注册，请直接登录"),
	U_MOBILE_NO_EXIST(2000106, "该手机号码未绑定账号"),
	U_USER_NO_SET_PASSWORD(2000107, "用户没有设置密码，请使用验证码登录"),
	U_TOKEN_NO_EXIST(200200, "未登录或者会话丢失，请先登录"),
	U_BALANCE_INSUFFICIENT(200300, "用户余额不足以支付"),
	U_PUSH_ERROR(201000, "推送失败"),

	/*************订单中心（以300开头后加三位数，如：300XXX）************/
	O_ORDER_NUMS_OVER_LIMITS(300020, "订单数量超限"),
	O_ORDER_STATUS_INVALID(300030, "订单已失效"),
	O_ORDER_VERIFICATION_FAIL(300031, "订单验证不通过"),
	O_ORDER_STATUS_EXCEPTION(300040, "订单状态异常"),
	O_BUSINESS_TYPE_ERROR(300050, "业务订单类型异常"),
	O_ORDER_PRICE_LESS_ZERO(300100, "订单金额必须大于零"),
	O_THIRD_PAY_RETURN_CODE_ERROR(310500, "第三方支付返回数据出错"),
	O_THIRD_PAY_RETURN_CODE_FAIL(310501, "第三方支付调用结果为FAIL"),
	O_THIRD_PAY_SIGN_INVALID(310502, "第三方支付调用结果签名失败"),
	O_THIRD_PAY_FAIL(300503, "第三方支付失败"),
	O_UNKNOW_PAY_WAY(300504, "未知支付类型"),
	O_UNKNOW_ORDER_TYPE(300600, "未知订单类型"),
	O_UNKNOW_RESERVATION_TYPE(300601, "未知预约类型"),
	O_NONSUPPORT_ORDER_TYPE(300602, "不支持的订单类型"),
	O_ORDER_PAID_EXCEPTION(300700, "订单支付异常"),
	O_ORDER_CLINIC_PERMISSION_FORBID(300800, "订单医馆权限受限"),
	;
	
	private static final Map<Integer,MsgCode> map = new HashMap<Integer,MsgCode>();
	static {
		MsgCode[] values = MsgCode.values();
		for(MsgCode msgCode : values){
			MsgCode code = map.get(msgCode.getMsgCode());
			if(code==null){
				map.put(msgCode.getMsgCode(),msgCode);
			}else{
				throw new RuntimeException("MsgCode存在重复code="+code);
			}
		}
	}
	
	private int msgCode;
	private String message;

	private MsgCode(int msgCode, String message) {
		this.msgCode = msgCode;
		this.message = message;
	}

	public int getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(int msgCode) {
		this.msgCode = msgCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	/**
	 * 根据ID获取枚举
	 * @param id
	 * @return
	 */
	public static MsgCode getMsgCode(Integer id){
		MsgCode code = MsgCode.map.get(id);
		if(code!=null){
			return code;
		}else{
			return null;
		}
	}
}
