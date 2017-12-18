package com.ivymei.system.common.constant.enums.ucenter;

public enum PhysicalTestResultEnum {
//	"气郁质","湿热质","痰湿质","血瘀质","阴虚质","阳虚质","气虚质","特禀质"

	QI_YU_ZHI(1,"气郁质"),
	SHI_RE_ZHI(2,"湿热质"),
	TAN_SHI_ZHI(3,"痰湿质"),
	XUE_YU_ZHI(4,"血瘀质"),
	YIN_XU_ZHI(5,"阴虚质"),
	YANG_XU_ZHI(6,"阳虚质"),
	QI_XU_ZHI(7,"气虚质"),
	TE_BING_ZHI(8,"特禀质"),
	PING_HE_ZHI(9,"平和质"),

	;

	private Integer value;
	private String name;
	private PhysicalTestResultEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static PhysicalTestResultEnum getByValue(Integer value){
		if(value != null){
			for(PhysicalTestResultEnum m : PhysicalTestResultEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
