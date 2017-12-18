package com.ivymei.system.common.constant.enums.common;

public enum EnabledEnum {
	NO(0, "禁用"), //
	YES(1, "启用"), //
	;

	private int id;
	private String name;

	private EnabledEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static EnabledEnum getById(Integer id) {
		if (id != null) {
			for (EnabledEnum m : EnabledEnum.values()) {
				if (m.getId() == id) {
					return m;
				}
			}
		}
		return null;
	}

}
