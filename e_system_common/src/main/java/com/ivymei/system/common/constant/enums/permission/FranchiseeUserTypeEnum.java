package com.ivymei.system.common.constant.enums.permission;

/**
 * 权限的角色类型
 * 
 * @author show
 *
 */
public enum FranchiseeUserTypeEnum {

	FRANCHISEE_MANAGER(1, "加盟商管理员"), //
	CURATOR(2, "医馆馆长"), //
	CLINIC_MANAGER(3, "医馆管理员"), //
	DOCTOR(4, "医生"), //
	NURSE(5, "护士"), //
	PHARMACIST(6, "药师"), //
	OTHERS(7, "其他"), //
	;

	private Integer id;
	private String name;

	FranchiseeUserTypeEnum(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public static FranchiseeUserTypeEnum getByName(String name) {
		for (FranchiseeUserTypeEnum t : FranchiseeUserTypeEnum.values()) {
			if (t.getName().equals(name)) {
				return t;
			}
		}
		return FranchiseeUserTypeEnum.OTHERS;
	}

	public static FranchiseeUserTypeEnum getById(Integer id) {
		for (FranchiseeUserTypeEnum t : FranchiseeUserTypeEnum.values()) {
			if (t.getId().intValue() == id) {
				return t;
			}
		}
		return null;
	}
}
