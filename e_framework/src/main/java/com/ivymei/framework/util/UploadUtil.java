package com.ivymei.framework.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * 上传工具类
 * 
 * @author show
 *
 */
public class UploadUtil {

	private static Logger log = Logger.getLogger(UploadUtil.class);

	private static final String uploadImageUrl = "http://fs.jinhuatuo.com/image/upload";

	private static final String uploadFileUrl = "http://fs.jinhuatuo.com/file/upload";

	private static Integer CONNECT_TIMEOUT = 1000 * 10;// 10秒
	private static Integer READ_TIMEOUT = 1000 * 20;// 20秒

	private static String BOUNDARY = "---------------------------Ij5gL6Ij5GI3cH2ae0ei4Ef1ae0ae0"; // boundary就是request头和上传文件内容的分隔符(这里是随机的)

	public static void main(String[] args) throws Exception {
		// File file = new File("D:/image.png");
		File file = new File("D:/a.xls");

		Map<String, String> params = new HashMap<String, String>();
		params.put("kkk", "ccc");

		String result = uploadFile(file, params);
		System.out.println(result);
	}

	/**
	 * 上传需要验证登录才能访问的图片
	 * 
	 * @param image
	 * @return <pre>
	 * http://photocdn.sohu.com/20160421/Img445347912.jpeg
	 * </pre>
	 * @throws Exception
	 */
	public static String uploadPrivateImage(MultipartFile image) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("isPrivate", "true");
		return uploadImage(image, params);
	}

	/**
	 * 上传需要验证登录才能访问的图片
	 * 
	 * @param image
	 * @return <pre>
	 * http://photocdn.sohu.com/20160421/Img445347912.jpeg
	 * </pre>
	 * @throws Exception
	 */
	public static String uploadPrivateImage(File image) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("isPrivate", "true");
		return uploadImage(image, params);
	}

	/**
	 * 上传图片
	 * 
	 * @param image
	 * @return <pre>
	 * http://photocdn.sohu.com/20160421/Img445347912.jpeg
	 * </pre>
	 * @throws Exception
	 */
	public static String uploadImage(File image) throws Exception {
		return uploadImage(image, null);
	}

	/**
	 * 上传图片
	 * 
	 * @param image
	 * @return <pre>
	 * http://photocdn.sohu.com/20160421/Img445347912.jpeg
	 * </pre>
	 * @throws Exception
	 */
	public static String uploadImage(MultipartFile image) throws Exception {
		return uploadImage(image, null);
	}

	/**
	 * 上传图片
	 * 
	 * @param image
	 * @param params
	 * @return <pre>
	 * http://photocdn.sohu.com/20160421/Img445347912.jpeg
	 * </pre>
	 * @throws Exception
	 */
	public static String uploadImage(File image, Map<String, String> params) throws Exception {
		UploadResponse response = uploadResources(image, UploadTypeEnum.IMAGE, params);
		return response.getResult().getImgUrl();
	}

	/**
	 * 上传图片
	 * 
	 * @param image
	 * @param params
	 * @return <pre>
	 * http://photocdn.sohu.com/20160421/Img445347912.jpeg
	 * </pre>
	 * @throws Exception
	 */
	public static String uploadImage(MultipartFile image, Map<String, String> params) throws Exception {
		String result = "";

		String originalFilename = image.getOriginalFilename();
		String prefix = image.getName() + "_";
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

		File file = File.createTempFile(prefix, suffix);
		OutputStream output = null;
		BufferedOutputStream bufferedOutput = null;
		try {
			output = new FileOutputStream(file);
			bufferedOutput = new BufferedOutputStream(output);
			bufferedOutput.write(image.getBytes());

			result = uploadImage(file, params);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				output.close();
			}
			if (bufferedOutput != null) {
				bufferedOutput.close();
			}
			file.delete();
		}

		return result;
	}

	/**
	 * 上传文件
	 * 
	 * @param newFile
	 * @return <pre>
	 * http://img.jinhuatuo.com/files/2016/04/23/220627d8f8ff4c2197706b07e53943c3.xls
	 * </pre>
	 * @throws Exception
	 */
	public static String uploadFile(File newFile) throws Exception {
		return uploadFile(newFile, null);
	}

	/**
	 * 上传文件
	 * 
	 * @param newFile
	 * @return <pre>
	 * http://img.jinhuatuo.com/files/2016/04/23/220627d8f8ff4c2197706b07e53943c3.xls
	 * </pre>
	 * @throws Exception
	 */
	public static String uploadFile(MultipartFile newFile) throws Exception {
		return uploadFile(newFile, null);
	}

	/**
	 * 上传文件
	 * 
	 * @param newFile
	 * @param params
	 * @return <pre>
	 * http://img.jinhuatuo.com/files/2016/04/23/220627d8f8ff4c2197706b07e53943c3.xls
	 * </pre>
	 * @throws Exception
	 */
	public static String uploadFile(MultipartFile newFile, Map<String, String> params) throws Exception {
		String result = "";

		String originalFilename = newFile.getOriginalFilename();
		String prefix = newFile.getName() + "_";
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

		File file = File.createTempFile(prefix, suffix);
		OutputStream output = null;
		BufferedOutputStream bufferedOutput = null;
		try {
			output = new FileOutputStream(file);
			bufferedOutput = new BufferedOutputStream(output);
			bufferedOutput.write(newFile.getBytes());

			result = uploadFile(file, params);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				output.close();
			}
			if (bufferedOutput != null) {
				bufferedOutput.close();
			}
			file.delete();
		}

		return result;
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param params
	 * @return <pre>
	 * http://img.jinhuatuo.com/files/2016/04/23/220627d8f8ff4c2197706b07e53943c3.xls
	 * </pre>
	 * @throws Exception
	 */
	public static String uploadFile(File file, Map<String, String> params) throws Exception {
		UploadResponse response = uploadResources(file, UploadTypeEnum.FILE, params);
		return response.getResult().getUrl();
	}

	/**
	 * 上传类型
	 * 
	 * @author jht
	 *
	 */
	static enum UploadTypeEnum {
		IMAGE(uploadImageUrl, "image"), //
		FILE(uploadFileUrl, "file");

		private UploadTypeEnum(String uploadUrl, String formName) {
			this.uploadUrl = uploadUrl;
			this.formName = formName;
		}

		private String uploadUrl;
		private String formName;

		public String getUploadUrl() {
			return uploadUrl;
		}

		public String getFormName() {
			return formName;
		}
	}

	private static HttpURLConnection makeConnection(String uri) throws Exception {
		HttpURLConnection conn = null;
		URL url = new URL(uri);
		conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(CONNECT_TIMEOUT);
		conn.setReadTimeout(READ_TIMEOUT);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
		conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		return conn;
	}

	private static UploadResponse uploadResources(File file, UploadTypeEnum type, Map<String, String> params)
		throws Exception {

		String res = "";
		HttpURLConnection conn = null;
		try {

			conn = makeConnection(type.getUploadUrl());

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// 参数
			if (params != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator<Map.Entry<String, String>> iter = params.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}

			String fileName = file.getName();
//			MagicMatch match = Magic.getMagicMatch(file, false, true);
//			String contentType = match.getMimeType();
			
			Path path = Paths.get(file.getPath());
			String contentType = Files.probeContentType(path);

			StringBuffer strBuf = new StringBuffer();
			strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
			strBuf.append("Content-Disposition: form-data; name=\"" + type.getFormName() + "\"; filename=\"" + fileName
					+ "\"\r\n");
			strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

			out.write(strBuf.toString().getBytes());

			InputStream in = new FileInputStream(file);
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			in.close();

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// 读取返回数据
			StringBuffer resultBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				resultBuf.append(line).append("\n");
			}
			res = resultBuf.toString();
			if (reader != null) {
				reader.close();
				reader = null;
			}
		} catch (Exception e) {
			log.error("上传文件发生异常：", e);
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}

		UploadResponse response = JSONObject.parseObject(res, UploadResponse.class);

		return response;
	}

	public static class UploadResponse {

		private int msgCode;
		private String message;
		private UploadResopnseResult result;

		public int getMsgCode() {
			return msgCode;
		}

		public void setMsgCode(int msgCode) {
			this.msgCode = msgCode;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public UploadResopnseResult getResult() {
			return result;
		}

		public void setResult(UploadResopnseResult result) {
			this.result = result;
		}
	}

	public static class UploadResopnseResult {
		private String url;
		private String imgUrl;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getImgUrl() {
			return imgUrl;
		}

		public void setImgUrl(String imgUrl) {
			this.imgUrl = imgUrl;
		}
	}
}
