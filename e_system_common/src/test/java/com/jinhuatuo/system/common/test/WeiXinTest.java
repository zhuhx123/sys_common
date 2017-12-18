package com.ivymei.system.common.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ivymei.framework.util.HttpUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kandy on 2016/12/15.
 */
public class WeiXinTest {

    /**
     * 111===value:{"expires_in":7200,
     * "access_token":"nLXB8lFeUbupnnYwarDY8-5RnAJtNPW-YPXLFOn3rkgJfJ_dcpfkXymihv5uxqiveRx4G4WdB0i7vzsz0TdsJpDemo0o5OWothoWH0UfoPDusQNUWlj0WlEdhBZGfuJMXLKaAFAPDC"}
     */
    @Test
    public void getAccessToken() {
        String appId = "wx8b01611ac0051146";
        String secret = "70951dba86064fdf1a9127c167ade0a8";

        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appid", appId);
        params.put("secret", secret);
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        String value = HttpUtil.httpGet(url, params);
        System.out.println("value:" + value);
    }

    /**
     * 获取微信服务器IP地址
     */
    @Test
    public void getCallbackIp() {
        Map<String, String> params = new HashMap<>();
        params.put("access_token", "O2p1yYznCAlY7MW7S2H67It31ypPyhfn4YjKRK2iCL672VTrUTcUkGg5qwyV2S1DHSDS7bELYXOZIc8Jj96MlxB0QYVkwZqzwHVgpHwmqp8MZbHBdLJlqQp5tktHeEWuDNMfACAPZY");
        String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip";
        String value = HttpUtil.httpGet(url, params);

        JSONObject jsonObject = (JSONObject) JSON.parse(value);
        JSONArray jsonArray = (JSONArray) jsonObject.get("ip_list");
        System.out.println("value:" + value + ",size:" + jsonArray.get(0));
    }

    /**
     * 获取二维码
     */
    @Test
    public void getQrcode() {
        try {
            String accessToken = "wphMySy2qkVET56Bootg7IeP6_ZJWRfhtbxQz8F4cIte6tjUjyeolP7eu_Jg8nYHO2QvRuNU8eLg652BsdWu0CEF8clHG9p93FNX2VG7fSY1OMuh7jcaJjKLp6l8GI3zQVPiAHAUZT";
            String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
            String json = "{\"expire_seconds\": 604800, \"action_name\": \"hhy\", \"action_info\": {\"scene\": {\"scene_id\": 345}}}";
            String result =HttpPluginTest.postByJson(url,json);
            System.out.println("result:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
