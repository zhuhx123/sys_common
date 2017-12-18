package com.ivymei.system.common.plugin.ice.server;

import com.ivymei.system.common.web.exception.ImeiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Ice.Current;

import com.ivymei.framework.util.SpringUtil;
import com.ivymei.framework.util.StringUtil;
import com.ivymei.system.common.constant.enums.common.MsgCode;
import com.ivymei.system.common.plugin.IceServiceFactory;
import com.ivymei.system.common.plugin.ice.permission.ICENeedLoginedURL;
import com.ivymei.system.common.plugin.ice.permission.UserNeedLoginInterface;
import common.business.commandice._CommandServiceIceDisp;
import common.business.commandice.model.Command;
import common.business.commandice.model.ResponseMessage;

/**
 * 提供ICE接口类
 * 
 * @author show
 * 
 */
public class IceCommandServiceImpl extends _CommandServiceIceDisp {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(IceCommandServiceImpl.class);

	public IceCommandServiceImpl() {
	}

	@Override
	public ResponseMessage doRequest(Command command, Current arg1) {

		try {
			String actionPath = command.actionPath;
			if (StringUtil.isNullOrBlank(actionPath)) {
				return IceResponseHelper.buildFaileResult("抱歉，类方法路径不能为空！");
			}
			// 得到类名。最后一个句号前。
			int index = actionPath.lastIndexOf(".");// .searchQuestion
			if (index < 1) {
				return IceResponseHelper.buildFaileResult("抱歉，缺少类定义。");
			}
			// 类
			String componentId = actionPath.substring(0, index);
			IceServiceFactory c = (IceServiceFactory) SpringUtil.getBean(componentId, IceServiceFactory.class);
			// 方法
			String method = actionPath.substring(index + 1);
			if (StringUtil.isNullOrBlank(method)) {
				return IceResponseHelper.buildFaileResult("抱歉，没有方法可执行。");
			}

			// 加上判断登录的逻辑。
			if (ICENeedLoginedURL.urlList.contains(actionPath)) {

				UserNeedLoginInterface userNeedLoginInterface = SpringUtil.getBean("userNeedLoginInterfaceImpl",
						UserNeedLoginInterface.class);
				if (userNeedLoginInterface == null) {
					return IceResponseHelper
							.buildExceptionResult("抱歉，调用失败，请先实现UserNeedLoginInterface接口的实现类userNeedLoginInterfaceImpl。");
				}
				ResponseMessage result = userNeedLoginInterface.checkLogined(command.params);
				if (result.getMsgCode() != MsgCode.SUCCESSFUL.getMsgCode()) {
					// String msg=result.getMessage();
					// return
					// IceResponseHelper.buildFaileResult("抱歉，此接口需登录后才能使用。详细信息："+msg);
					return result;
				}
			}

			// Class<? extends Object> clazz1 = c.getClass();
			return c.execute(method, command.params);

		} catch (ImeiException e) {
			return IceResponseHelper.buildExceptionResult(e.getMessage());
		} catch (Exception e) {
			logger.error("执行ICE出现异常！", e);
			return IceResponseHelper.buildExceptionResult("抱歉，执行失败，请检查类方法是否正确！原因：" + e.getCause().getMessage());
		}
	}

	/**
	 * 服务是否运行
	 * 
	 * @throws Exception
	 */
	public int isRun(Current arg0) {
		return 1;
	}
}
