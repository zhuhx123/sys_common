package com.ivymei.framework.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ConfigReaderUtil {
	private static ResourceBundle configMap = null;
	private static String propertiesFilePath;//相对于classes目录的配置路径。
	

	/**
	 * 初始化系统配置文件
	 * 注意不要加后缀.properties
	 * configs/configs 
	 * @param fileName
	 */
	public static void init(String fileName) {
		if(!StringUtil.isNotBlank(fileName)){
			int index=fileName.indexOf(".");
			if(index>-1){
				throw new IllegalArgumentException("抱歉，请不要加后缀。");
			}
		}
		propertiesFilePath=fileName;
		configMap=PropertyResourceBundle.getBundle(propertiesFilePath);
	}

	/**
	 * 通过Key获得配置信息
	 * 
	 * @param keyName
	 * @return
	 */
	public static String getValue(String keyName) {
		if(StringUtil.isNullOrBlank(propertiesFilePath)){
			throw new IllegalArgumentException("配置文件路径不能为空！");
		}
		if (configMap == null) {
			configMap=PropertyResourceBundle.getBundle(propertiesFilePath);
		}
		if (configMap.getString(keyName) == null) {
			return "";
		}
		return (String) configMap.getString(keyName);
	}
	/**
	 * 通过Key获得配置信息
	 * 
	 * @param keyName
	 * @return
	 */
	public static String getValue(String fileName,String keyName) {
		if(StringUtil.isNullOrBlank(fileName)){
			throw new IllegalArgumentException("文件路径不能为空");
		}
		if(StringUtil.isNullOrBlank(propertiesFilePath)){
			propertiesFilePath=fileName;
			init(fileName);
		}
		if (configMap == null) {
			configMap=PropertyResourceBundle.getBundle(propertiesFilePath);
		}
		if (configMap.getString(keyName) == null) {
			return "";
		}
		return (String) configMap.getString(keyName);
	}
	
	
	public static void main(String [] args){
		
		/*URL url=ConfigReaderUtil.class.getClassLoader().getResource("");
		String path=url.getPath();
		File file=new File(path);
		String parentPath=file.getParentFile().getParentFile().getPath();
		System.out.println("url="+url+",path="+path+",parentPath="+parentPath);*/
		String fileName="index.properties";
		int index=fileName.indexOf(".");
		System.out.println("index:"+index);
	}
}
