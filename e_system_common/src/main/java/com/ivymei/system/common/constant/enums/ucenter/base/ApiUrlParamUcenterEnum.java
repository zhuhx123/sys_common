package com.ivymei.system.common.constant.enums.ucenter.base;

public enum ApiUrlParamUcenterEnum {

	PARENT_ID("parentId"),
	TYPE("type"),
	CODE("code"),
	REG_TIME("regTime"),
	ADD_TIME("addTime"),
	BEGIN_TIME("beginTime")
	;
	
	private String name;


	private ApiUrlParamUcenterEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
