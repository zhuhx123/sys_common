package com.ivymei.system.common.constant.enums.ucenter.cms;

public enum CmsBlockEnum {

	/**
	 * 社区资讯
	 */
	SHE_QU_ZI_XUN(1,"社区资讯"),
	/**
	 * 中医知道-资讯
	 */
	ZHONG_YI_ZHI_DAO_ZI_XUN(2,"中医知道-资讯"),
	/**
	 * 中医知道-视
	 */
	ZHONG_YI_ZHI_DAO_SHI_PIN(3,"中医知道-视频"),
	/**
	 * 中医知道-问答
	 */
	ZHONG_YI_ZHI_DAO_WEN_DA(4,"中医知道-问答"),
	/**
	 * 义诊奖励规则
	 */
	YI_ZHEN_JIANG_LI_GUI_ZE(5,"义诊奖励规则"),
	/**
	 * DT安卓升级提示
	 */
	DT_ANDROID_SHENG_JI_TI_SHI(6,"DT安卓升级提示"),
	/**
	 * DT苹果升级提示
	 */
	DT_IOS_SHENG_JI_TI_SHI(7,"DT苹果升级提示"),
	/**
	 * 患者首页轮播
	 */
	HUAN_ZHE_SHOU_YE_LUN_BO(8,"患者首页轮播"),			// 保留
	/**
	 * 医生首页轮播
	 */
	YI_SHENG_SHOU_YE_LUN_BO(9,"医生首页轮播"),			// 保留
	/**
	 * 机构风采
	 */
	JI_GOU_FENG_CAI(10,"机构风采"),
	/**
	 * 医生推送方案
	 */
	YI_SHENG_TUI_SONG_FANG_AN(11,"医生推送方案"),
	/**
	 * 患者推送方案
	 */
	HUAN_ZHE_TUI_SONG_FANG_AN(12,"患者推送方案"),
	/**
	 * 体质测评结果
	 */
	TI_ZHI_CE_PING_JIE_GUO(13,"体质测评结果"),
	/**
	 * 网上开方奖励规则
	 */
	WANG_SHANG_KAI_FANG_JIANG_LI_GUI_ZE(1001,"网上开方奖励规则"),
	/**
	 * 视频讲座
	 */
	SHI_PIN_JIANG_ZUO(2001,"视频讲座"),
	/**
	 * 音频讲座
	 */
	YIN_PIN_JIANG_ZUO(2002,"音频讲座"),
	/**
	 * 电视讲座
	 */
	DIAN_SHI_JIANG_ZUO(2003,"电视讲座")

	;

	private Integer value;
	private String name;
	private CmsBlockEnum(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	
	
	public static CmsBlockEnum getByValue(Integer value){
		if(value != null){
			for(CmsBlockEnum m : CmsBlockEnum.values()){
				if(m.getValue().intValue() == value){
					return m;
				}
			}
		}
		return null;
	}
	
	
}
