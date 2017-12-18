package com.ivymei.system.common.constant.enums.permission;

public enum NotNeedAuthorityEnum {

	MAIN("main", "http://admin.permission.jinhuatuo.com/main"), //
	HEADER("header", "http://admin.permission.jinhuatuo.com/header"), //
	REDIRECT("redirect", "http://admin.permission.jinhuatuo.com/redirect"), //
	;

	private String name;
	private String url;

	private NotNeedAuthorityEnum(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public static NotNeedAuthorityEnum getByUrl(String url) {
		for (NotNeedAuthorityEnum e : NotNeedAuthorityEnum.values()) {
			if (e.getUrl().equals(url)) {
				return e;
			}
		}
		return null;
	}
}
