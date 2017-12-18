package com.ivymei.framework.plugin.cache;

public class DataCache {
	public static final int EXPIRE_DEFAULT = 60 * 60 * 5;// 5小时。秒为单位 。
	private static String TABLE_KEY_PREFIX="";
	
	public static Cache getCache(){
		return RedisFactory.getDataCache();
	}


}
