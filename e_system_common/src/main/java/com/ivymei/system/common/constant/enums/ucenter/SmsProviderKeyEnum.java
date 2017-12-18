package com.ivymei.system.common.constant.enums.ucenter;

public enum SmsProviderKeyEnum {

	/**
	 * 云通讯
	 */
	CLOOPEN("210783866f9c42178294dcfdd2646bbc","云通讯"),

	;

	private String value;
	private String name;
	private SmsProviderKeyEnum(String value, String name){
		this.value = value;
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static SmsProviderKeyEnum getByValue(Integer value){
		if(value != null){
			for(SmsProviderKeyEnum m : SmsProviderKeyEnum.values()){
				if(m.getValue().equals(value)){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
