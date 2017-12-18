package com.ivymei.system.common.constant.enums.permission;

import com.ivymei.framework.util.StringUtil;

public enum ProjectKeyEnum {

	GOODS_CENTER("GOODS_CENTER", "商品中心"), //
	USER_CENTER("USER_CENTER", "用户中心"), //
	ORDER_CENTER("ORDER_CENTER", "订单中心"), //
	PERMISSION("PERMISSION", "权限中心"), //
	SCHEDULE("SCHEDULE", "定时任务"), //
	SYSADMIN("SYSADMIN", "站方后台"), //

	SAAS("SAAS", "SAAS系统"), //
	;

	private String projectName;
	private String projectKey;

	ProjectKeyEnum(String projectKey, String projectName) {
		this.projectKey = projectKey;
		this.projectName = projectName;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public static ProjectKeyEnum getByKey(String projectKey) {
		if (!StringUtil.isNullOrBlank(projectKey)) {
			for (ProjectKeyEnum e : ProjectKeyEnum.values()) {
				if (e.getProjectKey().equals(projectKey)) {
					return e;
				}
			}
		}
		return null;
	}
}
