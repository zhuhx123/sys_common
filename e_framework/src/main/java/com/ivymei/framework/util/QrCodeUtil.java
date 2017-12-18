package com.ivymei.framework.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * 二维码相关帮助类
 * 
 * @author zhongjl
 * @date 2016年5月31日下午4:29:32
 * @version 1.0
 */
public class QrCodeUtil {
	
	/**
	 * 根据URL生成二维码文件
	 * 
	 * @param url
	 * 			二维码URL
	 * @param outFile
	 * 			输出的文件
	 * @throws Exception
	 */
	public static void getQrCodeByUrl(String url, File outFile) throws Exception {
		Hashtable<EncodeHintType, Object> hints= new Hashtable<EncodeHintType, Object>();   
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
		BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 1024, 1024,hints);   
		MatrixToImageWriter.writeToFile(bitMatrix, "png", outFile);
	}


	public static void CreateImage(String content,String path,int size)throws Exception{
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, size, size,hints);
		File file = new File(path);
		MatrixToImageWriter.writeToFile(bitMatrix, "png", file);
	}
	
	
	
	
	
}
