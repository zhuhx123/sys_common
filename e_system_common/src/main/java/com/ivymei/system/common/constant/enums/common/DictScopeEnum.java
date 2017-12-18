package com.ivymei.system.common.constant.enums.common;

/**
 * 字典表对应的 scope域说明
 * 		（即Dict表中的scope域）
 * 
 * @author zhongjl
 * @date 2016年6月8日上午10:22:43
 * @version 1.0
 */
public enum DictScopeEnum {
	
	/**
	 * 电话咨询服务挂机类型
	 */
	PHONE_SERVICE_HANGUP_TYPE(1, "PhoneServiceHangupType"),
	

	;

	private Integer value;
	private String name;

	private DictScopeEnum(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public Integer getId() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	
	public static DictScopeEnum getById(Integer id){
		if(id != null){
			for(DictScopeEnum m : DictScopeEnum.values()){
				if(m.getId().equals(id)){
					return m;
				}
			}
		}
		return null;
	}
	
	
	

}
