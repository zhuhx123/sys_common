package com.ivymei.system.common.constant.enums.ucenter;

public enum CommentTypeEnum {
//	医生评论
//	服务评论
	DOCTOR_COMMENT(1,"医生评论"),
	SERVICE_COMMENT(2,"服务评论")

	;

	private Integer value;
	private String name;
	private CommentTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static CommentTypeEnum getByValue(Integer value){
		if(value != null){
			for(CommentTypeEnum m : CommentTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
