package com.ivymei.system.common.plugin.ice.client;

import com.ivymei.framework.util.ConfigReaderUtil;
import com.ivymei.system.common.plugin.ice.server.IceServerEnum;

public class ICEClientConnect {

    static {
        //必须将配置文件ice.properties必须放到WEB-INF/classes/
        ConfigReaderUtil.init("configs/ice-client");
    }

    public static void initUcenterClient() {
        String ipStr = ConfigReaderUtil.getValue("API.ice.usercenter.address");
        IceMultiClientFactory.init(IceServerEnum.USER_CENTER.getName(), ipStr);
    }

    public static void initOrderClient() {
        String ipStr = ConfigReaderUtil.getValue("API.ice.ordercenter.address");
        IceMultiClientFactory.init(IceServerEnum.ORDER_CENTER.getName(), ipStr);
    }

    public static void initGcenterClient() {
        String ipStr = ConfigReaderUtil.getValue("API.ice.goodscenter.address");
        IceMultiClientFactory.init(IceServerEnum.GOODS_CENTER.getName(), ipStr);
    }

    public static void initPermissionClient() {
        String ipStr = ConfigReaderUtil.getValue("API.ice.permission.address");
        IceMultiClientFactory.init(IceServerEnum.PERMISSION.getName(), ipStr);
    }
}
