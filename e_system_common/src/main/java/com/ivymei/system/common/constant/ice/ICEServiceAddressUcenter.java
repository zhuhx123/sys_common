package com.ivymei.system.common.constant.ice;

public class ICEServiceAddressUcenter {


	private final static String UCENTER_API_PREFFIX = "ucenter.ice.api.";// 用户中心接口前缀



	// *****************用户中心接口 start*********************//
	public final static String UCENTER_GET_ENCRYPTED_KEY = UCENTER_API_PREFFIX + "get.encrypted.key";				// 获取RSA公私钥信息
	public final static String UCENTER_APP_PWD_LOGIN = UCENTER_API_PREFFIX + "app.pwd.login";						// APP密码登录
	public final static String UCENTER_APP_VERIFY_CODE_LOGIN = UCENTER_API_PREFFIX + "app.verifyCode.login";		// APP短信验证码登录
	public final static String UCENTER_WECHAT_LOGIN = UCENTER_API_PREFFIX + "wechat.register";						// M站登录
	public final static String UCENTER_APP_RESET_PASSWORD = UCENTER_API_PREFFIX + "app.reset.password";			// APP重置密码
	public final static String UCENTER_CHECK_LOGIN = UCENTER_API_PREFFIX + "check.login";							// 校验登录

	public final static String UCENTER_GET_USER_ID_BY_TOKEN = UCENTER_API_PREFFIX + "getUserId.byToken";			// 根据token获取用户Id
	public final static String UCENTER_REFRESH_TOKEN = UCENTER_API_PREFFIX + "refresh.token";						// 刷新token
	public final static String UCENTER_DESTORY_TOKEN = UCENTER_API_PREFFIX + "refresh.destory";					// 销毁token

	public final static String UCENTER_CREATE_USER_FROM_APP = UCENTER_API_PREFFIX + "create.user.from.app";				// APP创建用户
	public final static String UCENTER_UPDATE_SELECT_DOCTOR = UCENTER_API_PREFFIX + "update.select.doctor";				// APP注册后选择医生角色
	public final static String UCENTER_UPDATE_SELECT_PATIENT = UCENTER_API_PREFFIX + "update.select.patient";				// APP注册后选择患者角色

	public final static String UCENTER_CREATE_USER_FROM_WECHAT = UCENTER_API_PREFFIX + "create.user.from.wechat";		// 微信创建用户

	public final static String UCENTER_USER_PAGE_INFO = UCENTER_API_PREFFIX + "user.page.info";							// 患者端“我”页面
	public final static String UCENTER_MY_PATIENTS_AND_FANS_LIST = UCENTER_API_PREFFIX + "my.patients.and.fans.list";	// 医生端“患者与粉丝”列表
	public final static String UCENTER_GET_USER_BY_USER_ID = UCENTER_API_PREFFIX + "get.user.by.userId";					// 通过userId获取用户
	public final static String UCENTER_USER_DETAIL = UCENTER_API_PREFFIX + "user.detail";									// App用户详情
	public final static String UCENTER_USER_UPDATE = UCENTER_API_PREFFIX + "user.update";									// 修改用户信息
	public final static String UCENTER_USER_QUERY_LIST = UCENTER_API_PREFFIX + "user.query.list";							// 查询用户列表
	public final static String UCENTER_GET_USER_PASSWORD_SALT = UCENTER_API_PREFFIX + "get.user.pwd.salt";				// 获取用户密码扰码
	public final static String UCENTER_MASS_USER_LIST = UCENTER_API_PREFFIX + "mass.user.list";							// 群发列表的患者和粉丝列表

	public final static String UCENTER_GET_USER_SSO_BY_USER_ID = UCENTER_API_PREFFIX + "get.userSso.by.userId";			// 通过用户Id获取外部账号

	public final static String UCENTER_CHECK_MOBILE_NUMBER = UCENTER_API_PREFFIX + "check.mobileNumber";					// 检测用户是否绑定了手机号
	public final static String UCENTER_BIND_MOBILE_NUMBER = UCENTER_API_PREFFIX + "bind.mobileNumber";					// 用户绑定手机号

	public final static String UCENTER_ADD_FEEDBACK = UCENTER_API_PREFFIX + "feedback.add";								// 增加用户反馈（吐槽产品经理）

	public final static String UCENTER_CREATE_FAMILY_MEMBER = UCENTER_API_PREFFIX + "create.familyMember";						// 创建家庭成员用户
	public final static String UCENTER_DELETE_FAMILY_MEMBER = UCENTER_API_PREFFIX + "delete.familyMember";						// 删除家庭成员用户
	public final static String UCENTER_FAMILY_MEMBER_LIST_BY_USERID = UCENTER_API_PREFFIX + "familyMember.list.byUserId";		// 用户的家庭成员列表
	public final static String UCENTER_PATIENT_AND_FAMILY_MEMBER_INFO = UCENTER_API_PREFFIX + "patient.and.familyMember.info";	// 用户和其家庭成员信息

	public final static String UCENTER_FOLLOW_USER_TOGGLE = UCENTER_API_PREFFIX + "followUser.toggle";			// 关注或取消关注医生
	public final static String UCENTER_FOLLOW_USER_LIST = UCENTER_API_PREFFIX + "followUser.list";				// 返回用户关注的医生的列表【2.4后只有“我的医生”列表，此接口无用】
	public final static String UCENTER_DOCTOR_FANS_LIST = UCENTER_API_PREFFIX + "doctor.fans.list";				// 医生端—“关注我”列表
	public final static String UCENTER_DOCTOR_VISITOR_LIST = UCENTER_API_PREFFIX + "doctor.visitor.list";			// 医生端—“看过我”列表

	public final static String UCENTER_DELIVERY_LIST = UCENTER_API_PREFFIX + "userDelivery.list";					// 获取用户的收货地址列表
	public final static String UCENTER_DELIVERY_SAVE = UCENTER_API_PREFFIX + "userDelivery.save";					// 保存用户收货地址
	public final static String UCENTER_DELIVERY_SET_DEFAULT = UCENTER_API_PREFFIX + "userDelivery.setDefault";	// 设置默认收货地址
	public final static String UCENTER_DEL_DELIVERY = UCENTER_API_PREFFIX + "del.userDelivery";					// 删除收货地址

	public final static String UCENTER_DOCTOR_PAGE_INFO = UCENTER_API_PREFFIX + "doctor.page.info";									// 医生“我”页面数据
	public final static String UCENTER_DOCTOR_HOME_PAGE = UCENTER_API_PREFFIX + "doctor.home.page";									// 医生主页数据
	public final static String UCENTER_RECOMMEND_DOCTOR_LIST = UCENTER_API_PREFFIX + "recommend.doctor.list";							// 患者端获取推荐医生列表
	public final static String UCENTER_MY_DOCTOR_LIST = UCENTER_API_PREFFIX + "my.doctor.list";										// 患者端—我的医生列表
	public final static String UCENTER_DOCTOR_COMBO_LIST = UCENTER_API_PREFFIX + "doctor.combo.list";									// 医生下拉框列表
	public final static String UCENTER_DOCTOR_ADD_NUM_SERVICE = UCENTER_API_PREFFIX + "doctor.add.numService";						// 新增医生服务购买数
	public final static String UCENTER_DOCTOR_ADD_NUM_SERVICE_REGISTER = UCENTER_API_PREFFIX + "doctor.add.numServiceRegister";		// 新增医生挂号购买数
	public final static String UCENTER_GET_DOCTOR_BY_USER_ID = UCENTER_API_PREFFIX + "get.doctor.by.userId";							// 通过userId获取医生
	public final static String UCENTER_GET_DOCTOR_COUNT = UCENTER_API_PREFFIX + "get.doctor.count";									// 获取已验证医生总数
	public final static String UCENTER_LOAD_DOCTOR_INFO = UCENTER_API_PREFFIX + "load.doctor.info";									// 加载医生个人资料
	public final static String UCENTER_UPDATE_DOCTOR = UCENTER_API_PREFFIX + "update.doctor";											// 修改医生个人资料
	public final static String UCENTER_DOCTOR_LIST_FOR_PATIENT = UCENTER_API_PREFFIX + "doctor.list.for.patient";					// 患者端医生列表

	public final static String UCENTER_DOCTOR_SERVICE_LIST = UCENTER_API_PREFFIX + "doctorService.list";								// 医生服务列表
	public final static String UCENTER_DOCTOR_SERVICE_SAVE_OR_UPDATE = UCENTER_API_PREFFIX + "doctorService.save.or.update";			// 增加或更新医生服务
	public final static String UCENTER_FREE_SERVICE_SAVE_OR_UPDATE = UCENTER_API_PREFFIX + "freeService.save.or.update";				// 增加或更新医生免费咨询
	public final static String UCENTER_CHECK_DOCTOR_SERVICE = UCENTER_API_PREFFIX + "check.doctorService";							// 医生是否可以添加该服务
	public final static String UCENTER_DELETE_DOCTOR_SERVICE = UCENTER_API_PREFFIX + "delete.doctorService";							// 删除医生服务
	public final static String UCENTER_GET_BY_DOCTOR_SERVIEC_ID = UCENTER_API_PREFFIX + "get.by.doctorServiecId";					// 获取医生服务
	public final static String UCENTER_GET_MOST_EXPENSIVE_SERVICE = UCENTER_API_PREFFIX + "get.most.expensive.service";				// 获取某医生某类别最贵的服务
	public final static String UCENTER_DOCTOR_SERVICE_ADD_NUM_SERVICE = UCENTER_API_PREFFIX + "doctorService.add.numService";		// 增加服务购买数

	public final static String UCENTER_SERVICE_CATEGORY_LIST = UCENTER_API_PREFFIX + "message.category.list";				// 服务类别列表
	public final static String UCENTER_LOAD_SERVICE_CATEGORY = UCENTER_API_PREFFIX + "load.message.category";				// 选择服务后加载其信息

	public final static String UCENTER_REGION_LIST_BY_REGIONID = UCENTER_API_PREFFIX + "region.list.byRegionId";			// 地区列表

	public final static String UCENTER_PHYSICAL_TEST_QUESTION_LIST = UCENTER_API_PREFFIX + "physicalTest.question.list";
	public final static String UCENTER_PHYSICAL_TEST_QUESTION_SUBJECT = UCENTER_API_PREFFIX + "physicalTest.questionSubject";
	public final static String UCENTER_PHYSICAL_TEST_SIMPLE_QUESTION_SUBJECT = UCENTER_API_PREFFIX + "physicalTest.simple.questionSubject";
	public final static String UCENTER_PHYSICAL_TEST_ANSWER_SAVE = UCENTER_API_PREFFIX + "physicalTest.physicalTest.save";
	public final static String UCENTER_PHYSICAL_TEST_ANSWER_LIST_BY_USER_ID = UCENTER_API_PREFFIX + "physicalTest.physicalTest.list.byUserId";
	public final static String UCENTER_PHYSICAL_TEST_ANSWER_VIEW = UCENTER_API_PREFFIX + "physicalTest.physicalTest.view";
	public final static String UCENTER_PHYSICAL_TEST_ANSWER_ITEM_SAVE_OR_UPDATE = UCENTER_API_PREFFIX + "physicalTest.answerItem.saveOrUpdate";
	public final static String UCENTER_GET_ANSWER_RESULT = UCENTER_API_PREFFIX + "get.physicalTest.result";											// 获取体质测评结果信息

	public final static String UCENTER_FINANCE_RECHARGE_ADD = UCENTER_API_PREFFIX + "finance.recharge.add";											// 创建充值记录
	public final static String UCENTER_FINANCE_RECHARGE_BIND_REFER_CODE = UCENTER_API_PREFFIX + "finance.recharge.bindReferCode";					// 绑定充值订单的外部单号
	public final static String UCENTER_FINANCE_RECHARGE_UPDATE = UCENTER_API_PREFFIX + "finance.recharge.update";									// 充值后回调
	public final static String UCENTER_FINANCE_RECHARGE_GET_BY_REFER_CODE = UCENTER_API_PREFFIX + "finance.recharge.get.by.referCode";				// 通过外部单号获取充值记录
	public final static String UCENTER_FINANCE_RECHARGE_GET_BY_TRANSACTION_ID = UCENTER_API_PREFFIX + "finance.recharge.get.by.transactionId";		// 通过第三方订单号获取记录

	public final static String UCENTER_TRANSFER_MONEY_ADD = UCENTER_API_PREFFIX + "transferMoney.add";
	public final static String UCENTER_TRANSFER_MONEY_UPDATE = UCENTER_API_PREFFIX + "transferMoney.udpate";

	public final static String UCENTER_GET_FINANCE_ACCOUNT = UCENTER_API_PREFFIX + "get.financeAccount";							// 获取用户财务账号
	public final static String UCENTER_DOCTOR_FINANCE_INFO = UCENTER_API_PREFFIX + "doctor.finance.info";							// 医生的财务信息
	public final static String UCENTER_FINANCE_ACCOUNT_CHECK = UCENTER_API_PREFFIX + "financeAccount.check";						// 判断用户是否可以支付
	public final static String UCENTER_FINANCE_SETTLE_ADD = UCENTER_API_PREFFIX + "financeSettle.add";
	public final static String UCENTER_FINANCE_SETTLE_LIST_BY_USER = UCENTER_API_PREFFIX + "financeSettle.list.byUserId";
	public final static String UCENTER_FINANCE_MARK_ADD = UCENTER_API_PREFFIX + "financeMark.add";								// 添加应收付记录
	public final static String UCENTER_FINANCE_MARK_VERIFICATION = UCENTER_API_PREFFIX + "financeMark.verification";				// 核销应收付记录
	public final static String UCENTER_FINANCE_MARK_CANCLE = UCENTER_API_PREFFIX + "financeMark.cancle";							// 取消应收付记录
	public final static String UCENTER_FINANCE_WITHDRAW_ADD = UCENTER_API_PREFFIX + "financeWithdraw.add";

	public final static String UCENTER_ADD_LOGIN_LOG = UCENTER_API_PREFFIX + "add.login.log";							// 记录登录日志

	public final static String UCENTER_COMMENT_LIST_FOR_DOCTOR = UCENTER_API_PREFFIX + "comment.list.for.doctor";	// 医生端评论列表
	public final static String UCENTER_COMMENT_LIST_FOR_PATIENT = UCENTER_API_PREFFIX + "comment.list.for.patient";	// 患者端评论列表
	public final static String UCENTER_COMMENT_ADD = UCENTER_API_PREFFIX + "comment.add";								// 添加评论
	public final static String UCENTER_COMMENT_DEL = UCENTER_API_PREFFIX + "comment.del";								// 删除评论
	public final static String UCENTER_GET_DOCTOR_COMMENT_INFO = UCENTER_API_PREFFIX + "get.doctor.comment.info";	// 获取医生评论信息

	public final static String UCENTER_VIPCARD_BIND = UCENTER_API_PREFFIX + "vipcard.bind";					// 绑定会员卡
	public final static String UCENTER_GET_VIPCARD_INFO = UCENTER_API_PREFFIX + "get.vipcard.info";			// 获取用户会员卡信息
	public final static String UCENTER_GET_VIPCARD_BY_CODE = UCENTER_API_PREFFIX + "get.vipcard.by.code";		// 通过会员卡号获取会员卡信息
	public final static String UCENTER_GET_VIPCARD_LIST = UCENTER_API_PREFFIX + "get.vipcard.list";			// 获取会员卡号列表
	public final static String UCENTER_VIPCARD_UPDATE_RESERVED_MOBILENUMBER = UCENTER_API_PREFFIX + "vipcard.list.update.reserved.mobileNumber";		// 更新预留手机号
	public final static String UCENTER_VIPCARD_FORBIDDEN = UCENTER_API_PREFFIX + "vipcard.forbidden";			// 禁用会员卡
	public final static String UCENTER_VIPCARD_UPGRADE = UCENTER_API_PREFFIX + "vipcard.upgrade";				// 升级会员卡
	public final static String UCENTER_VIPCARD_SENDDOWN = UCENTER_API_PREFFIX + "vipcard.senddown";			// 会员卡下发
	public final static String UCENTER_VIPCARD_REPORT_LOST = UCENTER_API_PREFFIX + "vipcard.report.lost";		// 挂失会员卡
	public final static String UCENTER_VIPCARD_FOUND_BACK = UCENTER_API_PREFFIX + "vipcard.found.back";		// 找回会员卡

	public final static String UCENTER_BANKCARD_LIST = UCENTER_API_PREFFIX + "bankcard.list";				// 银行卡列表
	public final static String UCENTER_BANKCARD_SAVE = UCENTER_API_PREFFIX + "bankcard.save";				// 保存银行卡
	public final static String UCENTER_VIEW_BANKCARD = UCENTER_API_PREFFIX + "view.bankcard";				// 查看银行卡
	public final static String UCENTER_GET_BANK_NAME = UCENTER_API_PREFFIX + "get.bank.name";				// 获取银行名称
	public final static String UCENTER_DEL_BANKCARD = UCENTER_API_PREFFIX + "get.bankcard";				// 删除银行卡

	public final static String UCENTER_TOGGLE_PEACE = UCENTER_API_PREFFIX + "toggle.peace";										// 免打扰开关
	public final static String UCENTER_PEACETIMESPLOT_SAVE_OR_UPDATE = UCENTER_API_PREFFIX + "peaceTimesplot.save.or.update";	// 保存或更新免打扰
	public final static String UCENTER_LOAD_PEACETIMESPLOT = UCENTER_API_PREFFIX + "load.peaceTimesplot";							// 加载免打扰信息

	public final static String UCENTER_MESSAGE_SEND_BY_MOBILE = UCENTER_API_PREFFIX + "message.send.by.mobile";				// 通过手机号码发送短信
	public final static String UCENTER_MESSAGE_SEND_BY_USER_ID = UCENTER_API_PREFFIX + "message.send.by.userId";				// 通过用户Id发送短信

	public final static String UCENTER_VERIFY_CODE_MSG_CODE_SEND = UCENTER_API_PREFFIX + "verifyCode.msgCode.send";			// 发送验证码
	public final static String UCENTER_CHECK_MSG_VERIFY_CODE = UCENTER_API_PREFFIX + "check.msg.verify.code";					// 校验短信验证码

	public final static String UCENTER_SEND_MAIL = UCENTER_API_PREFFIX + "send.mail";											// 发送邮件
	public final static String UCENTER_SEND_MAILS = UCENTER_API_PREFFIX + "send.mails";										// 群邮
	public final static String UCENTER_SEND_INNER_STAFF = UCENTER_API_PREFFIX + "send.innerStaff";							// 发送内部人员邮件

	public final static String UCENTER_BIND_BAIDUYUN_CHANNEL_ID = UCENTER_API_PREFFIX + "bind.baiduyun.channelId";			// 绑定百度云推送的channelId
	public final static String UCENTER_PUSH_TO_SINGLE_DEVICE = UCENTER_API_PREFFIX + "push.to.single.device";					// 推送单部设备
	public final static String UCENTER_PUSH_TO_TAG_DEVICE = UCENTER_API_PREFFIX + "push.to.tag.device";						// 推送标签绑定的用户

	public final static String UCENTER_DOCTOR_CHAIR_ADD = UCENTER_API_PREFFIX + "doctor.chair.add";							// 申请开通讲座
	public final static String UCENTER_CHAIR_LIST_FOR_DOCTOR = UCENTER_API_PREFFIX + "chair.list.for.doctor";					// 获取医生个人讲座列表
	public final static String UCENTER_CHAIR_SUBMIT_MODIFY_REQUEST = UCENTER_API_PREFFIX + "chair.submit.modify.request";	// 医生提交修改讲座申请
	public final static String UCENTER_CHAIR_LIST = UCENTER_API_PREFFIX + "chair.list";										// 全部已通过状态的讲座列表
	public final static String UCENTER_CHAIR_DETAIL = UCENTER_API_PREFFIX + "chair.detail";									// 讲座详情
	public final static String UCENTER_RELEVANT_CHAIR = UCENTER_API_PREFFIX + "relevant.chair";								// 相关讲座推荐列表
	public final static String UCENTER_GET_LATEST_CHAIR = UCENTER_API_PREFFIX + "get.latest.chair";							// 获取下一个开奖的讲座
	public final static String UCENTER_CHAIR_ADD_COUNT_READ = UCENTER_API_PREFFIX + "chair.add.countRead";					// 增加讲座阅读数
	public final static String UCENTER_CHAIR_TOGGLE_LIKE = UCENTER_API_PREFFIX + "chair.toggle.like";							// 增加点赞或取消点赞
	public final static String UCENTER_CHAIR_ADD_COUNT_SHARE = UCENTER_API_PREFFIX + "chair.add.countShare";					// 增加讲座分享数
	public final static String UCENTER_CHAIR_ADD_EXPECTATION = UCENTER_API_PREFFIX + "chair.add.expectation";					// 增加讲座分享数
	public final static String UCENTER_CHAIR_ADD_SIGNUP = UCENTER_API_PREFFIX + "chair.add.signup";							// 讲座报名
	public final static String UCENTER_DOCTOR_CHAIR_LIST = UCENTER_API_PREFFIX + "doctor.chair.list";							// 医生的所有或前七个讲座列表（只返回id和title）

	public final static String UCENTER_GET_USER_SERVICE_CATEGORY_LIST = UCENTER_API_PREFFIX + "get.user.service.category.list";		// 获取用户关注服务或者医生擅长服务

	public final static String UCENTER_RESERVATION_APPLY = UCENTER_API_PREFFIX + "reservation.apply";											// 医生端：医生申请开通预约挂号
	public final static String UCENTER_RESERVATION_DOCTOR_LIST = UCENTER_API_PREFFIX + "reservation.doctor.list";							// 预约好中医医生列表
	public final static String UCENTER_RESERVATION_PROMOTE_LIST = UCENTER_API_PREFFIX + "reservation.reservationPromote.list";				// 预约好中医套餐列表
	public final static String UCENTER_RECOMMEND_RESERVATION_DOCTOR = UCENTER_API_PREFFIX + "recommend.reservation.doctor";					// 预约好中医推荐医生
	public final static String UCENTER_RESERVATION_GET_SCHEDULE_DATE = UCENTER_API_PREFFIX + "reservation.get.schedule.date";				// 获取排班日期
	public final static String UCENTER_RESERVATION_GET_SCHEDULE_TIMESLOT = UCENTER_API_PREFFIX + "reservation.get.schedule.timeslot";		// 获取排班日期
	public final static String UCENTER_RESERVATION_DOCTOR_DETAIL = UCENTER_API_PREFFIX + "reservation.doctor.detail";						// 预约医生详情信息
	public final static String UCENTER_RESERVATION_PROMOTE_DETAIL = UCENTER_API_PREFFIX + "reservation.reservationPromote.detail";			// 预约套餐详情信息
	public final static String UCENTER_AFTER_RESERVATION = UCENTER_API_PREFFIX + "after.reservation";											// 预约支付成功后回调
	public final static String UCENTER_CANCLE_RESERVATION = UCENTER_API_PREFFIX + "cancle.reservation";										// 预约支付取消后调用
	public final static String UCENTER_GET_DOCTOR_OUT_CALL_TIME_LIST = UCENTER_API_PREFFIX + "get.doctor.out.call.time.list";				// 获取医生大于等于今天的正常状态的出诊信息

	public final static String UCENTER_LIST_CLINIC_BY_DOCTOR_USER_ID = UCENTER_API_PREFFIX + "list.clinic.by.doctorUserId";			// 获取用户所属的医馆列表
	public final static String UCENTER_LIST_CLINIC = UCENTER_API_PREFFIX + "list.clinic";												// 获取医馆列表
	public final static String UCENTER_GET_CLINIC_BY_CLINIC_ID = UCENTER_API_PREFFIX + "get.clinic.by.primary.key";					// 获取医馆信息
	public final static String UCENTER_GET_AND_UPDATE_CLINIC_QUEUE = UCENTER_API_PREFFIX + "get.and.update.clinic.queue";			// 获取并更新医馆排队号

	public final static String UCENTER_GET_PROMOTE_TYPE_DETAIL = UCENTER_API_PREFFIX + "get.reservationPromote.type.detail";			// 获取某个套餐类型的详情

	public final static String UCENTER_LIST_SYSTEM_MESSAGE = UCENTER_API_PREFFIX + "list.system.message";							// 系统通知列表
	public final static String UCENTER_VIEW_SYSTEM_MESSAGE = UCENTER_API_PREFFIX + "view.system.message";							// 查看系统通知

	public final static String UCENTER_SUBMIT_CERTIFICATION = UCENTER_API_PREFFIX + "submit.certification";						// 提交资格认证
	public final static String UCENTER_CERTIFICATION_DETAIL = UCENTER_API_PREFFIX + "certification.detail";						// 资格认证详情
	public final static String UCENTER_SUBMIT_TITLE_AUTHENTICATION = UCENTER_API_PREFFIX + "submit.title.authentication";		// 提交职称认证
	public final static String UCENTER_TITLE_AUTHENTICATION_DETAIL = UCENTER_API_PREFFIX + "title.authentication.detail";		// 职称认证详情
	public final static String UCENTER_CHECK_AUTHENTICATION_STATUS = UCENTER_API_PREFFIX + "check.authentication.status";		// 校验用户认证状态和资料是否完整

	public final static String UCENTER_SEND_PHYSICAL_TEST_INVITE = UCENTER_API_PREFFIX + "send.physical.test.invite";						// 单发测评邀约
	public final static String UCENTER_SEND_LISTEN_CLASS_INVITE = UCENTER_API_PREFFIX + "send.listen.class.invite";							// 单发听课邀约
	public final static String UCENTER_SEND_OUT_CALL_TIME = UCENTER_API_PREFFIX + "send.out.call.time";										// 单发出诊时间
	public final static String UCENTER_SEND_RETURN_VISIT_INVITE = UCENTER_API_PREFFIX + "send.return.visit.invite";							// 单发复诊邀约
	public final static String UCENTER_PATIENT_OR_FANS_LETTER_LIST = UCENTER_API_PREFFIX + "patient.or.fans.letter.list";					// 患者随访或粉丝沟通的列表
	public final static String UCENTER_MASS_PHYSICAL_TEST_INVITE = UCENTER_API_PREFFIX + "mass.physical.test.invite";						// 群发测评邀约
	public final static String UCENTER_MASS_LISTEN_CLASS_INVITE = UCENTER_API_PREFFIX + "mass.listen.class.invite";							// 群发听课邀约
	public final static String UCENTER_MASS_OUT_CALL_TIME = UCENTER_API_PREFFIX + "mass.out.call.time";										// 群发出诊时间
	public final static String UCENTER_MASS_HISTORY_LIST = UCENTER_API_PREFFIX + "mass.history.list";											// 医生的群发记录列表
	public final static String UCENTER_MASS_OUT_CALL_TIME_TO_MY_PATIENTS = UCENTER_API_PREFFIX + "mass.out.call.time.to.myPatients";					// 发送出诊时间给我的患者
	public final static String UCENTER_MASS_STOP_REGISTER_NOTICE_TO_MY_PATIENTS = UCENTER_API_PREFFIX + "mass.stop.register.notice.to.myPatients";		// 发送停诊通知给我的患者

	/*  his使用接口  */
	public final static String UCENTER_GET_HIS_ADMIN = UCENTER_API_PREFFIX + "get.his.admin";								// 获取his用户
	public final static String UCENTER_LIST_HIS_ACCOUNT = UCENTER_API_PREFFIX + "list.his.account";						// his用户列表
	public final static String UCENTER_CREATE_HIS_ACCOUNT = UCENTER_API_PREFFIX + "create.his.account";					// his创建护士或管理员
	public final static String UCENTER_HIS_RESET_USER_PASSWORD = UCENTER_API_PREFFIX + "his.reset.user.password";		// his账号重置密码

	// *****************用户中心接口 end*********************//
	public final static String INDEX = UCENTER_API_PREFFIX + "index";
	public final static String SELECT_MEMBER = UCENTER_API_PREFFIX + "selectMember";
	public final static String SELECT_MEMBER_BLACK_LIST = UCENTER_API_PREFFIX + "selectMemberBlackList";
	public final static String CHANGE_MEMBER_IMPL = UCENTER_API_PREFFIX + "changeMemberImpl";
	public final static String SELECT_FAVORITE = UCENTER_API_PREFFIX + "selectFavorite";
	public final static String SELECT_BEAUTICIAN = UCENTER_API_PREFFIX + "selectBeautician";
	public final static String TOKEN = UCENTER_API_PREFFIX + "token";
	public final static String UPDATE_TOKEN = UCENTER_API_PREFFIX + "updateToken";
	public final static String SELECT_BEAUTYSHOPBED = UCENTER_API_PREFFIX + "selectBeautyshopBed";
	public final static String SELECT_BEAUTYSHOPBED_BY_TIME = UCENTER_API_PREFFIX + "selectBeautyshopBedByTime";
	public final static String CHANGE_BEAUTYSHOPBED = UCENTER_API_PREFFIX + "changeBeautyshopBed";
	public final static String SELECT_BEAUTYSHOP = UCENTER_API_PREFFIX + "selectBeautyshop";
	public final static String SELECT_BEAUTYSHOP_PIC = UCENTER_API_PREFFIX + "selectBeautyshopPic";
	public final static String SELECT_BEAUTYSHOP_EXT = UCENTER_API_PREFFIX + "selectBeautyshopExt";
	public final static String SELECT_INTEGRALADD = UCENTER_API_PREFFIX + "selectIntegralAdd";
	public final static String LOGIN_CODE_IMPL = UCENTER_API_PREFFIX +"loginCodeImpl";//获取login接口调用请求说明
	public final static String MEMBER_UP_SEX_IMPL= UCENTER_API_PREFFIX +"memberUpSexImpl";//更新用户性别
	public final static String MEMBER_UP_BIRTHDAY_IMPL = UCENTER_API_PREFFIX+"memberUpBirthdayImpl";//更新用户生日
	public final static String MEMBER_USER_ACCOUNT_IMPL =  UCENTER_API_PREFFIX + "memberUserAccountImpl";//用户帐户信息设置
	public final static String MEMBER_INTEGRAL_HISTORY_IMPL = UCENTER_API_PREFFIX + "memberIntegralHistoryImpl";//用户帐户信息设置
	public final static String MEMBER_UP_NAME_IMPL = UCENTER_API_PREFFIX + "memberUpNameImpl";//更新用户昵称
	public final static String MEMBER_ADDRESS_IMPL = UCENTER_API_PREFFIX  + "memberAddressImpl";//用户地址
	public final static String MEMBER_ADDRESS_ADD_IMPL = UCENTER_API_PREFFIX  + "memberAddressAddImpl";//添加用户地址
	public final static String MEMBER_ADDRESS_READ_IMPL = UCENTER_API_PREFFIX + "memberAddressReadImpl";//地址编辑预读
	public final static String MEMBER_ADDRESS_EDIT_IMPL  = UCENTER_API_PREFFIX + "memberAddressEditImpl";//修改用户地址
	public final static String MEMBER_ADDRESS_DEL_IMPL = UCENTER_API_PREFFIX + "memberAddressDelImpl";//删除用户地址
	public final static String MEMBER_SET_DEFAULT_IMPL = UCENTER_API_PREFFIX + "memberSetDefaultImpl";//设置默认用户地址
	public final static String MEMBER_INTEGRAL_RECORD_IMPL= UCENTER_API_PREFFIX + "memberIntegralRecordImpl";//积分换优惠券记录
	public final static String MEMBER_FEED_BACK_IMPL = UCENTER_API_PREFFIX + "memberFeedBackImpl";//反馈提交
	public final static String MEMBER_UP_AVATAR_IMPL = UCENTER_API_PREFFIX + "memberUpAvatarImpl";//上传头像

	public final static String COLLECT_STATUS = UCENTER_API_PREFFIX + "collectStatus"; //获取用户的收藏状态
	public final static String FAVORITE_INDEX_LIST_IMPL = UCENTER_API_PREFFIX + "favoriteIndexListImpl";//单个favorite请求参数
	public final static String FAVORITE_SIGN_IMPL = UCENTER_API_PREFFIX + "favoriteSignImpl";// 签到处理
	public final static String FAVORITE_INFO_LIST_IMPL = UCENTER_API_PREFFIX + "favoriteInfoListImpl";// 签到处理

	public final static String LOGIN_CHECK_CODE_IMPL = UCENTER_API_PREFFIX + "loginCheckCode";//获取token
	public final static String CHECK_CODE = UCENTER_API_PREFFIX + "checkCode";//获取token
	public final static String IS_LOGIN = UCENTER_API_PREFFIX + "isLogin";//是否登录
	public final static String SMS = UCENTER_API_PREFFIX + "sms";
	public final static String ADD_BOOK = UCENTER_API_PREFFIX + "addBook";

	public final static String MEMBER_INTEGRAL_IMPL = UCENTER_API_PREFFIX + "memberIntegralImpl";//积分接口
	public final static String MEMBER_ACCOUNT_IMPL = UCENTER_API_PREFFIX + "memberAccountImpl";//余额记录-接口
	public final static String MEMBER_GET_ADDRESS_IMPL = UCENTER_API_PREFFIX + "memberGetAddressImpl";//获取输入的地址
	public final static String MEMBER_ADDRESS_SEARCH_IMPL = UCENTER_API_PREFFIX + "memberAddressSearchImpl";//搜索地址
	public final static String MEMBER_ADDRESS_SAVE_SEARCH_IMPL = UCENTER_API_PREFFIX + "memberAddressSaveSearchImpl";//保存搜索的地址
	public final static String UPDATE_MEMBER_INTEGRAL_BY_MEMBER_ID_IMPL = UCENTER_API_PREFFIX + "updateMemberIntegralByMemberIdImpl";//更新积分
	public final static String MEMBER_MEAL_PRODUCT_IMPL = UCENTER_API_PREFFIX + "memberMealProductImpl";//获取套餐中的商品订单列表
	public final static String MEMBER_MEAL_PRODUCT_DETAIL_IMPL = UCENTER_API_PREFFIX + "memberMealProductDetailImpl";//获取套餐中的商品订单详情信息
	public final static String MEMBER_HELP_URL_IMPL = UCENTER_API_PREFFIX + "memberHelpUrlImpl";	//获取帮助中心
	public final static String MEMBER_CARD_DETAIL_IMPL = UCENTER_API_PREFFIX + "memberCardDetailImpl";	//我的会员卡中心
	public final static String MEMBER_CARD_SHOP_LIST_IMPL = UCENTER_API_PREFFIX + "memberCardShopListImpl";	//我的会员卡门店列表








}
