package com.ivymei.system.common.constant.enums.ucenter;

import java.math.BigDecimal;

public enum VipcardLevelEnum {

	/**
	 * 普通用户
	 */
	PU_TONG_YONG_HU(0, "普通用户", new BigDecimal(1),  new BigDecimal(1),  new BigDecimal(1),  new BigDecimal(1)),
	
	/**
	 * 至尊卡
	 */
	ZHI_ZUN_KA(1, "至尊卡", new BigDecimal(0.5),  new BigDecimal(1),  new BigDecimal(1),  new BigDecimal(0.5)),
	
	/**
	 * 白金卡
	 */
	BAI_JIN_KA(2, "白金卡", new BigDecimal(0.85),  new BigDecimal(0.9),  new BigDecimal(1),  new BigDecimal(0.85)),
	
	/**
	 * 金卡
	 */
	JIN_KA(3, "金卡", new BigDecimal(0.9),  new BigDecimal(0.95),  new BigDecimal(1),  new BigDecimal(0.9)),
	
	/**
	 * 银卡
	 */
	YIN_KA(4, "银卡", new BigDecimal(0.95),  new BigDecimal(0.95),  new BigDecimal(1),  new BigDecimal(0.95))
	;

	private Integer value;
	
	private String name;
	
	/**
	 * 挂号费折扣
	 */
	private BigDecimal registerDiscount;
	
	/**
	 * 中药药费折扣
	 */
	private BigDecimal cmedicineDiscount;
	
	/**
	 * 成药药品折扣
	 */
	private BigDecimal medicineDiscount;
	
	/**
	 * 治疗费折扣
	 */
	private BigDecimal cureDiscount;
	
	
	
	/**
	 * 
	 * @param value
	 * 			会员卡类型标识
	 * @param name
	 * 			会员卡名称
	 * @param registerDiscount
	 * 			挂号费折扣
	 * @param cmedicineDiscount
	 * 			中药费折扣
	 * @param medicineDiscount
	 * 			成药药费折扣
	 * @param cureDiscount
	 * 			治疗费折扣
	 */
	private VipcardLevelEnum(Integer value, String name, BigDecimal registerDiscount
			, BigDecimal cmedicineDiscount , BigDecimal medicineDiscount, BigDecimal cureDiscount){
		this.value = value;
		this.name = name;
		this.registerDiscount = registerDiscount;
		this.cmedicineDiscount = cmedicineDiscount;
		this.medicineDiscount = medicineDiscount;
		this.cureDiscount = cureDiscount;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public String getName() {
		return name;
	}
	
	public BigDecimal getRegisterDiscount() {
		return registerDiscount;
	}

	public BigDecimal getCmedicineDiscount() {
		return cmedicineDiscount;
	}

	public BigDecimal getMedicineDiscount() {
		return medicineDiscount;
	}

	public BigDecimal getCureDiscount() {
		return cureDiscount;
	}
	
	/**
	 * 通过Value获取挂号费折扣
	 * 		【如果没有值，默认折扣为1】
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal getRegisterDiscountByValue(Integer value) {
		if(value != null){
			for(VipcardLevelEnum m : VipcardLevelEnum.values()){
				if(m.getValue().intValue() == value){
					return m.getRegisterDiscount();
				}
			}
		}
		return new BigDecimal(1);
		
	}

	public static String getNameByValue(Integer value) {
		if(value != null){
			for(VipcardLevelEnum m : VipcardLevelEnum.values()){
				if(m.getValue().intValue() == value){
					return m.getName();
				}
			}
		}
		return null;
	}
	
	
	public static VipcardLevelEnum getByValue(Integer value){
		if(value != null){
			for(VipcardLevelEnum m : VipcardLevelEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
