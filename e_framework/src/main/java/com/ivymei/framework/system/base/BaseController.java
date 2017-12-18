package com.ivymei.framework.system.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;

public class BaseController {

	public final String SUCCESS = "success";

	public final String SHOW_LIST = "/showList";// 打开列表页面

	public final String LIST = "/list";// 获取列表数据

	public final String EDIT = "/edit";// 打开编辑页面

	public final String UPDATE = "/update";// 编辑更新

	public final String ADD = "/add";// 打开添加页面

	public final String SAVE = "/save";// 新增保存

	public final String DETAIL = "/detail";// 查看详情

	public final String DELETE = "/delete";// 删除

	public final String DISABLED_OR_ENABLED = "/disabledOrEnabled";// 启用或禁用

	public final String PAGE = "page";// 页码

	public final String ROWS = "rows";// 分页大小

	// private String succesResponse(Object data) {
	// JSONObject obj = init();
	//
	// obj.toJSONString();
	// }

	protected String succesJSONResult(Object data) {
		JSONObject obj = new JSONObject();
		obj.put("data", data);
		return obj.toJSONString();
	}

	protected String succesJSONResult(Object data, String pattern) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", data);

		if (pattern != null && pattern.trim().length() > 0) { // 使用指定格式字符串输出时间
			return JSON.toJSONStringWithDateFormat(map, pattern);
		} else { // 使用默认格式字符串输出时间
			return JSON.toJSONString(map, SerializerFeature.WriteDateUseDateFormat);
		}
	}

	/**
	 * 返回列表JSON数据（这里的按着easyui的列表格式返回）
	 * 
	 * @param pageInfo
	 * @return
	 */
	protected String easyuiListJSONResult(PageInfo<?> pageInfo) {
		JSONObject obj = new JSONObject();
		obj.put("total", pageInfo == null ? 0 : pageInfo.getTotal());
		obj.put("rows", pageInfo == null ? null : pageInfo.getList());
		return obj.toJSONString();
	}

	/**
	 * 返回列表JSON数据（这里的按着easyui的列表格式返回）
	 * 
	 * @param pageInfo
	 * @param pattern
	 *            定义时间类型怎么转换为字符串，为 空 使用默认 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	protected String easyuiListJSONResult(PageInfo<?> pageInfo, String pattern) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pageInfo == null ? 0 : pageInfo.getTotal());
		map.put("rows", pageInfo == null ? null : pageInfo.getList());

		if (pattern != null && pattern.trim().length() > 0) { // 使用指定格式字符串输出时间
			return JSON.toJSONStringWithDateFormat(map, pattern);
		} else { // 使用默认格式字符串输出时间
			return JSON.toJSONString(map, SerializerFeature.WriteDateUseDateFormat);
		}

	}

	/**
	 * 返回列表JSON数据返回列表JSON数据（这里的按着easyui的列表格式返回）
	 * 
	 * @param total
	 * @param list
	 * @return
	 */
	protected String easyuiListJSONResult(Integer total, List<?> list) {
		JSONObject obj = new JSONObject();
		obj.put("total", total);
		obj.put("rows", list);
		return obj.toJSONString();
	}

	/**
	 * 返回列表JSON数据返回列表JSON数据（这里的按着easyui的列表格式返回）
	 * 
	 * @param total
	 * @param list
	 * @return
	 */
	protected String easyuiListJSONResult(Integer total, List<?> list, String pattern) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", list);

		if (pattern != null && pattern.trim().length() > 0) { // 使用指定格式字符串输出时间
			return JSON.toJSONStringWithDateFormat(map, pattern);
		} else { // 使用默认格式字符串输出时间
			return JSON.toJSONString(map, SerializerFeature.WriteDateUseDateFormat);
		}
	}

}
