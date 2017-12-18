package com.ivymei.system.common.constant.enums.common;

/**
 * SQL MAPPER大小判断值 定义
 * 
 * @author zhongjl
 * @date 2016年6月20日下午2:21:58
 * @version 1.0
 */
public enum JudgeCompareEnum {

	EQUAL(0, "相等"), 
	GREATER_OR_EQUAL(1, "大于等于"), 
	LESS_OR_EQUAL(-1, "小于等于"),
	GREATER(2, "大于"),
	LESS(-2, "小于"),

	;

	private Integer value;
	private String name;

	private JudgeCompareEnum(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	public static JudgeCompareEnum getNameById(Integer value){
		if(value != null){
			for(JudgeCompareEnum m : JudgeCompareEnum.values()){
				if(m.getValue().equals(value)){
					return m;
				}
			}
		}
		return null;
	}

}
