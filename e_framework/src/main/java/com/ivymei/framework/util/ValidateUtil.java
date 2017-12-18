package com.ivymei.framework.util;

import java.util.Collection;



/**
 * 虽然一些方法可能与其他工具类的方法重复，但这工具类还是非常好用的
 * */
public class ValidateUtil {
	
	/**
	 * 判断传入对象是否为空
	 * */
	/*
	 * 校验字符串、数字、对象
	 */
	public static boolean isEmpty(String str){
		return str == null || str.length() == 0;
	}
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	public static boolean isEmpty(Integer integer){
		return integer == null || integer.intValue() == 0;
	}
	public static boolean isNotEmpty(Integer integer){
		return !isEmpty(integer);
	}
	
	public static boolean isEmpty(Object obj){
		return obj == null;
	}
	public static boolean isNotEmpty(Object obj){
		return !isEmpty(obj);
	}
	
	
	/*
	 * 校验数组集合
	 */
	public static boolean isEmpty(int[] array){
		return array == null || array.length == 0;
	}
	public static boolean isNotEmpty(int[] array){
		return !isEmpty(array);
	}
	public static boolean isEmpty(Object[] array){
		return array == null || array.length == 0;
	}
	public static boolean isNotEmpty(Object[] array){
		return !isEmpty(array);
	}
	public static  boolean isEmpty(Collection<?> collection){
		return collection == null || collection.isEmpty();
	}
	public static boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	
	
	/**
	 * 判断 src 是否与 tar 相等，两个对象都必须存在才 true
	 * 写这方法主要用于数字相等的比较
	 * */
	public static boolean isEquals(Object src, Object tar){
		if(isEmpty(src) || isEmpty(tar)){
			return false;
		}
		return src.equals(tar);
	}
	public static boolean isNotEquals(Object src, Object tar){
		return !isEquals(src,tar);
	}
	
	public static boolean isEquals(Integer src, Integer tar){
		// 如果不转为 Object，输入 0 的时候该方法会判断为 空
		if(isEmpty((Object)src) || isEmpty((Object)tar)){ 
			return false;
		}
		return src.intValue() == tar.intValue();
	}
	public static boolean isNotEquals(Integer src, Integer tar){
		return !isEquals(src,tar);
	}
	public static boolean isEquals(Long src, Long tar){
		// 如果不转为 Object，输入 0 的时候该方法会判断为 空
		if(isEmpty((Object)src) || isEmpty((Object)tar)){ 
			return false;
		}
		return src.intValue() == tar.intValue();
	}
	public static boolean isNotEquals(Long src, Long tar){
		return !isEquals(src,tar);
	}
	public static boolean isEquals(Integer src, Long tar){
		// 如果不转为 Object，输入 0 的时候该方法会判断为 空
		if(isEmpty((Object)src) || isEmpty((Object)tar)){ 
			return false;
		}
		return src.intValue() == tar.intValue();
	}
	public static boolean isNotEquals(Integer src, Long tar){
		return !isEquals(src,tar);
	}
	public static boolean isEquals(Long src, Integer tar){
		// 如果不转为 Object，输入 0 的时候该方法会判断为 空
		if(isEmpty((Object)src) || isEmpty((Object)tar)){ 
			return false;
		}
		return src.intValue() == tar.intValue();
	}
	public static boolean isNotEquals(Long src, Integer tar){
		return !isEquals(src,tar);
	}
	
	
	
	
	public void test_isEquals(){
		System.out.println(isEquals(0, 0)); // true
		System.out.println(isEquals(1, 2)); // false
		System.out.println(isEquals(1, 1)); // true
		System.out.println(isEquals(1, (Object)null)); // false
		
	}
	
	public static void test_isEmpty(){
		Object obj = null;
		
		System.out.println(isEmpty(obj));
		obj = new Object();
		System.out.println(isEmpty(obj));
		
		
		System.out.println(isEmpty(new Integer[]{}));
		System.out.println(isEmpty(new Integer[]{1,2,3}));
		System.out.println(isNotEmpty(new Integer[]{1,2,3}));
	}
	
}
