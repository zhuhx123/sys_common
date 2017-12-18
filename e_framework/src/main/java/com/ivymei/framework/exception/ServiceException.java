package com.ivymei.framework.exception;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ivymei.framework.util.StringUtil;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3022481503667660306L;

	private int msgCode;
	private String msg;
	private Object data;

	public ServiceException(int msgCode, String msg) {
		this.msgCode = msgCode;
		this.msg = msg;
	}

	public ServiceException(int msgCode, String msg, Object data) {
		this.msgCode = msgCode;
		this.msg = msg;
		this.data = data;
	}

	public ServiceException(int msgCode, Object data) {
		this.msgCode = msgCode;
		this.data = data;
	}

	public int getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(int msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {

		Map<String, Object> obj = new HashMap<String, Object>();
		obj.put("msgCode", msgCode);

		if (StringUtil.isNotBlank(msg)) {
			obj.put("message", msg);
		} else {
			obj.put("message", "");
		}

		if (data != null) {
			obj.put("data", data);
		}

		return JSON.toJSONString(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getMessage();
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
