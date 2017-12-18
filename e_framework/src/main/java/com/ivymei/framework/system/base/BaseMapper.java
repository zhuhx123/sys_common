package com.ivymei.framework.system.base;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper<T1, T_example> {

	int countByExample(T_example example);

	int deleteByExample(T_example example);

	int deleteByPrimaryKey(Integer id);

	int insert(T1 record);

	int insertSelective(T1 record);

	List<T1> selectByExample(T_example example);
	
	List<T1> selectByExampleWithBLOBs(T_example example);

	T1 selectByPrimaryKey(Integer id);

	T1 selectByPrimaryKey(Short id);

	T1 selectByPrimaryKeyWithBLOBs(Integer id);

	int updateByExampleSelective(@Param("record") T1 record, @Param("example") T_example example);
	
	int updateByExampleWithBLOBs(@Param("record") T1 record, @Param("example") T_example example);

	int updateByExample(@Param("record") T1 record, @Param("example") T_example example);

	 int updateByPrimaryKeyWithBLOBs(T1 record);
	
	int updateByPrimaryKeySelective(T1 record);

	int updateByPrimaryKey(T1 record);
}
