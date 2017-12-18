package com.ivymei.system.common.constant.enums.ucenter.cms;

public enum CmsArticleStatusEnum {

	/**
	 * 未审核
	 */
	WEI_SHEN_HE(1,"未审核"),
	/**
	 * 已通过
	 */
	YI_TONG_GUO(2,"已通过"),
	/**
	 * 未通过
	 */
	WEI_TONG_GUO(3,"未通过"),
	/**
	 * 待修改
	 */
	DAI_XIU_GAI(4,"待修改"),
	/**
	 * 已开讲
	 */
	YI_KAI_JIANG(5,"已开讲")

	;

	private Integer value;
	private String name;
	private CmsArticleStatusEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static CmsArticleStatusEnum getByValue(Integer value){
		if(value != null){
			for(CmsArticleStatusEnum m : CmsArticleStatusEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
