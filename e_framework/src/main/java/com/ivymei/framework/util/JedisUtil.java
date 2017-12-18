package com.ivymei.framework.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 对Redis数据库进行操作的工具类
 * 
 */
public class JedisUtil {
	private static final Logger LOG = LoggerFactory.getLogger(JedisUtil.class);

	public static final int JEDISPOOL_MAXTOTAL = 100;
	public static final int JEDISPOOL_MAXAIDLE = 20;
	public static final int JEDISPOOL_TIMEOUT = 5000;

	public static final int REDIS_DATABASE_NO = 1;

	private JedisUtil() {
	}

	/**
	 * 单例，延迟加载
	 * 
	 * @author Administrator
	 * 
	 */
	private static class SingletonHolder {
		private static JedisPool pool;
		static {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(JEDISPOOL_MAXTOTAL);
			config.setMaxIdle(JEDISPOOL_MAXAIDLE);
			if(StringUtil.isNotBlank(Config.REDIS_PASSWORD)){
				pool = new JedisPool(config, Config.REDIS_HOST, Config.REDIS_PORT, JEDISPOOL_TIMEOUT, Config.REDIS_PASSWORD, REDIS_DATABASE_NO);
			}else{
				pool = new JedisPool(config, Config.REDIS_HOST, Config.REDIS_PORT, JEDISPOOL_TIMEOUT);
			}
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					pool.destroy();
				}
			});
		}
	}

	/**
	 * 获取连接池
	 * 
	 * @return
	 */
	private static JedisPool getPool() {
		return SingletonHolder.pool;
	}

	/**
	 * 从连接池中取出一个连接
	 * 
	 * @return
	 */
	public static Jedis getJedis() {
		return getPool().getResource();
	}

	/**
	 * 关闭连接
	 * 
	 * @param jedis
	 */
	private static void closeJedis(Jedis jedis) {
		if (null != jedis) {
			jedis.close();
		}
	}

	/**
	 * 保存字符串值
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean saveString(String key, String value) {
		return saveString(key, value, 0);
	}

	/**
	 * 保存字符串值并设置过期时间
	 * 
	 * @param key
	 * @param value
	 * @param seconds
	 *            过期时间（秒）
	 * @return
	 */
	public static boolean saveString(String key, String value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.set(key, value);
			if (seconds > 0) {
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			LOG.error("redis error , the key is " + key + " and the value is " + value, e);
			return false;
		} finally {
			closeJedis(jedis);
		}
		return true;
	}

	/**
	 * 根据key取出值
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		Jedis jedis = null;
		String value = null;
		try {
			jedis = getJedis();
			value = jedis.get(key);
		} catch (Exception e) {
			LOG.error("redis error,the key is " + key, e);
		} finally {
			closeJedis(jedis);
		}
		return value;
	}

	/**
	 * 删除指定的键值
	 * 
	 * @param key
	 */
	public static void removeByKey(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.del(key);
		} catch (Exception e) {
			LOG.error("redis error ", e);
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 删除多个键值
	 * 
	 * @param keys
	 */
	public static void removeByKeys(String... keys) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.del(keys);
		} catch (Exception e) {
			LOG.error("redis error ", e);
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 保存值到hashMap
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public static boolean saveField(String key, String field, String value) {
		return saveField(key, field, value, 0);
	}

	/**
	 * 保存值到hashMap，并设置整个map的有效时间
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @param seconds
	 * @return
	 */
	public static boolean saveField(String key, String field, String value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			jedis.hset(key, field, value);
			if (seconds > 0) {
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			LOG.error("redis error ", e);
			return false;
		} finally {
			closeJedis(jedis);
		}
		return true;
	}

	/**
	 * 取出hashMap中对应字段的值
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public static String getFieldValue(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hget(key, field);
		} catch (Exception e) {
			LOG.error("redis error ", e);
		} finally {
			closeJedis(jedis);
		}
		return null;
	}

	/**
	 * 取出hashMap
	 * 
	 * @param key
	 * @return
	 */
	public static Map<String, String> getMap(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			LOG.error("redis error ", e);
		} finally {
			closeJedis(jedis);
		}
		return null;
	}

	/**
	 * 将对象序列化(json格式化)存入redis
	 * 
	 * @param key
	 * @param object
	 * @return
	 */
	public static boolean saveObj(String key, Object object) {
		return saveObj(key, object, 0);
	}

	/**
	 * 将对象序列化(json格式化)存入redis，并设置过期时间
	 * 
	 * @param key
	 * @param object
	 * @param seconds
	 *            过期时间 （秒）
	 * @return
	 */
	public static boolean saveObj(String key, Object object, int seconds) {
		String value = JSONObject.toJSONString(object);
		return saveString(key, value, seconds);
	}

	/**
	 * 将对象序列化后存入map
	 * 
	 * @param key
	 * @param field
	 * @param object
	 * @return
	 */
	public static boolean saveMapObj(String key, String field, Object object) {
		String value = JSONObject.toJSONString(object);
		return saveField(key, field, value);
	}

	/**
	 * 从redis的map中取出对象
	 * 
	 * @param key
	 * @param field
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public static <T> T getObj(String key, String field, Class<T> clazz) throws IOException {
		String value = getFieldValue(key, field);
		if (value == null) {
			return null;
		}
		return JSONObject.parseObject(value, clazz);
//		return JsonUtil.jsonToObject(value, clazz);
	}

	/**
	 * 从redis中取出对象
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	public static <T> T getObj(String key, Class<T> clazz) throws IOException {
		String value = getString(key);
		if (value == null) {
			return null;
		}
		return JSONObject.parseObject(value, clazz);
//		return JsonUtil.jsonToObject(value, clazz);
	}

	/**
	 * 将一个列表保存至redis
	 * 
	 * @param key
	 * @param list
	 * @return
	 */
	public static <T> boolean saveList(String key, List<T> list) {
		return saveList(key, list, 0);
	}

	/**
	 * 将一个列表保存至redis
	 * 
	 * @param key
	 * @param list
	 * @param seconds
	 * @return
	 */
	public static <T> boolean saveList(String key, List<T> list, int seconds) {
		if (list == null) {
			return false;
		}
		int size = list.size();
		// 如果列表为空，lpush会报错
		if (size == 0) {
			return false;
		}
		String[] array = new String[size];
		Jedis jedis = null;
		try {
			// redis存储list是倒序的
			for (int i = size - 1, j = 0; i >= 0; i--, j++) {
//				array[j] = JsonUtil.toJsonString(list.get(i));
				array[j] = JSONObject.toJSONString(list.get(i));
			}
			jedis = getJedis();
			jedis.del(key);
			jedis.lpush(key, array);
			// 设置过期时间
			if (seconds > 0)
				jedis.expire(key, seconds);
			return true;
		} catch (Exception e) {
			LOG.info("Exception:", e);
			return false;
		} finally {
			closeJedis(jedis);
		}
	}

	/**
	 * 从redis中取出列表
	 * 
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> getList(String key, Class<T> clazz) {
		return getListByRange(key, clazz, 0, 0);
	}

	/**
	 * 从redis中取出列表，分页
	 * 
	 * @param key
	 * @param clazz
	 * @param page
	 *            页码，从1开始
	 * @param size
	 * @return
	 */
	public static <T> List<T> getListByRange(String key, Class<T> clazz, int page, int size) {
		Jedis jedis = null;
		List<T> result = new ArrayList<T>();
		try {
			jedis = getJedis();
			int from = 0;
			int to = -1;
			if (page != 0 && size != 0) {
				from = (page - 1) * size;
				to = from + size - 1;
			}
			List<String> list = jedis.lrange(key, from, to);
			for (String string : list) {
//				T t = JsonUtil.jsonToObject(string, clazz);
				T t = JSONObject.parseObject(string, clazz);
				result.add(t);
			}
		} catch (Exception e) {
			LOG.error("redis error ", e);
		} finally {
			closeJedis(jedis);
		}
		return result;
	}

	/**
	 * 查询列表长度
	 * 
	 * @param key
	 * @return
	 */
	public static int getListSize(String key) {
		Jedis jedis = null;
		int size = 0;
		try {
			jedis = getJedis();
			size = jedis.llen(key).intValue();
		} catch (Exception e) {
			LOG.error("redis error ", e);
		} finally {
			closeJedis(jedis);
		}
		return size;
	}
}
