
package com.ivymei.system.common.constant.enums.common;


/**
 * 为了避免API链接参数定义紊乱，故建立此类做统一规范命名
 * 在定义API链接参数之前，一定要查看原先是否已经有定义过相同的参数名，同时这里的参数名和业务无关
 * 避免重复定义，定义后一定要写明参数意思
 *
 */
public enum ApiUrlParamEnum {

	/**客户端请求信息头部**/
	CLINET_INFO("clientInfo"),
	/**客户端请求信息头部**/
	ICE_CLINET_INFO("iceClientInfo"),
	/**客户端请求信息头部**/
	CLIENT_COMMON_PARAM("clientCommonParam"),
	/**分页页码**/
	PAGE_NO("pageNo"),
	/**分页大小**/
	PAGE_SIZE("pageSize"),
	/**令牌*/
	TOKEN("token"),
	/**私钥在redis中的索引key*/
	KEY("key"),
	/**加密串*/
	ENCRYPT("encrypt"),
	/**项目KEY*/
	PROJECT_KEY("projectKey"),
	/**项目VALUE*/
	PROJECT_VALUE("projectValue"),
	/**项目KEY*/
	USER_AGENT("userAgent"),
	/**角色类型*/
	ROLE_TYPE("roleType"),
	/**时间戳*/
	TIMESTAMP("timeStamp"),

	/** id */
	ID("id"),
	/** MENBER_ID */
	MENBER_ID("menberId"),
	/** flag */
	FLAG("flag"),
	/** 备注 */
	REMARK("remark"),
	/** 地区id */
	REGION_ID("regionId"),
	/** 电话号码 */
	MOBILE_NUMBER("mobileNumber"),
	/** 密码扰码 */
	PASSWORD_SALT("passwordSalt"),
	/** 密码MD5加密 */
	PASSWORD_MD5("passwordMd5"),
	/** 明文密码 */
	PASSWORD("password"),
	/** 新明文密码 */
	PASSWORD_NEW("passwordNew"),
	/** 旧明文密码 */
	PASSWORD_OLD("passwordOld"),
	/** 验证码 */
	VALIDATE_CODE("validateCode"),
	/** 邀请码 */
	INVITE_CODE("inviteCode"),
	/** 昵称 */
	NICKNAME("nickname"),
	/** 性别 */
	SEX("sex"),
	/** 年龄 */
	AGE("age"),
	/** 用户姓名 */
	NAME("name"),
	/** 登录名 */
	LOGIN_NAME("loginName"),
	/** 登录名 */
	LOGIN_NAME_NEW("loginNameNew"),
	/** 头像url */
	QUALIFICATION_FILE_URL("qualificationFileUrl"),
	/** 注册平台 */
	SOURCE_REG_PLATFORM("sourceRegPlatform"),
	/** 注册渠道 */
	SOURCE_REG_CHANNEL("sourceRegChannel"),
	/** 服务数组/列表 */
	SERVICE_CATEGORY_IDS("serviceCategoryIds"),
	/** 微信返回的appId */
	APP_ID("appId"),
	/** 微信返回的openId */
	OPEN_ID("openId"),
	/** 微信返回的unionId */
	UNION_ID("unionId"),
	/** 微信昵称 */
	WECHAT_NICKNAME("wechatNickname"),
	/** 家庭成员记录id */
	FAMILY_MEMBER_ID("familyMemberId"),
	/** 用户收货地址Id  */
	USER_DELIVERY_ID("userDeliveryId"),
	/** 医生Id  */
	DOCTOR_ID("doctorId"),
	/** 医生userId  */
	DOCTOR_USER_ID("doctorUserId"),
	/** 服务类别Id  */
	SERVICE_CATEGORY_ID("serviceCategoryId"),
	/** 体质测评问卷Id  */
	QUESTION_ID("questionId"),
	/** 体质测评题目Id  */
	QUESTION_SUBJECT_ID("questionSubjectId"),
	/** 体质测评答案选项Id  */
	QUESTION_OPTIONS_ID("questionOptionsId"),
	/** 体质测评答案Id  */
	ANSWER_ID("answerId"),
	/** 体质测评回答明细Id  */
	ANSWER_ITEM_ID("answerItemId"),
	ITEM_ID("item_id"),
	/** 充值记录Id  */
	FINANCE_RECHARGE_ID("financeRechargeId"),
	/** 转账记录Id  */
	FINANCE_TRANSFER_MONEY_ID("financeTransferMoneyId"),
	/** 处理结果  */
	RESULT("result"),
	/** 外部单号  */
	REFER_CODE("referCode"),
	/** 第三方交易单号  */
	TRANSACTION_ID("transactionId"),
	/** 外部回调内容  */
	TRANSACTION_CONTENT("transactionContent"),
	/** 充值时间  */
	CONFIRM_TIME("confirmTime"),
	/** 途径  */
	WAY("way"),
	/** 标记  */
	TAG("tag"),
	/** 业务类型  */
	BUSINESS_TYPE("businessType"),
	/** 业务参数  */
	BUSINESS_PARAM("businessParam"),
	/** 来源用户Id  */
	FROM_USER_ID("fromUserId"),
	/** 目标用户Id  */
	TARGET_USER_ID("targetUserId"),
	/** 感谢金  */
	THANKS_MONEY("thanksMoney"),
	/** 价格  */
	MONEY("money"),
	/** 内容  */
	CONTENT("content"),
	/** 验证码  */
	VERIFY_CODE("verifyCode"),
	/** 会员卡号  */
	VIPCARD_ID("vipcardID"),
	/** 会员卡号  */
	VIPCARD_NO("vipcardNo"),
	/** 等级  */
	LEVEL("level"),
	/** 银行卡号Id  */
	BANKCARD_ID("BankCardId"),
	/** 银行卡号  */
	BANKCARD_NO("bank_card_no"),
	/** 开始时间段  */
	START_TIME("startTime"),
	/** 结束时间段  */
	STOP_TIME("stopTime"),
	END_TIME("endTIme"),
	E_PRICE("ePrice"),
	PROPERTY("property"),
	S_PRICE("sPrice"),
	/** 关闭信息通知  */
	DISABLE_MESSAGE("disableMessage"),
	/** 消息  */
	MESSAGE("exceptionMessage"),
	/** 关闭电话  */
	DISABLE_PHONE("disablePhone"),
	/** 工作日类型  */
	DAY_TYPE("dayType"),
	/** 邮箱标题  */
	TITLE("title"),
	/** 收件人  */
	TO_MAIL("toMail"),
	/** 模板Id  */
	TEMPLATE_ID("templateId"),
	/** 短信发送参数数组  */
	PARAMS("params"),
	/** 百度推送的设备唯一标识  */
	CHANNEL_ID("channelId"),
	/** 渠道  */
	CHANNEL("channel"),
	/** 百度推送的设备标签  */
	TAG_NAME("tagName"),
	/** 描述  */
	DESCRIPTION("description"),
	/** 定义好的跳转的页面Id枚举值  */
	VIEW_NO("viewNo"),
	/** 推送的设备类型  */
	DEVICE_TYPE("deviceType"),
	/** 是否电话咨询  */
	IS_PHONE_CHAT("isPhoneChat"),
	/** 文章讲座主键  */
	ARTICLE_ID("articleId"),
	/** lenght  */
	LENGHT("lenght"),
	/** 预约排班Id  */
	SCHEDULE_ID("scheduleId"),
	/** 预约排班时间  */
	SCHEDULE_DATE("scheduleDate"),
	/** 预约排班类型  */
	SCHEDULE_TYPE("scheduleType"),
	/** 医馆Id  */
	CLINIC_ID("clinicId"),
	/** 套餐Id  */
	RESERVATION_PROMOTE_ID("reservationPromoteId"),
	/** 套餐类型名称  */
	PROMOTE_TYPE_NAME("promoteTypeName"),
	/** 状态  */
	STATUS("status"),
	PRO_ID("proId"),
	/** 系统通知Id  */
	SYSTEM_MESSAGE_ID("systemMessageId"),
	/** 认证文件url  */
	AUTHENTICATION_FILE_URLS("authenticationFileUrls"),
	/** 档案号 */
	ARCHIVE_NUMBER("archiveNumber"),
	/** 义诊开关 */
	FREE_CHAT_ENABLE("freeChatEnable"),
	/** 开关 */
	ENABLE("enable"),
	/** 搜索条件 */
	QUERY_CONDITION("queryCondition"),
	/** 排序条件 */
	ORDER_PROPERTY("orderProperty"),
	/** 是否升序 */
	ORDER_ASC("orderAsc"),
	/** 评论Id */
	COMMENT_ID("comment_id"),
	/** 是否显示群发信息 */
	SHOW_MASS("showMass"),
	/** 用户类型 */
	USER_TYPE("userType"),

	/** userBase对象 */
	USER_BASE("userBase"),
	/** UserUpdateParamVo对象 */
	USER_UPDATE_PARAM_VO("userUpdateParamVo"),
	/** app注册用户时传入的对象 */
	APP_USER_SAVE_PARAM_VO("appUserSaveParamVo"),
	/** 注册成功后选择医生角色传入的对象 */
	UPDATE_SELECT_DOCTOR_PARAM_VO("updateSelectDoctorParamVo"),
	/** 注册成功后选择患者角色传入的对象 */
	UPDATE_SELECT_PATIENT_PARAM_VO("updateSelectPatientParamVo"),
	/** userBaseExt对象 */
	USER_BASE_EXT("userBaseExt"),
	/** userDelivery对象 */
	USER_DELIVERY("userDelivery"),
	/** userDeliveryParamVo对象 */
	USER_DELIVERY_PARAM_VO("userDeliveryParamVo"),
	/** doctorUpdateParamVo对象 */
	DOCTOR_UPDATE_PARAM_VO("doctorUpdateParamVo"),
	/** financeRechargeParamVo对象 */
	FINANCE_RECHARGE_PARAM_VO("financeRechargeParamVo"),
	/** financeSettleParamVo对象 */
	FINANCE_SETTLE_PARAM_VO("financeSettleParamVo"),
	/** financeMarkParamVo对象 */
	FINANCE_MARK_PARAM_VO("financeMarkParamVo"),
	/** commentParamVo对象 */
	COMMENT_PARAM_VO("commentParamVo"),
	/** comment对象 */
	BANK_CARD_SAVE_PARAM_VO("bankCardSaveParamVo"),
	/** doctorServiceSaveParamVo对象 */
	DOCTORSERVICE_SAVE_PARAM_VO("doctorServiceSaveParamVo"),
	/** chairSaveVo对象 */
	CHAIR_SAVE_VO("chairSaveVo"),
	/** CreateHisAccountVo对象 */
	CREATE_HIS_ACCOUNT_VO("createHisAccountVo"),
	/** familyMemberParamVo对象 */
	FAMILY_MEMBER_PARAM_VO("familyMemberParamVo"),


	//========================================订单中心相关VO值定义==============================================



	//========================================订单中心相关VO值定义==============================================


	/** 商品ID  */
	GOODS_ID("goodsId"),
	/** 用户ID  */
	USER_ID("userId"),
	/** 评价内容*/
	COMMENT("comment"),
	/**图片地址*/
	IMAGE_URL("image_url"),
	/** 图片数组*/
	PIC_ARR("picArr"),
	PIC_ARR_KEY("picArrKey"),
	PIC_ARR_VALUE("picArrValue"),
	SHOP_STARS("shopStars"),
	SERVICE_STARS("serviceStars"),
	BEAUTICIAN_STARS("beauticianStars"),
	/** 用户ID  */
	DELETE_USER_ID("delete_user_id"),
	/** 用户ID  */
	DETAIL_USER_ID("detail_user_id"),
	/**药品ID**/
	MEDICIME_ID("medicineId"),
	/**类型**/
	TYPE("type"),
	GIVE_MONEY("giveMoney"),
	PRICE("price"),
	TOTAL_PRICE("totalPrice"),
	/** 优惠券id*/
	CASH_COUPON_ID("cashCouponId"),
	STAR_TYPE("starType"),
	STARS("stars"),
	/** 子类型 */
	SUB_TYPE("subType"),
	/**输入的关键词**/
	KEYWORD("keyword"),
	BOOKING_METHOD("bookingMethod"),
	/** 套餐id */
	MEAL_ID("mealId"),
	PORT("port"),
	DAY("day"),
	/** 订单Id */
	SERVICE_ID("serviceId"),
	/** 活动标识*/
	CODE("code"),
	LNG("lng"),
	LAT("lat"),
	SHOP_NAME("shopName"),
	/** 服务时间*/
	SERVICE_TIME("serviceTime"),
	/** 活动链接*/
	ACTIVITY_LINK_TAG("activityLinkTag"),
	/** 跟进人*/
	FOLLOW("follow"),
	/**医生服务id**/
	DOCTOR_SERVICE_ID("doctorServiceId"),
	/** 数量*/
	NUM("num"),
	/** 支付方式*/
	PAY_METHOD("payMethod"),
	/**角色ID**/
	ROLE_ID("roleId"),
	/**角色**/
	ROLE("role"),
	/**是否显示全部**/
	IF_SHOW_ALL("ifShowAll"),
	/**是否异常**/
	IF_EXCEPTION("ifException"),
	/**url**/
	URL("url"),
	/**avatarUrl**/
	AVATAR_IMG_URL("avatarImageUrl"),
	/**页面标识符**/
	PAGE_CODE("pageCode"),
	/**模块标识符**/
	SECTION_CODE("sectionCode"),
	/**订单编号*/
	ORDER_NO("orderNo"),
	/**字典类型*/
	DICT_SCOPE("dictScope"),
	/**字典名称*/
	DICT_NAME("dictName"),
	/**开方药材*/
	PRESCRIPTION_TEMPLATE_MEDICINES("prescriptionTemplateMedicines"),
	/**功能主治*/
	MAJOR_FUNCTION("prescriptionTemplateMedicines"),
	/**用法*/
	USAGES("usages"),
	/**适用人群*/
	ADAPT("adapt"),
	/**出处*/
	COME_FROM("comeFrom"),
	/**别名*/
	GENERAL_NAME("generalName"),
	/**加盟商Id*/
	FRANCHISEE_ID("franchiseeId"),
	/**saas系统用户*/
	SAAS_USER_JSON("saasUserJson"),

	/**saas系统登录用户ID（如果是医生登录，则此ID为doctorId）*/
	SAAS_LOGIN_USER_ID("saasLoginUserId"),

	/** SAAS医馆名称 */
	SAAS_CLINIC_ID("clinicId"),

	/**定时任务日志记录Id*/
	RECORD_ID("recordId"),
	/**定时任务Id*/
//	SCHEDULE_ID("scheduleId"),

	NEW_TIME("newTime"),
	IM_TOKEN("imToken"),
	//=====================用户中心相关值定义=====================//


	//=====================SAAS店务系统参数=====================//

	/**年份*/
	YEAR("year"),
	/**商户Id*/
	COMPANY_ID("companyId"),
	/**商户Id缩写*/
	COM_ID("comId"),
	/**门店Id*/
	SHOP_ID("shopId"),
	/**门店Id*/
	SHOP_IDS("shopIds"),
	/**营业开始时间*/
	BEGIN_BUSINESS_TIME("beginTime"),
	/**营业结束时间*/
	END_BUSINESS_TIME("endTime"),
	/**顾客名称*/
	USER_NICK("userNick"),
	/**顾客电话号码*/
	USER_MOBILE("userMobile"),
	/**顾客类别*/
	USER_RID("userRid"),
	/**顾客类别*/
	USER_RIDS("userRids"),
	/**是否导出数据*/
	EXPORT("export"),
	/**顾客生日开始时间*/
	BEGIN_BIRTHDAY("beginBirthday"),
	/**顾客生日结束时间*/
	END_BIRTHDAY("endBirthday"),
	/**到店次数*/
	COUNT_ARRIVAL_SHOP("countArrivalShop"),
	/**顾客编号*/
	USER_SN("userSn"),
	/**订单编号*/
	ORDER_SN("orderSn"),
	/**订单Id*/
	ORDER_ID("orderId"),
	/**品牌Id*/
	BRAND_ID("brandId"),
	/**品牌Id（多个）*/
	BRAND_IDS("brandIds"),
	/**品牌名*/
	BRAND_NAME("brandName"),
	MEMBER("member"),
	/**项目名称*/
	PROJECT_NAME("projectName"),
	/**项目名称*/
	PROJECT_NAMES("projectNames"),
	/**消费类型*/
	CONSUME_TYPE("consumeType"),
	/**消费类型*/
	CONSUME_TYPES("consumeTypes"),
	/**支付类型*/
	PAY_TYPE("payType"),
	/**最近到店时间*/
	RECENT_ARRIVE_TIME("RecentArriveTime"),
	/**操作类型*/
	ACTION_TYPE("actionType"),
	/**优惠券Id*/
	COUPON_ID("couponId"),
	/**优惠券Id*/
	COUPON_IDS("couponIds"),
	/**优惠券号*/
	COUPON_SN("coupon_sn"),
	/**消耗金额开始*/
	CONS_BEGIN("consBegin"),
	/**消耗金额结束*/
	CONS_END("consEnd"),
	/**专属员工*/
	EMP_ID("empId"),
	/**专属员工*/
	EMP_IDS("empIds"),
	/**介绍人*/
	INVITE_NAME("inviteName"),
	/**跟进人Id*/
	FOLLOW_ID("followId"),
	/**跟进人Id*/
	FOLLOW_IDS("followIds"),
	/**最近到店开始时间*/
	RECENT_BEGIN("recentBegin"),
	/**最近到店结束时间*/
	RECENT_END("recentEnd"),
	/**手机号码(必须使用 MIME base64 对数据进行编码)*/
	MOBILE("mobile"),
	/**活动id*/
	ACTIVE_ID("active_id"),
	/**美容师ID*/
	BEAUTICIAN_ID("beautician_id"),
	/**备注*/
	MEMO("memo"),
	/**标签，表示设备的标记*/
	SIGN("sign"),
	/**更新用户性别*/
	SOURCE("source"),
	/**用户id*/
	MEMBER_ID("memberId"),
	/**用户生日*/
	BIRTHDAY("birthday"),
	/**省份id*/
	PROVINCE("province"),
	PROVINCE_ID("provinceId"),
	/**城市id*/
	CITY("city"),
	CITY_ID("cityId"),
	CITY_NAME("cityName"),
	FATHER_ID("fatherId"),
	/**区/镇id*/
	DISTRICT("district"),
	DISTRICT_ID("districtId"),
	/**地址*/
	ADDRESS("address"),
	/**联系人*/
	CONTACTMAN("contactman"),
	/**房号*/
	ROOM("room"),
	/**是否为默认地址*/
	IS_DEFAULT("isDefault"),
	DEFAULT_PIC("defaultPic"),
	/**积分总数*/
//	INTEGRAL("integral"),
	/**总积分数*/
//	TOTAL_COUNT("totalCount"),
	POSITION("position"),
	SUCCESS("success"),
	/**收藏类型*/
	FAVORITE_TYPE("favoriteType"),
	VALUE("value"),
	/**edit=add签到 */
	EDIT("edit"),
	IP("ip"),
	SHOP("shop"),
	PROJECT("project"),
	PROJECT_NUM("projectNum"),
	SHOP_NUM("shopNum"),
	CID("cId"),
	IS_USE("isUse"),
	ADD_TIME("addTime"),
	DOLE_TIME("doleTime"),
	TIME("time"),
	PROJECT_IDS("projectIds"),
	/**联想搜索*/
	SEARCH("search"),
	/**顾客id*/
	SAAS_USER_ID("userId"),
	/**区域id*/
	SHOA_ID("shoaId"),

	//=====================用户中心相关值定义=====================//
	/**类型ID*/
	TYPE_ID("typeId"),
	/**排序方式*/
	SORT("sort"),
	/**排序顺序*/
	SORT_ORDER("sortOrder"),
	/**项目Id**/
	PROJECT_ID("projectId"),

	INTEGRAL("integral"),

	SHOP_START_TIME("shopStartTime"),
	SHOP_END_TIME("shopEndTime"),
	RANKING("ranking"),
	OPERAT("operat"),
	ROOM_NUMBER("roomNumber"),
	HEADIMGURL("headimgurl"),
	IDENTIFY("identify"),
	I_KEY("iKey"),
	;
	private String name;


	private ApiUrlParamEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}

