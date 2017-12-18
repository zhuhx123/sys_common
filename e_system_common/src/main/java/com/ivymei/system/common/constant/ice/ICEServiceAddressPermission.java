package com.ivymei.system.common.constant.ice;

public class ICEServiceAddressPermission {

	private final static String PERMISSION_API_PREFFIX = "permission.ice.api.";// 权限中心接口前缀
	// *****************权限中心接口 start*********************//
	public final static String PERMISSION_LOAD_MENU = PERMISSION_API_PREFFIX + "loadMenu";// 加载菜单
	public final static String PERMISSION_IS_LOGIN = PERMISSION_API_PREFFIX + "isLogin";// 判断是否登录
	public final static String PERMISSION_LOGIN_IN = PERMISSION_API_PREFFIX + "loginIn";// 登录
	public final static String PERMISSION_LOGIN_IN_WITH_MD5_PWD = PERMISSION_API_PREFFIX + "loginInWithMD5Pwd";// 用MD5密码登录
	public final static String PERMISSION_LOGIN_OUT = PERMISSION_API_PREFFIX + "loginOut";// 退出登录
	public final static String PERMISSION_GET_ENCRYPION = PERMISSION_API_PREFFIX + "getEncrypKey";// 获取密钥
	public final static String PERMISSION_GET_LOGIN_SESSION = PERMISSION_API_PREFFIX + "getLoginSession";// 获取登录会话
	public final static String PERMISSION_PROLONG_TOKEN_EXPIRE_TIME = PERMISSION_API_PREFFIX + "proLongTokenExpireTime";// 延长登录token的过期时间
	public final static String PERMISSION_CHECK_PERMISSION = PERMISSION_API_PREFFIX + "checkPermission";// 验证访问URL是否授权
	public final static String PERMISSION_QUERY_PROJECT = PERMISSION_API_PREFFIX + "queryProject";// 查询项目
	public final static String PERMISSION_GET_USER_BYID = PERMISSION_API_PREFFIX + "getUserById";// 查询用户
	public final static String PERMISSION_QUERY_STAFF_DOCTOR = PERMISSION_API_PREFFIX + "queryStaffDoctor";// 查询关联医生ID列表

	public final static String PERMISSION_SAAS_ROLE_QUERY_LIST = PERMISSION_API_PREFFIX + "saas.query.role.list";// 查询角色列表
	public final static String PERMISSION_SAAS_ROLE_UPSERT = PERMISSION_API_PREFFIX + "saas.role.upsert";// 更新或新增角色
	public final static String PERMISSION_SAAS_ROLE_DELETE = PERMISSION_API_PREFFIX + "saas.role.delete";// 删除角色
	public final static String PERMISSION_SAAS_ROLE_DETAIL = PERMISSION_API_PREFFIX + "saas.role.detail";// 角色详情
	public final static String PERMISSION_SAAS_ROLE_QUERY_FUNCTION = PERMISSION_API_PREFFIX + "saas.role.queryFunction";// 查询角色功能

	public final static String PERMISSION_SAAS_USER_QUERY_LIST = PERMISSION_API_PREFFIX + "saas.user.query.list";// 查询用户列表
	public final static String PERMISSION_SAAS_USER_QUERY_FRANCHISEE_MANAGER_LIST = PERMISSION_API_PREFFIX + "saas.user.query.franchiseeManager.list";// 查询加盟商管理员用户列表
	public final static String PERMISSION_SAAS_USER_QUERY_CURATOR_LIST = PERMISSION_API_PREFFIX + "saas.user.query.curator.list";// 查询医馆馆长用户列表
	public final static String PERMISSION_SAAS_USER_DETAIL = PERMISSION_API_PREFFIX + "saas.user.detail";// 查询用户详情
	public final static String PERMISSION_SAAS_USER_MY_DETAIL = PERMISSION_API_PREFFIX + "saas.user.my.detail";// 查询我的详情
	public final static String PERMISSION_SAAS_USER_ADD = PERMISSION_API_PREFFIX + "saas.user.add";// 添加用户
	public final static String PERMISSION_SAAS_USER_BYNAME= PERMISSION_API_PREFFIX + "saas.user.byname";// 根据登录名用户
	public final static String PERMISSION_SAAS_USER_UPSERT_FRANCHISEE_MANAGER = PERMISSION_API_PREFFIX + "saas.user.upsert.franchiseeManager";// 添加加盟商管理员
	public final static String PERMISSION_SAAS_USER_UPSERT_CURATOR = PERMISSION_API_PREFFIX + "saas.user.upsert.curator";// 添加馆长
	public final static String PERMISSION_SAAS_USER_ADD_DOCTOR = PERMISSION_API_PREFFIX + "saas.user.add.doctor";// 添加医生
	public final static String PERMISSION_SAAS_USER_DELETE_DOCTOR = PERMISSION_API_PREFFIX + "saas.user.delete.doctor";// 添加医生
	public final static String PERMISSION_SAAS_USER_UPDATE = PERMISSION_API_PREFFIX + "saas.user.update";// 更新用户
	public final static String PERMISSION_SAAS_USER_DELETE = PERMISSION_API_PREFFIX + "saas.user.delete";// 删除用户
	public final static String PERMISSION_SAAS_USER_UPDATE_MYINFO = PERMISSION_API_PREFFIX + "saas.user.update.myInfo";// 更新我的信息
	public final static String PERMISSION_SAAS_USER_UPDATE_PASSWORD = PERMISSION_API_PREFFIX + "saas.user.updatePassword";// 更新用户密码
	public final static String PERMISSION_SAAS_USER_UPDATE_DOCTOR_AVATAR = PERMISSION_API_PREFFIX + "saas.user.updateDoctorAvatar";// 更新医生头像
	public final static String PERMISSION_SAAS_USER_UPDATE_DOCTOR_PASSWORD_FROM_DT = PERMISSION_API_PREFFIX + "saas.user.updateDoctorPasswordFromDt";// 提供给dt项目的接口修改医生密码
	public final static String PERMISSION_SAAS_USER_CHOOSE_CLINIC = PERMISSION_API_PREFFIX + "saas.user.chooseClinic";// 医生用户选择医院
	public final static String PERMISSION_SAAS_CLINIC_COUNT_USER_NUM = PERMISSION_API_PREFFIX + "saas.clinic.countUserNum";// 统计医馆人数

	public final static String PERMISSION_OB_ROLE_QUERY_LIST = PERMISSION_API_PREFFIX + "ob.query.role.list";// 查询角色列表
	public final static String PERMISSION_OB_USER_ADD_OR_UPDATE = PERMISSION_API_PREFFIX + "ob.user.addOrUpdate";// 添加用户
	public final static String PERMISSION_OB_USER_DELETE = PERMISSION_API_PREFFIX + "ob.user.delete";// 删除用户
	public final static String PERMISSION_OB_USER_UPDATE_PASSWORD = PERMISSION_API_PREFFIX + "ob.user.updatePassword";// 更新用户密码
	// *****************权限中心接口 end*********************//
}
