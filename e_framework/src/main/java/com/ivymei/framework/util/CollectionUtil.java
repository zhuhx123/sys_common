package com.ivymei.framework.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtil {
	
	/**
	 * 获取集合中的第一个元素
	 * 如果集合为空，返回 null
	 */
	public static <T> T getFirstResult(Collection<T> collection){
		if(collection == null || collection.isEmpty()){
			return null;
		}
		return collection.iterator().next();
	}
	
	
	public static  boolean isEmpty(Collection<?> collection){
		return collection == null || collection.isEmpty();
	}
	public static boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	
	
	
	
	
	/**
	 * 判断 arr 数组中是否包含某个 值
	 * 对于一些不适合的类型可能需要进行方法重载
	 * */
	public static boolean contains(Object[] arr, Object target){
		if(ValidateUtil.isEmpty(arr) || target == null){
			return false;
		}
		for (Object src : arr) {
			if(src.equals(target)){
				return true;
			}
		}
		return false;
	}
	public static boolean contains(Integer[] arr, Integer target){
		if(ValidateUtil.isEmpty(arr) || target == null){
			return false;
		}
		for (Integer src : arr) {
			if(src.intValue() == target.intValue()){
				return true;
			}
		}
		return false;
	}
	public static boolean contains(String[] arr, String target){
		if(ValidateUtil.isEmpty(arr) || target == null){
			return false;
		}
		for (String src : arr) {
			if(src.equals(target)){
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 集合合并
	 * @param tar
	 * 	将 srcs 所有集合合并到 tar 中
	 * @param srcs
	 * 	可以传入多个参数
	 */
	public static <T> void mergeCollections(Collection<T> tar, Collection<T> ... srcs ){
		if(tar != null && srcs != null && srcs.length > 0){
			for (Collection<T> src : srcs) {
				if(src != null && src.size() > 0){
					tar.addAll(src);
				}
			}
		}
	}
	
	
	
	
	public static void main(String[] args) {
		test_mergeCollections();
	}
	
	
	public static void test_mergeCollections(){
		
		List<String> list = new ArrayList<>();
		list.add("aa");
		list.add("bb");
		List<String> list2 = new ArrayList<>();
		list2.add("cc");
		list2.add("dd");
		List<String> list3 = new ArrayList<>();
		list3.add("ee");
		list3.add("ff");
		list3.add("aa");
		
		// [aa, bb]
		System.out.println(list);
		mergeCollections(list, list2);
		// [aa, bb, cc, dd]
		System.out.println(list);
		// [aa, bb, cc, dd, ee, ff,aa]
		mergeCollections(list, list3);
		System.out.println(list);
		
	}
	
	public static void test_contains(){
		System.out.println(contains((Object[])null, (Object)null)); // false
		Integer[] ids = {-1,0,1};
		System.out.println(contains(ids, -1)); // true
		System.out.println(contains(ids, 0)); // true
		System.out.println(contains(ids, 1)); // true
		System.out.println(contains(ids, 2)); // false
		
	}
	
	
	public static void test_isEmpty(){
		
		List<String> list = null;
		System.out.println(isEmpty(list)); // true
		System.out.println(isNotEmpty(list)); // false
		
		list = new ArrayList<String>();
		System.out.println(isEmpty(list)); // true
		System.out.println(isNotEmpty(list)); // false
		
		
		list.add("abc");
		System.out.println(isEmpty(list)); // false
		System.out.println(isNotEmpty(list)); // true
	}
	
	public static void test_getFirstResult(){
		
		List<String> list = new ArrayList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		
		System.out.println(list);
		String str = getFirstResult(list);
		System.out.println(str);
		
		
		Set<Object> set = new HashSet<Object>();
		set.add(new Object());
		set.add(new Object());
		set.add(new Object());
		
		System.out.println(set);
		Object obj = getFirstResult(set);
		System.out.println(obj);
	}
	
}
