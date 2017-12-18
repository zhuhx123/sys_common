package com.ivymei.system.common.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class QueryResultParser {

	public static final <T> QueryResult<T> parseObject(String result, Class<T> clazz) {
		// return parseObject(text, clazz, new Feature[0]);

		JSONObject jsonResult = JSONObject.parseObject(result);
		Integer totalSize = jsonResult.getInteger("totalSize");
		JSONArray jsonDatas = jsonResult.getJSONArray("data");
		if(jsonDatas==null){
			jsonDatas = new JSONArray();
		}
		// 封装QueryResult的data
		List<T> data = new ArrayList<T>();
		if (jsonDatas.size() > 0) {
			for (int i = 0; i < jsonDatas.size(); i++) {
				data.add(jsonDatas.getObject(i, clazz));
			}
		}

		return new QueryResult<T>(data, totalSize);
	}

}
