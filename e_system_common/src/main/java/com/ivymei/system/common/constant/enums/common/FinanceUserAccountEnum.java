package com.ivymei.system.common.constant.enums.common;

/**
 * 财务账号ID
 * 
 * @author zhongjl
 * @date 2016年6月2日下午8:35:04
 * @version 1.0
 */
public enum FinanceUserAccountEnum {
	
	SYSTEM_ACCOUNT(1);
	
	
	private Integer value;
	
	FinanceUserAccountEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	
	
	
}
