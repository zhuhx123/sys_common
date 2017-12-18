package com.ivymei.system.common.constant.enums.permission;

/**
 * Created by 20170331 on 2017/6/23.
 */
public enum IfShowLeftEnum {
    NO(0, "否"), //
    YES(1, "是"), //
    ;

    private Integer id;
    private String name;

    IfShowLeftEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
