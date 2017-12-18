package com.ivymei.framework.plugin.cache;

import com.ivymei.framework.util.DateUtil;
import com.ivymei.framework.util.SerializeUtil;

import com.ivymei.framework.util.StringUtil;
import org.apache.log4j.Logger;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.HashMap;
import java.util.Map;

public class RedisDataCache implements Cache{
		private ShardedJedisPool jedisPool;




		protected final static Logger log = Logger.getLogger(RedisDataCache.class);
		public static final String EXPIRE_KEY_PREFIX=":exrpise";

		/**
		 * 缓存key的前缀，每个数据库定一个前缀名称。
		 */
		private String cacheKeyPrefix;

		public RedisDataCache(ShardedJedisPool shardedJedisPool,String cacheKeyPrefix){
			this.jedisPool=shardedJedisPool;
			if(!StringUtil.isNullOrEmpty(cacheKeyPrefix)){
				this.cacheKeyPrefix=cacheKeyPrefix;
			}else{
				this.cacheKeyPrefix="";
			}
		}

		@Override
		public Object get(String key) {
			ShardedJedis jedis = null;
			try {
				key = getCacheKey(key);
				jedis = jedisPool.getResource();
				byte[] result = jedis.get(key.getBytes());
				if (result == null) {
					return null;
				}
				byte[] bytes = jedis.get(key.getBytes());
				return SerializeUtil.unserialize(bytes);
			} catch (Exception e) {
				log.error("get", e);
			} finally {
				returnResource(jedis);
			}
			return null;
		}


		/**
		 * 取缓存,key不带前缀
		 */
		@Override
		public Object getWithoutPrefix(String key) {
			ShardedJedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				byte[] result = jedis.get(key.getBytes());
				if (result == null) {
					return null;
				}
				// byte[] bytes = jedis.get(key.getBytes());
				return SerializeUtil.unserialize(result);
			} catch (Exception e) {
				log.error("getWithoutPrefix", e);
			} finally {
				returnResource(jedis);
			}
			return null;
		}

		/**
		 *
		 * @param key
		 * @return
		 */
		public String getUnSerialStringValue(String key){
			ShardedJedis jedis=null;
			try{
				key=getCacheKey(key);
				jedis=jedisPool.getResource();
				return jedis.get(key);
			}catch (Exception e){
				log.error("获取字符串值：",e);
			}finally {
				returnResource(jedis);
			}
			return null;
		}

		/**
		 * 原子性-递增数字操作。
		 * 注意：递增后，redis会自动存储数字值，但此数字值不是序列化存储，不能用序列化方法。
		 * @param key
		 * @return
		 */
		public boolean increase(String key){
			ShardedJedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				key=getCacheKey(key);
				long result=jedis.incr(key);
				if(result>0){
					return true;
				}
			}catch (Exception e){
				log.error("redis递增数据出现异常！",e);
			}finally {
				returnResource(jedis);
			}
			return false;
		}

		/**
		 * 原子性操作-递减操作。
		 * 注意：递减后，redis会自动存储数字值，但此数字值不是序列化存储，不能用序列化方法。
		 * @param key
		 * @return
		 */
		public boolean decrease(String key){
			ShardedJedis jedis=null;
			try {
				jedis=jedisPool.getResource();
				long result=jedis.decr(getCacheKey(key));
				if(result>-1){
					return true;
				}
			}catch (Exception e){
				log.error("redis递减数字出现异常！",e);
			}finally {
				returnResource(jedis);
			}
			return false;
		}

		public String showMsg(){
			return "缓存显示信息：111111111144444444444444444";
		}

		/**
		 * 从缓存中取出序列化之后的值，反序列化后再返回。
		 * @param key
		 * @return
		 */
		@Override
		public Object getBySerialize(String key) {
			ShardedJedis jedis = null;
			try {
				key = getCacheKey(key);
				jedis = jedisPool.getResource();
				byte[] result = jedis.get(key.getBytes());
				if (result == null) {
					return null;
				}
				return SerializeUtil.unserialize(result);
			} catch (Exception e) {
				log.error("get", e);
			} finally {
				returnResource(jedis);
			}
			return null;
		}


		private void returnResource(ShardedJedis jedis) {
			if (jedis != null) {
				try {
					jedisPool.returnResource(jedis);
				} catch (Exception e) {
					log.error("returnResource", e);
				}

			}
		}

		@Override
		public boolean contains(String key) {
			ShardedJedis jedis = null;
			try {
				key = getCacheKey(key);
				jedis = jedisPool.getResource();
				return jedis.exists(key);
			} catch (Exception e) {
				log.error("contains", e);
			} finally {
				returnResource(jedis);
			}
			return false;
		}

		@Override
		public void put(String key, Object value, int expireSeconds) {
			ShardedJedis jedis = null;
			try {
				key = getCacheKey(key);
				jedis = jedisPool.getResource();
				jedis.set(key.getBytes(), SerializeUtil.serialize(value));
				// 设置缓存过期时间 时间大于0才设置
				if (expireSeconds > 0) {
					jedis.expire(key, expireSeconds);
				}
			} catch (Exception e) {
				log.error("put", e);
			} finally {
				returnResource(jedis);
			}
		}

		@Override
		public void put(String key, Object value) {
			put(key, value, DataCache.EXPIRE_DEFAULT);
		}



		@Override
		public void remove(String key) {
			ShardedJedis jedis = null;
			try {
				key = getCacheKey(key);
				jedis = jedisPool.getResource();
				jedis.del(key);
			} catch (Exception e) {
				log.error("", e);
			} finally {
				returnResource(jedis);
			}
		}

		@Override
		public void removeHash(String key) {
			ShardedJedis jedis = null;
			try {
				key = getCacheKey(key);
				jedis = jedisPool.getResource();
				jedis.del(key.getBytes());
			} catch (Exception e) {
				log.error("", e);
			} finally {
				returnResource(jedis);
			}
		}

		public void removeForSerialize(String key) {
			ShardedJedis jedis = null;
			try {
				key = getCacheKey(key);
				jedis = jedisPool.getResource();
				jedis.del(SerializeUtil.serialize(key));
				// jedis.expire(key, seconds)
			} catch (Exception e) {
				log.error("", e);
			} finally {
				returnResource(jedis);
			}
		}

		private String getCacheKey(String key) {
			return this.cacheKeyPrefix + "-" + key;
		}

		@Override
		public void clear() {
		}

		@Override
		public void destroy() {
			// TODO Auto-generated method stub

		}

		/*
         * (non-Javadoc)
         *
         * @see
         * cn.com.iamgenius.framework.cache.Cache#getStringValue(java.lang.String)
         */
		@Override
		public String getStringValue(String key) {
			Object obj = this.get(key);
			if (null != obj && obj instanceof String) {
				return obj.toString();
			}
			return null;
		}


		/**
		 * 设置值到HashMap数据结构中。
		 * @param key
		 * @param field
		 * @param value
		 */
		@Override
		public void putHash(String key, String field, Object value) {
			ShardedJedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				key=getCacheKey(key);
				jedis.hset(key.getBytes(), field.getBytes(), SerializeUtil.serialize(value));
			} catch (Exception e) {
				log.error("putHash", e);
			} finally {
				returnResource(jedis);
			}
		}

		@Override
		public void removeHashField(String key, String field) {
			ShardedJedis jedis = null;
			try {
				key=getCacheKey(key);
				jedis = jedisPool.getResource();
				jedis.hdel(key, field);
			} catch (Exception e) {
				log.error("removeHashFieldWithoutPrefix", e);
			} finally {
				returnResource(jedis);
			}
		}

		@Override
		public void removeHashFieldForSerialize(String key, String field) {
			ShardedJedis jedis = null;
			try {
				key=getCacheKey(key);
				jedis = jedisPool.getResource();
				jedis.hdel(SerializeUtil.serialize(key), SerializeUtil.serialize(field));
			} catch (Exception e) {
				log.error("removeHashFieldWithoutPrefix", e);
			} finally {
				returnResource(jedis);
			}
		}

		@Override
		public Object getFromHash(String key, String field) {
			ShardedJedis jedis = null;
			try {
				key=getCacheKey(key);
				jedis = jedisPool.getResource();
				byte[] result = jedis.hget(key.getBytes(), field.getBytes());
				if (result == null) {
					return null;
				}
				return SerializeUtil.unserialize(result);
			} catch (Exception e) {
				log.error("getWithoutPrefix", e);
			} finally {
				returnResource(jedis);
			}
			return null;
		}

		@Override
		public Map<String, Object> getHashAll(String key) {
			ShardedJedis jedis = null;
			try {
				key=getCacheKey(key);
				jedis = jedisPool.getResource();
				Map<byte[], byte[]> map = jedis.hgetAll(key.getBytes());
				if (map == null || map.isEmpty()) {
					return null;
				}

				Map<String, Object> res = new HashMap<String, Object>(map.size());
				for (Map.Entry<byte[], byte[]> entry : map.entrySet()) {
					String k = new String(entry.getKey());
					Object v = SerializeUtil.unserialize(entry.getValue());
					res.put(k, v);
				}
				return res;
			} catch (Exception e) {
				log.error("getHashAllWithoutPrefix", e);
			} finally {
				returnResource(jedis);
			}
			return null;
		}



		@Override
		public void setExpiretime(String key, int expireSeconds) {
			ShardedJedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				// 设置缓存过期时间 时间大于0才设置
				if (expireSeconds > 0) {
					jedis.expire(key, expireSeconds);
				}
			} catch (Exception e) {
				log.error("putHash", e);
			} finally {
				returnResource(jedis);
			}
		}

	/**
	 * 反序列化获取对象。必须与后缀为Serialize的方法相匹配。 获取指定名称的Hash中存储的对应字段的值
	 *
	 * @param key
	 *            哈希名
	 * @param field
	 *            字段名
	 * @return 如果不存在指定的hash或该Hash不存在指定名称的字段，返回null, 否则返回正确的关联字段
	 */
	@Override
	public Object hgetForSerialize(String key, String field) {
		ShardedJedis jedis = null;
		try {
			key = getCacheKey(key);
			jedis = jedisPool.getResource();
			byte[] object = jedis.hget(SerializeUtil.serialize(key), SerializeUtil.serialize(field));
			if (object != null) {
				return SerializeUtil.unserialize(object);
			}
		} catch (Exception e) {
			log.error("hget", e);
		} finally {
			returnResource(jedis);
		}
		return null;
	}

	public String getExpireTimeCacheKey(String fieldName){
		return fieldName+EXPIRE_KEY_PREFIX;
	}

	/**
	 * 序列化保存对象，必须与其它以Serialize为后缀的方法搭配使用。
	 * 向指定名称的hash中添加字段名为field,值为value的元素,永不过期。
	 * @param key 哈希名  缓存对象集合名称
	 * @param field 字段名
	 * @param value 字段值
	 */
	public void hsetForSerialize(String key, String field, Object value) {
		ShardedJedis jedis = null;
		try {
			key = getCacheKey(key);
			jedis = jedisPool.getResource();
			jedis.hset(SerializeUtil.serialize(key), SerializeUtil.serialize(field), SerializeUtil.serialize(value));
			long curTime=DateUtil.getCurrentUnixTimeSecond();
			jedis.hset(SerializeUtil.serialize(key),SerializeUtil.serialize(getExpireTimeCacheKey(field)),
					SerializeUtil.serialize(curTime));
		} catch (Exception e) {
			log.error("hset", e);
		} finally {
			returnResource(jedis);
		}
	}


	public ShardedJedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(ShardedJedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
}
