package com.ivymei.system.common.constant.enums.ucenter.doctor;

public enum DoctorTitleEnum {
	
//	0、未知
//	1、主任医生
//	2、副主任医生
//	3、主治医生
//	4、医师
//	5、实习生
//	6、医学生
//	7、其他
	
	UNKNOWN(0,""),				// 方便显示，未知返回空字串
	ZHU_REN_YI_SHENG(1,"主任医生"),
	FU_ZHU_REN_YI_SHENG(2,"副主任医生"),
	ZHU_ZHI_YI_SHENG(3,"主治医生"),
	YI_SHI(4,"医师"),
	SHI_XI_SHENG(5,"实习生"),
	YI_XUE_SHENG(6,"医学生"),
	OTHER(7,"其他")
	;
	
	private Integer value;
	private String name;
	private DoctorTitleEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static DoctorTitleEnum getByValue(Integer value){
		if(value != null){
			for(DoctorTitleEnum m : DoctorTitleEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
