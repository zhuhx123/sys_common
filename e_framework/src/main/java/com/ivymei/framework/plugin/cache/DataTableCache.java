package com.ivymei.framework.plugin.cache;

import com.ivymei.framework.system.base.BaseMapper;
import com.ivymei.framework.util.Md5Encrypt;
import com.ivymei.framework.util.StringUtil;

public class DataTableCache {
	public static final int EXPIRE_DEFAULT = 60 * 60 * 5;// 5小时。秒为单位 。
	private static String TABLE_KEY_PREFIX="";
	private static Cache cache;
	private static DataTableCache dataTableCache;



	public static DataTableCache getTableCache(BaseMapper baseMapper){
		if(baseMapper==null){
			throw new RuntimeException("抱歉，请在service类中，继承BaseService使用该方法。");
		}
		TABLE_KEY_PREFIX= Md5Encrypt.md5ByUTF8(baseMapper.getClass().getName());

		cache= RedisFactory.getDataCache();
		synchronized (DataTableCache.class){
			dataTableCache=new DataTableCache();
		}
		return dataTableCache;
	}

	/**
	 * 以数据库表为单位，缓存数据。
	 * @param cacheKey
	 * @param data
	 * @param expire
	 */
	public void putByTable(String cacheKey, Object data, int expire) {
		if (cache != null) {

			String collectionKey=getCollectionKey();//集合对象名称。
			String fieldName=cacheKey;
			cache.hsetForSerialize(collectionKey, fieldName, data);

		}
	}
	public void deleteByTable(){
		if(!StringUtil.isNotBlank(TABLE_KEY_PREFIX)) {
			String collectionKey = getCollectionKey();
			cache.removeForSerialize(collectionKey);
		}

	}

	private static String getCollectionKey(){
		return "table:"+TABLE_KEY_PREFIX;
	}

	public void refreshCache(){
		String key=getCollectionKey();
		cache.removeForSerialize(key);

	}

	public <T> T getObject(String cacheKey) {
		if (cache != null) {

			String collectionKey=getCollectionKey();
			String fieldName=cacheKey;
			T data= (T)cache.hgetForSerialize(collectionKey, fieldName);
			return data;
		}
		return null;
	}
}
