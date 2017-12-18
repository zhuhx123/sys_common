package com.ivymei.system.common.plugin.ice.client;

import Ice.ConnectionRefusedException;
import Ice.ObjectPrx;
import com.alibaba.fastjson.JSONObject;
import com.ivymei.system.common.constant.enums.common.MsgCode;
import com.ivymei.system.common.constant.ice.ICEServiceAddressCommon;
import com.ivymei.system.common.constant.ice.IceServerInfo;
import com.ivymei.system.common.plugin.ice.server.IceResponseHelper;
import com.ivymei.system.common.web.exception.ImeiException;
import common.business.commandice.CommandServiceIcePrx;
import common.business.commandice.CommandServiceIcePrxHelper;
import common.business.commandice.model.Command;
import common.business.commandice.model.ResponseMessage;
import org.apache.log4j.Logger;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 多ICE客户端配置表（为了适应一个项目引用多个不同ICE接口，故对ICEClient做此改造）
 *
 * @author show
 */
public class ICEMultiClient {

    // ICE引用，由几个IP组合而成。
    private static Map<String, CommandServiceIcePrx> apiServiceIcePrxs = new HashMap<String, CommandServiceIcePrx>();

    // 超时
    private static int timeOut = 60000;

    private static Logger log = Logger.getLogger(ICEMultiClient.class);

    // // 服务端的标识定义。
    // private static String SERVER_SIGN = "promotion-api:";

    public static void init(String serverName, List<IceServerInfo> serverInfos) {

        try {
            // props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("resources/config/ice.properties"));

            // 创建搜索项目的ICE服务引用。
            String indexTcpUrl = buildTcpUrlFromConfig(serverName, serverInfos);
            CommandServiceIcePrx apiServiceIcePrx = createIcePrx(indexTcpUrl);
            apiServiceIcePrxs.put(serverName, apiServiceIcePrx);

        } catch (Exception e) {
            log.error("抱歉，初始ICE连接时出现异常！", e);
        }
    }

    /**
     * @param ipAndPortConfig 多个IP由分号相隔，IP与端口与冒号相连。链接格式如192.168.0.1:10001;192.168.0.2:10002
     * @param type
     * @param iceName
     * @return
     */
    private static String buildTcpUrlFromConfig(String serverName, List<IceServerInfo> iceServerInfos) {
        if (iceServerInfos == null || iceServerInfos.size() < 1) {
            throw new IllegalArgumentException("抱歉，ICE服务器的IP与端口不能为空！");
        }

        StringBuffer tcpUrl = new StringBuffer();
        tcpUrl.append(serverName + ":");

        for (int i = 0; i < iceServerInfos.size(); i++) {
            if (i != 0) {
                tcpUrl.append(":");// 各个IP以冒号相隔。
            }

            IceServerInfo iceServerInfo = iceServerInfos.get(i);
            String ip = iceServerInfo.getIp();
            String port = iceServerInfo.getPort() + "";

            tcpUrl.append(createTcpUrl(ip, port));
        }
        return tcpUrl.toString();
    }

    /**
     * 通用的创建ICE引用方法。
     *
     * @param serviceSign
     * @param tcpUrl
     * @return
     */
    private static CommandServiceIcePrx createIcePrx(String tcpUrl) {
        // 加载属性文件
        Ice.Properties beforeProperties = Ice.Util.createProperties();
        beforeProperties.setProperty("Ice.MessageSizeMax", "10240");
        Ice.InitializationData initData = new Ice.InitializationData();
        initData.properties = beforeProperties;
        // 1, 初始化环境
        Ice.Communicator ic = Ice.Util.initialize(initData);

        ObjectPrx searchCollectBase = ic.stringToProxy(tcpUrl);
        CommandServiceIcePrx commandServiceIcePrx = CommandServiceIcePrxHelper.uncheckedCast(searchCollectBase);
        return commandServiceIcePrx;
    }

    private static String createTcpUrl(String ip, String port) {
        return " tcp -h " + ip + " -p " + port + " -t " + timeOut;
    }

    /**
     * 调用搜索ICE服务的通用方法。
     *
     * @param serverName  服务名称(初始化时所定义的)
     * @param serviceName ICE service的声明
     * @param methodName  ICE service的方法名
     * @param paramMap
     * @return
     */
    public static ResponseMessage doRequest(String serverName, String serviceName, String methodName,
                                            Map<String, String> paramMap) {
        CommandServiceIcePrx apiServiceIcePrx = getServiceIcePrx(serverName);
        if (apiServiceIcePrx == null) {
            return IceResponseHelper.buildExceptionResult("抱歉，创建连接失败，请先初始ICE服务器IP与端口！");
        }

        Command command = new Command(serviceName + "." + methodName, paramMap);

        log.debug("[" + serverName + serviceName + "]请求参数:" + paramMap.toString());
        ResponseMessage msgReponse = apiServiceIcePrx.doRequest(command);
        log.debug("[" + serverName + serviceName + "]请求返回:" + JSONObject.toJSONString(msgReponse));

        return msgReponse;
    }

    /**
     * 调用搜索ICE服务的通用方法。
     *
     * @param serverName  服务名称(初始化时所定义的)
     * @param serviceName 注意，必须包括执行类的servcie id.
     * @param paramMap
     * @return 服务端接口返回错误码非MsgCode.SUCCESSFUL，会抛出{@link PlatenoException}
     */
    public static ResponseMessage doRequest(String serverName, String serviceName, Map<String, String> paramMap) {
        CommandServiceIcePrx apiServiceIcePrx = getServiceIcePrx(serverName);
        if (apiServiceIcePrx == null) {
            throw new ImeiException(MsgCode.ICE_SERVER_ERROR);
//			return IceResponseHelper.buildExceptionResult("抱歉，创建连接失败，请先初始ICE服务器IP与端口！");
        }

        Command command = new Command(serviceName + "." + ICEServiceAddressCommon.METHOD_DO, paramMap);

        log.debug("[" + serverName + "]【" + serviceName + "】请求参数:" + paramMap.toString());
        ResponseMessage response = null;

        try {
            response = apiServiceIcePrx.doRequest(command);
        } catch (Exception e) {
            if (e instanceof ConnectException || e instanceof ConnectionRefusedException) {
                throw new ImeiException(MsgCode.ICE_SERVER_ERROR);
            }
        }

        log.debug("[" + serverName + "]【" + serviceName + "】请求返回:" + JSONObject.toJSONString(response));

        if (response != null && response.getMsgCode() == MsgCode.SUCCESSFUL.getMsgCode()) {
            return response;
        }

        if (response == null) {
            throw new ImeiException(MsgCode.ICE_SERVER_ERROR);
        }

        MsgCode msgCode = MsgCode.getMsgCode(response.getMsgCode());
        if (msgCode != null) {
            throw new ImeiException(MsgCode.getMsgCode(response.getMsgCode()), response.getMessage(),
                    response.getResult());
        } else {
            throw new ImeiException(MsgCode.OTHER_ERROR, response.getMessage(), response.getResult());
        }

    }

    public static CommandServiceIcePrx getServiceIcePrx(String serverName) {
        return apiServiceIcePrxs.get(serverName);
    }

    /**
     * 创建任务ICE引用
     *
     * @param ip
     * @param port
     * @return
     */
    @SuppressWarnings("unused")
    private static CommandServiceIcePrx createIcePrx(String ip, String port) {
        String urlString = createTcpUrl(ip, port);
        return createIcePrx(urlString);
    }

    public static void main(String[] args) {

        System.exit(-1);
    }

}
