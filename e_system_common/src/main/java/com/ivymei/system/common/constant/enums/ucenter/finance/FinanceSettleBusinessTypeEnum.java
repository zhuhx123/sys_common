package com.ivymei.system.common.constant.enums.ucenter.finance;

public enum FinanceSettleBusinessTypeEnum {
    /**
     * 账户余额
     */
    ZHANG_HU_YU_E(90, "账户余额"),
    /**
     * 在线咨询服务
     */
    ZAI_XIAN_ZI_XUN(100, "在线咨询服务"),
    /**
     * 免费咨询升级
     */
    MIAN_FEI_ZI_XUN_SHENG_JI(101, "免费咨询升级"),
    /**
     * 电话咨询
     */
    DIAN_HUA_ZI_XUN(200, "电话咨询服务"),
    /**
     * 家庭医生服务
     */
    JIA_TING_YI_SHENG(300, "家庭医生服务"),
    /**
     * 线下挂号费
     */
    XIAN_XIA_GUA_HAO_FEI(400, "线下挂号费"),
    /**
     * 线下诊疗费
     */
    XIAN_XIA_ZHEN_LIAO_FEI(410, "线下诊疗费"),
    /**
     * 医生提现
     */
    YI_SHENG_TI_XIAN(500, "医生提现"),
    /**
     * 免费咨询感谢金
     */
    MIAN_FEI_ZI_XUN_GAN_XIE_JIN(600, "免费咨询感谢金"),
    /**
     * 拍方抓药
     */
    PAI_FANG_ZHUA_YAO(700, "拍方抓药"),
    /**
     * 调理方案
     */
    TIAO_LI_FANG_AN(800, "调理方案"),
    /**
     * 活动赠送
     */
    HUO_DONG_ZENG_SONG(900, "活动赠送"),
    /**
     * 义诊日奖励咨询
     */
    YI_ZHEN_RI_JIANG_LI_ZI_XUN(1000, "义诊日奖励咨询"),
    /**
     * 网上开方
     */
    WANG_SHANG_KAI_FANG(1100, "网上开方"),
    /**
     * 网上开方咨询费
     */
    WANG_SHANG_KAI_FANG_ZI_XUN_FEI(1101, "网上开方咨询费"),
    /**
     * 医生奖励
     */
    YI_SHENG_JIANG_LI(1200, "医生奖励"),
    /**
     * 处方建议
     */
    CHU_FANG_JIAN_YI(1500, "处方建议")

    ;

    private Integer value;
    private String name;

    private FinanceSettleBusinessTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }


    public static FinanceSettleBusinessTypeEnum getByValue(Integer value) {
        if (value != null) {
            for (FinanceSettleBusinessTypeEnum m : FinanceSettleBusinessTypeEnum.values()) {
                if (m.getValue().intValue() == value) {
                    return m;
                }
            }
        }
        return null;
    }


}
