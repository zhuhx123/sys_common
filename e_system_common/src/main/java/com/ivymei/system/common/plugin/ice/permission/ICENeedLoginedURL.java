package com.ivymei.system.common.plugin.ice.permission;

import java.util.ArrayList;
import java.util.List;

import com.ivymei.system.common.constant.ice.ICEServiceAddressCommon;
/**
 * 需要验证登录的ICE地址。
 * @author kandy
 *
 */
public class ICENeedLoginedURL {
	//用户中心-验证登录的ICE地址
	public static List<String> urlList=new ArrayList<String>();
	static{
		//用户中心
//		urlList.add(build(ICEService.U_COLLECT_BUILD));
//		urlList.add(build(ICEService.U_COLLECT_CANCEL));
//		urlList.add(build(ICEService.U_CREDIT_DELETE));
//		urlList.add(build(ICEService.U_CREDIT_INFO));		
//		urlList.add(build(ICEService.U_CREDIT_LIST));
//		urlList.add(build(ICEService.U_CREDIT_MODIFY));
//		urlList.add(build(ICEService.U_CREDIT_ADD));
//		urlList.add(build(ICEService.U_IM_REGISTER));
//		urlList.add(build(ICEService.U_MEMBER_DELETE));
//		urlList.add(build(ICEService.U_MEMBER_ADD));
//		urlList.add(build(ICEService.U_SETTING_CHANGE_PASSWORD));
//		urlList.add(build(ICEService.U_SETTING_CHANGE_PHONE ));
		
		//活动中心
//		urlList.add(build(ICEService.ACTIVITYSKU_CANCEL));
//		urlList.add(build(ICEService.ACTIVITYSKU_FINISHE));
//		urlList.add(build(ICEService.FAVORITES_ADD));
//		urlList.add(build(ICEService.FAVORITES_DELETE));
//		urlList.add(build(ICEService.FAVORITES_QUERY_USERFAV));
		
		
		//订单中心。
//		urlList.add(build(ICEService.SERVICE_CREATE_USER_ORDER));
//		urlList.add(build(ICEService.SERVICE_CANCEL_USER_ORDER));
//		urlList.add(build(ICEService.SERVICE_GET_USER_ORDER_LIST));
//		urlList.add(build(ICEService.SERVICE_ADD_MY_JOIN));
//		urlList.add(build(ICEService.SERVICE_CREATE_WEIXIN_PREPAY_ID));
//		urlList.add(build(ICEService.SERVICE_CREATE_ALIPAY_PREPAY_ID));
//		urlList.add(build(ICEService.SERVICE_SCAN_QRCODE));
//		urlList.add(build(ICEService.SERVICE_GET_UNSIGNIN_LIST));
//		urlList.add(build(ICEService.SERVICE_GET_SIGNIN_LIST));
//		urlList.add(build(ICEService.SERVICE_FINISH_ACTIVITY_ORDER));
//		urlList.add(build(ICEService.SERVICE_SCAN_QRCODE_BY_TICKET_CODE));
//		urlList.add(build(ICEService.SERVICE_CREATE_ALIPAY_WAP_REQUEST_FORM));
	}
	
	private static String build(String iceUrl){
		return iceUrl+"." + ICEServiceAddressCommon.METHOD_DO;
	}
	
	public static void main(String[] args) {
		/*boolean bb=ucenterUrlList.contains(ICEService.U_AUTH_CHECK_LOGIN);
		System.out.println(bb);*/
	}
}
