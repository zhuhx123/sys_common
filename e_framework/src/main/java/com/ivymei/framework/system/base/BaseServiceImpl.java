/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ivymei.framework.system.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ivymei.framework.plugin.cache.DataTableCache;
import com.ivymei.framework.plugin.cache.RedisFactory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通用接口
 */

public abstract class BaseServiceImpl<T, T_example> implements BaseService<T, T_example> {



	public abstract BaseMapper<T, T_example> getBaseMapper();

	public int countByExample(T_example example) {
		return getBaseMapper().countByExample(example);
	}

	public int deleteByExample(T_example example) {
		return getBaseMapper().deleteByExample(example);
	}

	public int deleteByExampleWithCache(T_example example) {
		return getBaseMapper().deleteByExample(example);
	}

	public int deleteByPrimaryKey(Integer id) {
		return getBaseMapper().deleteByPrimaryKey(id);
	}

	public int deleteByPrimaryKeyWithCache(Integer id) {
		return getBaseMapper().deleteByPrimaryKey(id);
	}

	public int insert(T record) {
		return getBaseMapper().insert(record);
	}

	public int insertWithCache(T record) {
		return getBaseMapper().insert(record);
	}

	public int insertSelective(T record) {
		return getBaseMapper().insertSelective(record);
	}

	public int insertSelectiveWithCache(T record) {
		return getBaseMapper().insertSelective(record);
	}

	@Override
	public T selectOneByExample(T_example t_example) {
		PageInfo<T> pageInfo = selectByExample(t_example, 1, 1);
		List<T> list = pageInfo.getList();
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public T selectOneByExampleWithBLOBs(T_example t_example) {
		List<T> list = selectByExampleWithBLOBs(t_example);
		if(list != null && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public List<T> selectByExample(T_example example) {
		return getBaseMapper().selectByExample(example);
	}

	public List<T> selectByExampleWithCache(T_example example) {
		String cacheKey="selectByExampleWithCache";
		DataTableCache dataTableCache=DataTableCache.getTableCache(getBaseMapper());
		List list=dataTableCache.getObject(cacheKey);
		if(list==null || list.size()<1) {
		    list = getBaseMapper().selectByExample(example);
			if (list != null && list.size() > 0) {
				dataTableCache.putByTable(cacheKey, list, RedisFactory.EXPIRE_DEFAULT);
			}
		}
		return list;
	}

	public PageInfo<T> selectByExample(T_example example,
			Integer pageNo,
			Integer pageSize) {
		if (pageNo == null || pageNo < 0) {
			pageNo = 1;
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;
		}
		PageHelper.startPage(pageNo, pageSize);
		List<T> list = getBaseMapper().selectByExample(example);
		PageInfo<T> page = new PageInfo<T>(list);
		return page;
	}


	public PageInfo<T> selectByExampleWithCache(T_example example,
			Integer pageNo,
			Integer pageSize) {
		String cacheKey="selectByExampleWithCache";
		PageInfo<T> page=getCache().getObject(cacheKey);
		if(page	==null || page.getSize()<1) {
			page = selectByExample(example, pageNo, pageSize);
			if (page != null && page.getSize() > 0) {
				DataTableCache dataTableCache = DataTableCache.getTableCache(getBaseMapper());
				dataTableCache.putByTable("selectByExampleWithCache", page, RedisFactory.EXPIRE_DEFAULT);
			}
		}
		return page;
	}

	public T selectByPrimaryKey(Integer id) {
		return getBaseMapper().selectByPrimaryKey(id);
	}

	@Override
	public T selectByPrimaryKey(Short id) {
		return getBaseMapper().selectByPrimaryKey(id);
	}

	public T selectByPrimaryKeyWithCache(Integer id) {
		String cacheKey="selectByPrimaryKeyWithCache";
		T object=getCache().getObject(cacheKey);
		if(object==null ) {
			object=getBaseMapper().selectByPrimaryKey(id);
			if(object!=null){
				getCache().putByTable(cacheKey,object,RedisFactory.EXPIRE_DEFAULT);
			}
		}
		return object;
	}

	public int updateByExample(@Param("record") T record,
			@Param("example") T_example example) {
		return getBaseMapper().updateByExample(record, example);
	}

	/**
	 * 更新完，刷新缓存
	 * @param record
	 * @param example
	 * @return
	 */
	public int updateByExampleWithCache(@Param("record") T record,
			@Param("example") T_example example) {

		int result= updateByExample(record,example);
		if(result>0){
			getCache().refreshCache();
		}
		return result;
	}

	public int updateByExampleSelective(@Param("record") T record,
			@Param("example") T_example example) {
		int result= getBaseMapper().updateByExampleSelective(record, example);

		return result;
	}
	public int updateByExampleSelectiveWithCache(@Param("record") T record,
			@Param("example") T_example example) {
		int result= getBaseMapper().updateByExampleSelective(record, example);

		if(result>0){
			getCache().refreshCache();
		}
		return result;
	}
	private DataTableCache getCache(){
		return DataTableCache.getTableCache(getBaseMapper());
	}


	public int updateByPrimaryKeySelective(T record) {
		return getBaseMapper().updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeySelectiveWithCache(T record) {
		int result= getBaseMapper().updateByPrimaryKeySelective(record);
		if(result>0){
			getCache().refreshCache();
		}
		return result;
	}

	public int updateByPrimaryKey(T record) {
		return getBaseMapper().updateByPrimaryKey(record);
	}

	public int updateByPrimaryKeyWithCache(T record) {

		int result= getBaseMapper().updateByPrimaryKey(record);
		if(result>0){
			getCache().refreshCache();
		}
		return result;
	}

	@Override
	public List<T> selectByExampleWithBLOBs(
			T_example example) {
		return getBaseMapper().selectByExampleWithBLOBs(example);
	}

	@Override
	public PageInfo<T> selectByExampleWithBLOBs(
			T_example example,
			Integer pageNo,
			Integer pageSize) {
		if (pageNo == null || pageNo < 0) {
			pageNo = 1;
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 10;
		}
		PageHelper.startPage(pageNo, pageSize);
		List<T> list = getBaseMapper().selectByExampleWithBLOBs(example);
		PageInfo<T> page = new PageInfo<T>(list);
		return page;
	}

	@Override
	public int updateByExampleWithBLOBs(
			T record,
			T_example example) {
		return getBaseMapper().updateByExampleWithBLOBs(record, example);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(
			T record) {
		return getBaseMapper().updateByPrimaryKeyWithBLOBs(record);
	}
}
