package com.ivymei.system.common.constant.enums.ucenter.letter;

/**
 * 随访信息的类型，LetterType = MassType
 */
public enum LetterTypeEnum {

	/**
	 * 测评邀约
	 */
	PHYSICAL_TEST_INVITE(1,"测评邀约"),

	/**
	 * 听课邀约
	 */
	LISTEN_CLASS_INVITE(2,"听课邀约"),

	/**
	 * 出诊时间
	 */
	OUT_CALL_TIME(3,"出诊时间"),

	/**
	 * 复诊邀约
	 */
	RETURN_VISIT_INVITE(4,"复诊邀约"),

	/**
	 * 停诊通知
	 */
	STOP_REGISTER_NOTICE(5,"停诊通知")

	;

	private Integer value;
	private String name;
	private LetterTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static LetterTypeEnum getByValue(Integer value){
		if(value != null){
			for(LetterTypeEnum m : LetterTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
