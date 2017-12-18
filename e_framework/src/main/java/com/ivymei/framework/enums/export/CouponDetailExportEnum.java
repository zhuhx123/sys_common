package com.ivymei.framework.enums.export;

import com.ivymei.framework.util.StringUtil;

/**
 * Created by Nathy on 2017/10/6.
 */
public enum CouponDetailExportEnum {
    SEND("send","赠送"),
    CONSUME("consume","划扣")
    ;
    private String key;
    private String value;

    CouponDetailExportEnum(String key, String value) {
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

    public static CouponDetailExportEnum getByKey(String key) {
        if (!StringUtil.isNullOrBlank(key)) {
            for (CouponDetailExportEnum m : CouponDetailExportEnum.values()) {
                if (m.getKey().equals(key)) {
                    return m;
                }
            }
        }
        return null;
    }
}
