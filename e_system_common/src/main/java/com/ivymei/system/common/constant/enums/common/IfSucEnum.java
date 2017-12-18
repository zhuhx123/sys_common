/**
 * 
 */
package com.ivymei.system.common.constant.enums.common;

/**
 *
 * @author luoxl
 */
public enum IfSucEnum {

	SUCCESS(1,"成功"),
	FAILURE(0,"失败");
	
	
	private int id;
	private String name;
	
	private IfSucEnum(int id,String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
