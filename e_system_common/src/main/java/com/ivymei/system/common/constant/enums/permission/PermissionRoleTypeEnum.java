package com.ivymei.system.common.constant.enums.permission;

/**
 * 权限的角色类型
 * 
 * @author show
 *
 */
public enum PermissionRoleTypeEnum {

	SYSTEM_ADMIN(1, "后台管理系统", "ADMIN"), // 如：权限系统、商品中心、用户中心、订单中心、定时任务中心等...
	SYSTEM_SAAS(2, "SAAS系统", "SAAS"),
	SYSTEM_OB(3, "OB后台", "OB");

	private Integer id;
	private String name;
	private String key;

	PermissionRoleTypeEnum(Integer id, String name, String key) {
		this.id = id;
		this.name = name;
		this.key = key;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getKey() {
		return key;
	}

	public static PermissionRoleTypeEnum getById(Integer id) {
		if (id == null) {
			return null;
		}
		for (PermissionRoleTypeEnum bean : PermissionRoleTypeEnum.values()) {
			if (bean.getId().intValue() == id) {
				return bean;
			}
		}
		return null;
	}
}
