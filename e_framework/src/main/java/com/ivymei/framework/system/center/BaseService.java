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

import com.github.pagehelper.PageInfo;
import com.ivymei.framework.util.db.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通用接口
 */
@DataSource(name = DataSource.centerDataSource)
public interface BaseService<T, T_example> {

    abstract BaseMapper<T, T_example> getBaseMapper();

    int countByExample(T_example example);

    int deleteByExample(T_example example);

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectOneByExample(T_example example);

    T selectOneByExampleWithBLOBs(T_example example);

    List<T> selectByExample(T_example example);

    List<T> selectByExampleWithBLOBs(T_example example);

    PageInfo<T> selectByExample(T_example example, Integer pageNo, Integer pageSize);

    PageInfo<T> selectByExampleWithBLOBs(T_example example, Integer pageNo, Integer pageSize);

    T selectByPrimaryKey(Integer id);

    T selectByPrimaryKey(Short id);

    int updateByExample(@Param("record") T record,
                        @Param("example") T_example example);

    int updateByExampleWithBLOBs(@Param("record") T record, @Param("example") T_example example);


    int updateByExampleSelective(@Param("record") T record, @Param("example") T_example example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeyWithBLOBs(T record);

}
