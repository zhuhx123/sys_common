package com.ivymei.framework.plugin.cache.reids;

import com.ivymei.framework.plugin.cache.DataCache;
import com.ivymei.framework.plugin.cache.RedisDataCache;
import com.ivymei.framework.util.ConfigReaderUtil;
import com.ivymei.framework.util.SerializeUtil;
import com.ivymei.framework.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;
import redis.clients.util.Pool;

/**
 * Created by 20170331 on 2017/7/15.
 */
public class RedisHelper {

    private static Logger log = LoggerFactory.getLogger(RedisHelper.class);

    private Pool<Jedis> jedisPool;

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 6379;
    private final static int TIMEOUT = 5000;
    private final static int MAX_TOTAL = 1;
    private final static int MAX_IDLE = 5;
    private final static int MAX_WAIT_MILLIS = 5000;
    private final static boolean TEST_ON_BORROW = false;
    private final static boolean TEST_ON_RETURN = true;
    private final static JedisPoolConfig POOLCONFIG;

    private static RedisHelper instance;

    static {
        POOLCONFIG = new JedisPoolConfig();
        POOLCONFIG.setMaxTotal(MAX_TOTAL);
        POOLCONFIG.setMaxIdle(MAX_IDLE);
        POOLCONFIG.setMaxWaitMillis(MAX_WAIT_MILLIS);
        POOLCONFIG.setTestOnBorrow(TEST_ON_BORROW);
        POOLCONFIG.setTestOnReturn(TEST_ON_RETURN);

        init();
    }

    public RedisHelper() {
        this(HOST, PORT, TIMEOUT);
    }

    public RedisHelper(String host, int port, int timeout) {
        this(host, port, timeout, null, POOLCONFIG);
    }

    public RedisHelper(String host, int port, int timeout, String password) {
        this(host, port, timeout, password, POOLCONFIG);
    }

    public RedisHelper(JedisPoolConfig poolConfig) {
        this(new JedisPool(poolConfig, HOST, PORT, TIMEOUT));
    }

    public RedisHelper(String host, int port, int timeout, String password, JedisPoolConfig poolConfig) {
        this(new JedisPool(poolConfig, host, port, timeout, password));
    }

    public RedisHelper(Pool<Jedis> jedisPool) {
        this.jedisPool = jedisPool;
    }

    private static void init() {
        ConfigReaderUtil.init("configs/redis");
        String ips = ConfigReaderUtil.getValue("redis.ips");
        if (StringUtil.isNullOrBlank(ips)) {
            throw new IllegalArgumentException("抱歉，redis的地址不能为空");
        }

        String[] ipsArr = ips.split(",");
        String[] ipAndPort = ipsArr[0].split(":");
        String ip = ipAndPort[0];
        String port = ipAndPort[1];
        String password = "";
        if (ipAndPort.length >= 3) {
            password = ipAndPort[2];
        }

        instance = new RedisHelper(ip, Integer.parseInt(port), TIMEOUT, password);
    }

    public static RedisHelper getInstance() {
        if (instance == null) {
            init();
        }
        return instance;
    }

    /**
     * 定阅消息
     *
     * @param jedisPubSub
     * @param channels
     */
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.subscribe(jedisPubSub, channels);
        } catch (Exception e) {
            log.error("subscribe", e);
        } finally {
            returnJedis(jedis);
        }
    }

    /**
     * 发布消息
     *
     * @param channel
     * @param message
     */
    public void publish(String channel, String message) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            jedis.publish(channel, message);
        } catch (Exception e) {
            log.error("subscribe", e);
        } finally {
            returnJedis(jedis);
        }
    }

    public Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        return jedis;
    }

    public void returnJedis(Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }

}
