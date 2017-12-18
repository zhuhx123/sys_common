package com.ivymei.system.common.plugin.ice.server;

import org.apache.log4j.Logger;

import Ice.Application;
import Ice.ObjectAdapter;
import Ice.ObjectImpl;
import Ice.ObjectPrx;


/**
 * ICE服务器
 * 
 * @author liuxh
 * 
 */
public class ICEServer extends Application implements Runnable {
	private final static Logger logger = Logger.getLogger(ICEServer.class);

	private static ICEServer server;
	public static ICEServer getInstance() {
		if (server == null) {
			server = new ICEServer();
		}

		return server;
	}
	private String configFileName; // ICE配置文件路径
	private String iceServerName; // ICE服务器名称
	private ObjectImpl[] impls; // ICE服务实现类实例

	private String[] serviceNames; // ICE服务实例名称

	private ICEServer() {
		super();
	}

	/**
	 * 根据ICE连接串获取ICE代理
	 * 
	 * @param iceUrl
	 * @return
	 */
	public ObjectPrx getPrx(String iceUrl) {
		if (communicator() != null) {
			return communicator().stringToProxy(iceUrl);
		} else {
			try {
				Thread.sleep(3500);

				if (communicator() != null) {
					return communicator().stringToProxy(iceUrl);
				}
			} catch (Exception e) {
				logger.error("ICE启动可能出现了问题，无法取得ICE代理......",e);
			}
		}

		return null;
	}

	@Override
	public void run() {
		main(this.iceServerName, new String[] {}, configFileName);
	}

	/**
	 * 启动ICE服务器
	 */
	@Override
	public int run(String[] args) {
		setInterruptHook(new Thread() {
			@Override
			public void run() {
				ICEServer.this.stopIceServer();
			}
		});

		try {
			ICEServer.this.startIceServer(args);
		} finally {
			ICEServer.this.stopIceServer();
		}

		return 0;
	}

	/**
	 * 参数配置
	 * 
	 * @param configFileName
	 * @param impls
	 * @param serviceNames
	 */
	public void setParams(String configFileName, String iceServerName, ObjectImpl[] impls, String[] serviceNames) {
		this.configFileName = configFileName;
		this.iceServerName = iceServerName;
		this.impls = impls;
		this.serviceNames = serviceNames;
	}

	/**
	 * 启动ICE服务实例
	 * 
	 * @param args
	 */
	public void startIceServer(String[] args) {
		logger.info("开始启动ICE服务器......");

		try {
			if (impls != null && impls.length > 0) {
				ObjectAdapter adapter = communicator().createObjectAdapter(this.iceServerName);

				for (int i = 0; i < this.impls.length; i++) {
					adapter.add(impls[i], Ice.Util.stringToIdentity(this.serviceNames[i]));
				}

				adapter.activate();

				logger.info("ICE服务器启动成功...");
			} else {
				logger.info("没有ICE实现类实例......");
			}

			communicator().waitForShutdown();
		} catch (Exception e) {
			logger.error("ICE服务器启动失败......", e);
		}
	}

	/**
	 * 关闭ICE服务器
	 */
	public void stopIceServer() {
		try {
			if (communicator() != null) {
				communicator().destroy();
				logger.info("ICE服务器关闭成功......");
			}
		} catch (Exception ex) {
			logger.info("ICE服务器关闭异常......",ex);
		}
	}
}
