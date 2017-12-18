package com.ivymei.framework.plugin.cache;

import java.util.ArrayList;
import java.util.List;

import com.ivymei.framework.util.ConfigReaderUtil;
import com.ivymei.framework.util.StringUtil;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class RedisFactory {
	private static String cacheKeyPrefix="table";//初始化为table，如有需要，则改为各库名称。
	private static String ips;
	private static int maxIdle; 
	//以秒为单位
	private static long maxWait;
	private static boolean testOnBorrow;
	private static int timeout = 3000;//3秒
	private static Cache redisDataCache;
	//单位为秒。10分钟
	public static final int EXPIRE_DEFAULT=60*10;
	
    public static Cache getDataCache(){
    	init();
    	return redisDataCache;
    }
    
    private static void init(){
    	
    	if(redisDataCache != null){
    		return;
    	}
    	
    	ConfigReaderUtil.init("configs/redis");
    	ips=ConfigReaderUtil.getValue("redis.ips");
    	maxIdle=Integer.parseInt(ConfigReaderUtil.getValue("redis.maxIdle"));
    	testOnBorrow=Boolean.parseBoolean(ConfigReaderUtil.getValue("redis.testOnBorrow"));
    	List<JedisShardInfo> shardInfos=getShardInfos();
    	JedisPoolConfig jedisPoolConfig=initJedisPoolConfig();
    	
    	ShardedJedisPool jedisPool = new ShardedJedisPool(jedisPoolConfig, shardInfos);
    	 redisDataCache=new RedisDataCache(jedisPool, cacheKeyPrefix);
    	
    }
	
    
    private static  List<JedisShardInfo> getShardInfos(){
    	if(StringUtil.isNullOrEmpty(ips)){
    		throw new IllegalArgumentException("抱歉，redis的地址不能为空");
    	}
    	List<JedisShardInfo> shards=new ArrayList<>();
    	String [] ipsArr=ips.split(",");
    	for(String ipPort:ipsArr){
    		String[] ipAndPort = ipPort.split(":");
			String ip = ipAndPort[0];
			String port = ipAndPort[1];
			String password ="";
			if(ipAndPort.length>=3){
				password = ipAndPort[2];
				
			}
			JedisShardInfo jedisShardInfo = new JedisShardInfo(ip, Integer.parseInt(port), timeout);
			//如果服务端redis配置文件中没有设置密码，这里一定不能设置，否则，将抛异常。
			if(!StringUtil.isNullOrEmpty(password)){
				
				jedisShardInfo.setPassword(password);
			}
			
			shards.add(jedisShardInfo);
    	}
    	return shards;
    }
    
    /**
	 * 使用默认的jedisPoolConfig
	 */
	private static JedisPoolConfig initJedisPoolConfig() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
			// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			jedisPoolConfig.setMaxIdle(maxIdle);
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			maxWait=maxWait*1000;
			jedisPoolConfig.setMaxWaitMillis(maxWait);
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			jedisPoolConfig.setTestOnBorrow(testOnBorrow);
			// 当调用return Object方法时，是否进行有效性检查
			jedisPoolConfig.setTestOnReturn(true);
			return jedisPoolConfig;
	}
}
