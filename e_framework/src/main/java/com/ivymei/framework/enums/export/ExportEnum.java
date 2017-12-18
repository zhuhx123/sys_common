package com.ivymei.framework.enums.export;

import com.ivymei.framework.util.StringUtil;

/**
 * Created by Nathy on 2017/10/6.
 */
public enum ExportEnum {
    BUY("buy","购买"),
    RECHARGE("recharge","充值"),
    CONSUME("consume","消耗"),
    REPAYMENT("repayment", "还款"),
    ;
    private String key;
    private String value;

    ExportEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public static ExportEnum getByKey(String key) {
        if (!StringUtil.isNullOrBlank(key)) {
            for (ExportEnum m : ExportEnum.values()) {
                if (m.getKey().equals(key)) {
                    return m;
                }
            }
        }
        return null;
    }
}
