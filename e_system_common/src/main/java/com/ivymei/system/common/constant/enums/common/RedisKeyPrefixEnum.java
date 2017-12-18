package com.ivymei.system.common.constant.enums.common;

public enum RedisKeyPrefixEnum {

	LOGIN_TOKEN("login_token", "登录token前缀"), // 对应token
	LOGIN_USER("login_user", "登录用户前缀"), //
	LOGIN_FAIL_NUM("login_fail_num", "登录失败次数前缀"), //
	MOBILE_VALIDATE_CODE("mobile_validate_code", "手机验证码"), //
	IMAGE_VALIDATE_CODE("image_validate_code", "图形验证码"), //
	RSA_KEY("RSA_key", "RSAKey"), //

	/** =================权限系统================== */
	/** 权限系统角色功能URL（如：ROLE_URLS_KEY_{roleId}） */
	ROLE_URLS_KEY("ROLE_URLS_KEY_", "权限系统角色功能URL"), //
	/** 权限系统角色菜单（如：ROLE_MENUS_KEY_{roleId}） */
	ROLE_MENUS_KEY("ROLE_MENUS_KEY_", "权限系统角色菜单"), //

	/** =================权限系统================== */

	/** HIS端session（如：HIS_SESSION_{token}） */
	HIS_SESSION_KEY("HIS_SESSION_", "HIS端session"), //
	PERMISSION_SESSION_KEY("PERMISSION_SESSION_", "权限系统登录session"), //


	/** ====================SAAS系统=======================*/
	REPORT_DATA_CACHE_KEY_PREFIX("REPORT_DATA_CACHE_KEY_{0}", "SAAS系统报表缓存key"),
	;

	private String name;
	private String key;

	private RedisKeyPrefixEnum(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
