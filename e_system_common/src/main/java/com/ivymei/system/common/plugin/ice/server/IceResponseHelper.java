package com.ivymei.system.common.plugin.ice.server;

import com.alibaba.fastjson.JSONObject;
import com.ivymei.framework.util.StringUtil;
import com.ivymei.system.common.constant.enums.common.MsgCode;

import common.business.commandice.model.ResponseMessage;

public class IceResponseHelper {

	//private static final SerializerFeature serializerfeatures [] = {SerializerFeature.WriteMapNullValue};
	
	public static ResponseMessage buildSuccessResult(String message) {
		return buildResultMessage(MsgCode.SUCCESSFUL.getMsgCode(), message, null);
	}

	public static ResponseMessage buildSuccessResult(String message, Object data) {
		if (message == null) {
			message = MsgCode.SUCCESSFUL.getMessage();
		}
		return buildResultMessage(MsgCode.SUCCESSFUL.getMsgCode(), message, data);
	}

	public static ResponseMessage buildFaileResult(String message) {
		return buildResultMessage(MsgCode.FAILURE.getMsgCode(), message, null);
	}

	public static ResponseMessage buildFaileResult(String message, Object result) {
		return buildResultMessage(MsgCode.FAILURE.getMsgCode(), message, result);
	}

	public static ResponseMessage buildFaileResult(MsgCode msgCode) {
		return buildResultMessage(msgCode.getMsgCode(), msgCode.getMessage(), null);
	}

	public static ResponseMessage buildFaileResult(MsgCode msgCode, String msg) {
		return buildResultMessage(msgCode.getMsgCode(), msg, null);
	}
	
	public static ResponseMessage buildFaileResult(MsgCode msgCode, String msg,Object result) {
		return buildResultMessage(msgCode.getMsgCode(), msg, result);
	}

	public static ResponseMessage buildFaileResult(MsgCode msgCode, Object result) {
		return buildResultMessage(msgCode.getMsgCode(), msgCode.getMessage(), result);
	}

	public static ResponseMessage buildResultMessage(MsgCode msgCode) {
		return buildResultMessage(msgCode, null);
	}

	public static ResponseMessage buildResultMessage(MsgCode msgCode, Object result) {
		return buildResultMessage(msgCode.getMsgCode(), msgCode.getMessage(), result);
	}

	public static ResponseMessage buildExceptionResult() {
		return buildResultMessage(MsgCode.SYSTEM_ERROR.getMsgCode(), MsgCode.SYSTEM_ERROR.getMessage(), null);
	}

	public static ResponseMessage buildExceptionResult(String message) {
		return buildResultMessage(MsgCode.SYSTEM_ERROR.getMsgCode(), message, null);
	}
	
	public static ResponseMessage buildResultMessage(MsgCode msgCode, String message, Object result) {
		return buildResultMessage(msgCode.getMsgCode(), message, result);
	}

	public static ResponseMessage buildResultMessage(int msgCode, String message, Object result) {
		ResponseMessage resonseMessage = new ResponseMessage();
		resonseMessage.setMsgCode(msgCode);
		if(StringUtil.isNotBlank(message)) {
			resonseMessage.setMessage(message);
		} else {
			resonseMessage.setMessage(MsgCode.getMsgCode(msgCode).getMessage());
		}
		
		if (result != null) {
			//resonseMessage.setResult(JSONObject.toJSONString(result,serializerfeatures));
			resonseMessage.setResult(JSONObject.toJSONString(result));
		} else {
			resonseMessage.setResult("");
		}
		return resonseMessage;
	}
}
