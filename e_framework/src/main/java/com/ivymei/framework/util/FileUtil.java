package com.ivymei.framework.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

public class FileUtil {

	private static Logger log = Logger.getLogger(FileUtil.class);

	public static File create(String filename, byte[] content) {

		File file = new File(filename);
		if (!file.exists()) {
			new File(file.getParent()).mkdirs();
		}
//		File path = file.getParentFile();
//		if (!path.exists()) {
//			path.mkdir();
//		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(content);
		} catch (IOException e) {
			// e.printStackTrace();
			log.error(e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return file;
	}

	public static void delete(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				for (File sonFile : file.listFiles()) {
					if (sonFile.exists()) {
						if (sonFile.isDirectory()) {
							delete(sonFile);
						}
						sonFile.delete();
					}
				}
			}
			file.delete();
		}
	}

}
