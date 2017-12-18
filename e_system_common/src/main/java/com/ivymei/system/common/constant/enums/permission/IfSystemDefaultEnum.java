package com.ivymei.system.common.constant.enums.permission;

public enum IfSystemDefaultEnum {

	NO(0, "否"), //
	YES(1, "是"), //
	;

	private Integer id;
	private String name;

	IfSystemDefaultEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
