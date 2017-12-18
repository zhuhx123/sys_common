package com.ivymei.system.common.web.exception;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ivymei.system.common.constant.enums.common.MsgCode;

/**
 * 不会滚事务异常（统一，需在Spring配置文件中加入不回滚事务）
 * 须在Spring配置中加入：
 * 	<tx:method name="*" no-rollback-for="ImeiNoRollBackException" isolation="DEFAULT" propagation="REQUIRED"/>
 * 
 * 或者在对应的方法类中加入注释：
 * 	@Transactional(propagation = Propagation.REQUIRED, noRollbackFor=ImeiNoRollBackException.class)
 * 
 * @author zhongjl
 * @date 2016年5月19日下午3:39:07
 * @version 1.0
 */
public class ImeiNoRollBackException extends ImeiException {
	
private static final long serialVersionUID = 3022481503667660854L;
	
	private MsgCode msgCode;
	private String msg;
	private Object result;

	public ImeiNoRollBackException(MsgCode msgCode) {
		super(msgCode);
		this.msgCode = msgCode;
		this.msg = msgCode.getMessage();
	}

	public ImeiNoRollBackException(MsgCode msgCode, String msg) {
		super(msgCode);
		this.msgCode = msgCode;
		this.msg = msg;
	}
	
	public ImeiNoRollBackException(MsgCode msgCode, String msg, Object result) {
		super(msgCode);
		this.msgCode = msgCode;
		this.msg = msg;
		this.result = result;
	}

	public ImeiNoRollBackException(MsgCode msgCode, Object result) {
		super(msgCode);
		this.msgCode = msgCode;
		this.result = result;
	}
	


	public MsgCode getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(MsgCode msgCode) {
		this.msgCode = msgCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	@Override
	public String getMessage() {

		Map<String,Object> obj = new HashMap<String,Object>();
		obj.put("msgCode", msgCode.getMsgCode());

		if (msg != null) {
			obj.put("message", msg);
		} else {
			obj.put("message", msgCode.getMessage());
		}

		if (result != null) {
			obj.put("result", result);
		}
		
		return JSON.toJSONString(obj);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getMessage();
	}
}
