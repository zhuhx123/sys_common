package com.ivymei.framework.test;

import org.junit.Test;

import com.ivymei.framework.util.StringUtil;

public class StringUtilTest {

	@Test
	public void test(){
		boolean isRight=StringUtil.validateUserName("刘 这 中");
		System.out.println("right:"+isRight);
	}
}
