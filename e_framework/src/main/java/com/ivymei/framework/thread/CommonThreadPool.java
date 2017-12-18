package com.ivymei.framework.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonThreadPool {
	static ThreadPoolExecutor executor = null;
	static BlockingQueue<Runnable> workQueue = null;
	private static int threadCount = 0;
	private static final String appName = "《后台业务处理线程池》";
	private static final Logger log = LoggerFactory.getLogger(CommonThreadPool.class);
	
	// private static Logger log =
	// LoggerFactory.getLogger(CommonThreadPool.class);

	static void init() {

		try {
			final Integer THREAD_QUEUE_SIZE = 10000;// Integer.parseInt(PropertiesUtil.getConfigValue("ThreadQueueSize"));
			final Integer CORE_POOL_SIZE = 10;// Integer.parseInt(PropertiesUtil.getConfigValue("CorePoolSize"));
			final Integer MAX_POOL_SIZE = 15;// Integer.parseInt(PropertiesUtil.getConfigValue("MaxPoolSize"));

			if (workQueue == null) {
				workQueue = new ArrayBlockingQueue<Runnable>(THREAD_QUEUE_SIZE);
			}

			if (executor == null) {
				executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 10,
						TimeUnit.SECONDS, workQueue);
				executor.allowCoreThreadTimeOut(true);
			}

		} catch (Exception t) {
			log.error("初始化" + appName + "线程池时出现异常......" + t.getMessage(), t);
		}
	}

	public static void execute(Runnable runnable) {
		if (executor == null || workQueue == null) {
			init();
		}

		executor.execute(runnable);
		// upThreadSize();
	}

	public static int getThreadCount() {
		return threadCount;
	}

}
