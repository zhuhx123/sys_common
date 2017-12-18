package com.ivymei.framework.plugin.cache;

import java.util.Map;


public interface Cache {
	public Object get(String key);


	/**
	 * 将Object序列化放入缓存
	 *
	 * @param key
	 *            key
	 * @param value
	 *            对象值
	 * @param expireSeconds
	 *            过期时间，为0的时候 不设置过期时间
	 */
	public void put(String key, Object value, int expireSeconds);

	public void put(String key, Object value);

	public void remove(String key);

	public void removeHash(String key);

	public void clear();


	public void destroy();

	public String getStringValue(String key);


	public String getUnSerialStringValue(String key);

	public String showMsg();
	/**
	 * 原子性操作-递减操作。
	 * @param key
	 * @return
	 */
	public boolean decrease(String key);
	/**
	 * 原子性-递增数字操作。
	 * @param key
	 * @return
	 */
	public boolean increase(String key);

	public void removeForSerialize(String key);
	public Object getBySerialize(String key);

	public boolean contains(String key);



	/* hash存储 */
	public void putHash(String key, String field, Object value);
	/* 移除hash Key 中的某一个field*/
	public void removeHashField(String key, String field);
	/* 移除hash Key 中的某一个field*/
	public void removeHashFieldForSerialize(String key, String field);
	/* 获取hash存储 */
	public Object getFromHash(String key, String field);
	/* 获取hash存储 */
	public Map<String, Object> getHashAll(String key);


	public void setExpiretime(String key, int expireSeconds);

	public Object getWithoutPrefix(String key);
	public Object hgetForSerialize(String key, String field);
	/**
	 * 序列化保存对象，与hgetForSerialize方法搭配使用。
	 * 向指定名称的hash中添加字段名为field,值为value的元素,永不过期。
	 * @param key 哈希名  缓存对象集合名称
	 * @param field 字段名
	 * @param value 字段值
	 */
	public void hsetForSerialize(String key, String field, Object value);
}
