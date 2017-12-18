package com.ivymei.system.common.constant.enums.common;

public enum FlagEnum {

	NORMAL(1, "正常"), //
	DELETE(-1, "删除"), //
	DISABLE(-2, "禁用"), //

	;

	private int id;
	private String name;

	private FlagEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
	public static FlagEnum getById(Integer id){
		if(id != null){
			for(FlagEnum m : FlagEnum.values()){
				if(m.getId() == id){
					return m;
				}
			}
		}
		return null;
	}
	
	
	

}
