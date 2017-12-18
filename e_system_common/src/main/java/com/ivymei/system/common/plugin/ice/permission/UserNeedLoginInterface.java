package com.ivymei.system.common.plugin.ice.permission;

import java.util.Map;

import common.business.commandice.model.ResponseMessage;

public interface UserNeedLoginInterface {
	/**
	 * 判断是否已经登录。
	 * @return
	 */
	public ResponseMessage checkLogined(Map<String, String> paramsMap);
	
}
