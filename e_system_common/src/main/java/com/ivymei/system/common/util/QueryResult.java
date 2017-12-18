package com.ivymei.system.common.util;

import java.io.Serializable;
import java.util.List;

public class QueryResult<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1698505761876076392L;
	private List<T> data;
	private int totalSize;
	private int pageSize;
	private int pageNo;
	
	
	public QueryResult(List<T> data, int totalSize) {
		super();
		this.data = data;
		this.totalSize = totalSize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	

}
