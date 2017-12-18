package com.ivymei.system.common.constant.enums.ucenter.browsingHistory;

public enum BrowsingHistoryTypeEnum {

	/**
	 * 访问医生主页
	 */
	BROWSE_DOCTOR_HOME_PAGE(1,"访问医生主页"),

	;

	private Integer value;
	private String name;
	private BrowsingHistoryTypeEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static BrowsingHistoryTypeEnum getByValue(Integer value){
		if(value != null){
			for(BrowsingHistoryTypeEnum m : BrowsingHistoryTypeEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
