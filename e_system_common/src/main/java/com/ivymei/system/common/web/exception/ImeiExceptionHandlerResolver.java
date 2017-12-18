package com.ivymei.system.common.web.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.ivymei.system.common.constant.Constant;
import com.ivymei.system.common.constant.enums.common.MsgCode;

public class ImeiExceptionHandlerResolver implements HandlerExceptionResolver {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
		Exception ex) {

		ModelAndView view = new ModelAndView();

		MappingJackson2JsonView jsonveiw = new MappingJackson2JsonView();
		Map<String, Object> attributes = new HashMap<String, Object>();
		if (ex instanceof ImeiException) {
			log.error("【业务异常】：", ex);
			ImeiException jex = (ImeiException) ex;
			// 没登录，则跳转至“提示登录”的页面
			if (jex.getMsgCode().getMsgCode() == MsgCode.REDIRECT_LOGIN_PAGE.getMsgCode()) {
				view.setViewName("commons/redirect");
				view.addObject("tips", jex.getMsg());
			} else {// 自定义的接口报的错误提示
				attributes.put(Constant.ERROR_CODE_KEY, jex.getMsgCode().getMsgCode());
				attributes.put(Constant.ERROR_MSG_KEY, jex.getMsg());
				jsonveiw.setAttributesMap(attributes);
				view.setView(jsonveiw);
			}
		} else {// 系统异常（程序上的错误），统一提示
			attributes.put(Constant.ERROR_CODE_KEY, MsgCode.SYSTEM_ERROR.getMsgCode());
			attributes.put(Constant.ERROR_MSG_KEY, MsgCode.SYSTEM_ERROR.getMessage());
			jsonveiw.setAttributesMap(attributes);
			log.error("【系统异常】：", ex);

			view.setView(jsonveiw);
		}

		return view;
	}
}
