package com.ivymei.system.common.constant.enums.common;

/**
 * Created by Nathy on 2017/9/26.
 */
public enum  ApiUrlEnum {
    CENTER_MESSAGE("/index.php?/Centre/message/t"),
    COMMISSION_INFOREMA_APP("/index.php?/Commission/informa/app"),
    API_MEMBER_CARD_GET_USER_ORDER_SHOP("/Api/MemberCard/getUserOrderShop");


    private String name;

     ApiUrlEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
