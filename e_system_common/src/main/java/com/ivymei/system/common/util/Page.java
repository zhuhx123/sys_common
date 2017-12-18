package com.ivymei.system.common.util;

import java.util.Map;

import com.ivymei.system.common.constant.enums.common.ApiUrlParamEnum;
import org.apache.commons.lang.StringUtils;

import com.ivymei.framework.util.StringUtil;
import com.ivymei.system.common.constant.Constant;

/**
 * 分页信息对像（主要是为了接收ICE后解析分页参数）
 * 
 * @author show
 *
 */
public class Page {

	private Integer pageNo;
	private Integer pageSize;

	public Page(Map<String, String> params) {
		String pageNoStr = params.get(ApiUrlParamEnum.PAGE_NO.getName());
		String pageSizeStr = params.get(ApiUrlParamEnum.PAGE_SIZE.getName());
		if (!StringUtil.isNullOrBlank(pageNoStr) && StringUtils.isNumeric(pageNoStr)) {
			this.pageNo = Integer.parseInt(pageNoStr);
		} else {
			this.pageNo = Constant.DEFAULT_PAGE_NO;
		}
		if (!StringUtil.isNullOrBlank(pageSizeStr) && StringUtils.isNumeric(pageSizeStr)) {
			this.pageSize = Integer.parseInt(pageSizeStr);
		} else {
			this.pageSize = Constant.DEFAULT_PAGE_SIZE;
		}
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
