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

package com.ivymei.framework.system.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ivymei.framework.util.db.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通用接口
 */
@DataSource(name = DataSource.centerDataSource)
public abstract class BaseServiceImpl<T, T_example> implements BaseService<T, T_example> {

    @Override
    public abstract BaseMapper<T, T_example> getBaseMapper();

    @Override
    public int countByExample(T_example example) {
        return getBaseMapper().countByExample(example);
    }

    @Override
    public int deleteByExample(T_example example) {
        return getBaseMapper().deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return getBaseMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        return getBaseMapper().insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return getBaseMapper().insertSelective(record);
    }

    @Override
    public T selectOneByExample(T_example t_example) {
        PageInfo<T> pageInfo = selectByExample(t_example, 1, 1);
        List<T> list = pageInfo.getList();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public T selectOneByExampleWithBLOBs(T_example t_example) {
        List<T> list = selectByExampleWithBLOBs(t_example);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<T> selectByExample(T_example example) {
        return getBaseMapper().selectByExample(example);
    }

    @Override
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

    @Override
    public T selectByPrimaryKey(Integer id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

    @Override
    public T selectByPrimaryKey(Short id) {
        return getBaseMapper().selectByPrimaryKey(id);
    }

    @Override
    public int updateByExample(@Param("record") T record,
                               @Param("example") T_example example) {
        return getBaseMapper().updateByExample(record, example);
    }

    @Override
    public int updateByExampleSelective(@Param("record") T record,
                                        @Param("example") T_example example) {
        int result = getBaseMapper().updateByExampleSelective(record, example);

        return result;
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return getBaseMapper().updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return getBaseMapper().updateByPrimaryKey(record);
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
