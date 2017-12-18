/**
 * 
 */
package com.ivymei.system.common.constant.enums.common;

/**
 * 是否默认
 * 
 * @author show
 */
public enum IsDefaultEnum {

	YES(1, "是"), NO(0, "否");

	private int id;
	private String name;

	private IsDefaultEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(
			int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(
			String name) {
		this.name = name;
	}

}
