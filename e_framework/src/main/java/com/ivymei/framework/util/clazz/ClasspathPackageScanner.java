package com.ivymei.framework.util.clazz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClasspathPackageScanner {

	private static Logger logger = LoggerFactory.getLogger(ClasspathPackageScanner.class);

	private String basePackage;
	private ClassLoader cl;

	/**
	 * Construct an instance and specify the base package it should scan.
	 * 
	 * @param basePackage
	 *            The base package to scan.
	 */
	public ClasspathPackageScanner(String basePackage) {
		this.basePackage = basePackage;
		this.cl = getClass().getClassLoader();

	}

	/**
	 * Construct an instance with base package and class loader.
	 * 
	 * @param basePackage
	 *            The base package to scan.
	 * @param cl
	 *            Use this class load to locate the package.
	 */
	public ClasspathPackageScanner(String basePackage, ClassLoader cl) {
		this.basePackage = basePackage;
		this.cl = cl;
	}

	/**
	 * 获取包{@code basePackage} 下所有类的完全限定名，如：
	 * <p>
	 * [com.ivymei.test.ClassName1,com.ivymei.test.ClassName2 ]
	 * </p>
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<String> getFullyQualifiedClassNameList() throws IOException {
		return doScan(basePackage, new ArrayList<String>());
	}

	/**
	 * 扫描包{@code basePackage}下所有类
	 * 
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public List<Class> scanPackageClass() throws IOException {

		List<Class> clazzes = new ArrayList<Class>();
		List<String> clazzNames = getFullyQualifiedClassNameList();

		if (clazzNames != null && clazzNames.size() > 0) {
			for (String name : clazzNames) {
				try {
					Class clazz = Class.forName(name);
					clazzes.add(clazz);
				} catch (ClassNotFoundException e) {
					logger.error("找不类：", e);
				}
			}
		}

		return clazzes;
	}

	private List<String> doScan(String basePackage, List<String> nameList) throws IOException {
		// replace dots with splashes
		String splashPath = StringUtil.dotToSplash(basePackage);

		// get file path
		URL url = cl.getResource(splashPath);
		String filePath = StringUtil.getRootPath(url);

		// Get classes in that package.
		// If the web server unzips the jar file, then the classes will exist in
		// the form of
		// normal file in the directory.
		// If the web server does not unzip the jar file, then classes will
		// exist in jar file.
		List<String> names = null; // contains the name of the class file. e.g.,
		// Apple.class will be stored as "Apple"

		boolean isJarFile = isJarFile(filePath);

		if (isJarFile) {

			names = readFromJarFile(filePath, splashPath);
		} else {
			// directory
			// if (logger.isDebugEnabled()) {
			// logger.debug("{} 是一个目录", filePath);
			// }

			names = readFromDirectory(filePath);
		}

		for (String name : names) {
			if (isClassFile(name)) {
				// nameList.add(basePackage + "." +
				// StringUtil.trimExtension(name));
				if (isJarFile) {
					nameList.add(StringUtil.trimExtension(name).replaceAll("/", "."));
				} else {
					nameList.add(toFullyQualifiedName(name, basePackage));
				}
			} else {
				// this is a directory
				// check this directory for more classes
				// do recursive invocation
				doScan(basePackage + "." + name, nameList);
			}
		}

		// if (logger.isDebugEnabled()) {
		// for (String n : nameList) {
		// logger.debug("找到{}", n);
		// }
		// }

		return nameList;
	}

	/**
	 * Convert short class name to fully qualified name. e.g., String ->
	 * java.lang.String
	 */
	private String toFullyQualifiedName(String shortName, String basePackage) {
		StringBuilder sb = new StringBuilder(basePackage);
		sb.append('.');
		sb.append(StringUtil.trimExtension(shortName));

		return sb.toString();
	}

	/**
	 * 读取jar包中指定包名的文件名
	 * 
	 * @param jarPath
	 * @param splashedPackageName
	 * @return
	 * @throws IOException
	 */
	private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
		List<String> nameList = new ArrayList<String>();
		JarInputStream jarIn = null;
		try {
			jarIn = new JarInputStream(new FileInputStream(jarPath));
			JarEntry entry = jarIn.getNextJarEntry();

			while (null != entry) {
				String name = entry.getName();
				if (name.startsWith(splashedPackageName) && isClassFile(name)) {
					nameList.add(name);
				}
				entry = jarIn.getNextJarEntry();
			}
		} catch (Exception e) {
			logger.error("读取jar包异常：", e);
		} finally {
			if (jarIn != null) {
				jarIn.close();
			}
		}

		return nameList;
	}

	private List<String> readFromDirectory(String path) {
		File file = new File(path);
		String[] names = file.list();

		if (null == names) {
			return null;
		}

		return Arrays.asList(names);
	}

	private boolean isClassFile(String name) {
		return name.endsWith(".class");
	}

	private boolean isJarFile(String name) {
		return name.endsWith(".jar");
	}

	/**
	 * For test purpose.
	 */
	public static void main(String[] args) throws Exception {
		ClasspathPackageScanner scan = new ClasspathPackageScanner("com.ivymei.framework.util.clazz");
		scan.getFullyQualifiedClassNameList();
	}

	static class StringUtil {

		private StringUtil() {

		}

		/**
		 * "file:/home/whf/cn/fh" -> "/home/whf/cn/fh"
		 * "jar:file:/home/whf/foo.jar!cn/fh" -> "/home/whf/foo.jar"
		 */
		public static String getRootPath(URL url) {
			String fileUrl = url.getFile();
			int pos = fileUrl.indexOf('!');

			if (-1 == pos) {
				return fileUrl;
			}

			return fileUrl.substring(5, pos);
		}

		/**
		 * "cn.fh.lightning" -> "cn/fh/lightning"
		 * 
		 * @param name
		 * @return
		 */
		public static String dotToSplash(String name) {
			return name.replaceAll("\\.", "/");
		}

		/**
		 * "Apple.class" -> "Apple"
		 */
		public static String trimExtension(String name) {
			int pos = name.indexOf('.');
			if (-1 != pos) {
				return name.substring(0, pos);
			}

			return name;
		}

		/**
		 * /application/home -> /home
		 * 
		 * @param uri
		 * @return
		 */
		public static String trimURI(String uri) {
			String trimmed = uri.substring(1);
			int splashIndex = trimmed.indexOf('/');

			return trimmed.substring(splashIndex);
		}
	}
}
