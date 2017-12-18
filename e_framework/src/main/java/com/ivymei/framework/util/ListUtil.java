package com.ivymei.framework.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListUtil {

	/**
	 * 对列表作分页处理
	 * 
	 * @param list
	 * @param pageSize
	 * @return
	 */
	public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
		int listSize = list.size();
		int page = (listSize + (pageSize - 1)) / pageSize;

		List<List<T>> listArray = new ArrayList<List<T>>();
		for (int i = 0; i < page; i++) {
			List<T> subList = new ArrayList<T>();
			for (int j = 0; j < listSize; j++) {
				int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;
				if (pageIndex == (i + 1)) {
					subList.add(list.get(j));
				}
				if ((j + 1) == ((j + 1) * pageSize)) {
					break;
				}
			}
			listArray.add(subList);
		}
		return listArray;
	}

	public static String combine(List<?> list) {
		return combine(list, ',');
	}

	public static String combine(List<?> list, char split) {
		StringBuffer buf = new StringBuffer();
		if (list != null && list.size() > 0) {

			Iterator<?> it = list.iterator();
			while (it.hasNext()) {
				Object obj = it.next();
				buf.append(obj.toString());
				buf.append(split);
			}

		}
		String result = "";
		if (buf.length() > 0) {
			result = buf.substring(0, buf.length() - 1);
		}
		return result;
	}
}
