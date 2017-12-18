package com.ivymei.system.common.constant.enums.common;

/**
 * 性别
 * 
 * @author liuxh
 *
 */
public enum SexEnum {
	UNKOWN(0, "未知"), //
	MAN(1, "男"), //
	WOMEN(2, "女");
	private int id;
	private String name;

	private SexEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public static boolean have(Integer value) {
		SexEnum sex = getById(value);
		if (sex != null) {
			return true;
		}

		return false;
	}

	public static SexEnum getById(Integer id) {
		if (id != null && id.intValue() != 0) {
			for (SexEnum m : SexEnum.values()) {
				if (m.getId() == id) {
					return m;
				}
			}
		}
		return null;
	}
}
