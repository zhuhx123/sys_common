/********************************************************************
 * 文件名：StringUtil.java
 * 功能描述：字符串相关操作类
 * 版本号： 1.0
 * 创建日期：2006.11.20
 * 修改记录：
 * (1)原文件包括StringUitl，StringApp，StringKit以及其他一些类中的String操作
 * (2)2006.11.20 14：30:Tiger整理本文件
 ********************************************************************/
package com.ivymei.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang.math.NumberUtils;

import com.thoughtworks.xstream.XStream;

/**
 * <p>
 * 通用字符串处理操作类
 * </p>
 * <br>
 * <p>
 * Description: 可以作为通用字符串处理的组件。
 * </p>
 */
public class StringUtil {
	
	private static final String DEFAULT_SEPARATOR = ",";
	
	private static Random rnd = new Random();
//	private static final Logger log = LoggerFactory.getLogger(StringUtil.class);

	final static char[] digits = {
			'0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
	};

	final static String digitsBase36 = "0123456789abcdefghijklmnopqrstuvwxyz";
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.equals("");
	}
	public static boolean validateUserName(String userName){
		String pattern="^(([\\u4e00-\\u9fa5]+\\s?)+|([a-z]+\\s?)+)$";
		return Pattern.matches(pattern, userName);
	}
	/**
	 * 判断一个字符串是否为null或是空字符串
	 * <p>
	 * 
	 * @param str
	 *            The string for checking
	 * @return true if the string is neither null nor empty string
	 */
	public static boolean isEmpty(String str) {
		return str == null || (str.trim().length() == 0);
	}

	public static boolean isEmpty(Object obj) {
		return obj == null || isEmpty(obj.toString());
	}
	
	public static boolean isEmpty(Integer num) {
		return num==null || num.equals(0);
	}

	/**
	 * byte数组转化为16进制的String
	 * <p>
	 * 
	 * @param byteData
	 *            byte[] 字节数组
	 * @return String
	 *         <p>
	 *         把字节数组转换成可视字符串
	 *         </p>
	 */
	public static String toHex(byte byteData[]) {
		return toHex(byteData, 0, byteData.length);
	}

	/**
	 * 将字符串data按照encode转化为byte数组，然后转化为16进制的String
	 * 
	 * @param data
	 *            源字符串
	 * @param encode
	 *            字符编码
	 * @return 把字节数组转换成可视字符串
	 */
	public static String toHex(String data, String encode) {
		try {
			return toHex(data.getBytes(encode));
		} catch (Exception e) {
//			log.error("toHex:" + data + ",encode:" + encode);
		}
		return "";
	}

	/**
	 * byte转化为16进制的String
	 * 
	 * @param b
	 * @return 16进制的String
	 */
	public static String toHex(byte b) {
		byte[] buf = { b };
		return toHex(buf);
	}

	/*
	 * public static String toHex2(byte byteData[], int off, int len) {
	 * //等效于下面的toHex，但速度慢10倍以上 StringBuffer buf = new StringBuffer(len * 2); int
	 * i;
	 * 
	 * for (i = off; i < len; i++) { if ( ( (int) byteData[i] & 0xff) < 0x10) {
	 * buf.append("0"); } buf.append(Integer.toString( (int) byteData[i] & 0xff,
	 * 16)); } return buf.toString(); }
	 */
	/**
	 * byte数组的部分字节转化为16进制的String
	 * 
	 * @param byteData
	 *            待转换的byte数组
	 * @param offset
	 *            开始位置
	 * @param len
	 *            字节数
	 * @return 16进制的String
	 */
	public static String toHex(byte byteData[], int offset, int len) {
		char buf[] = new char[len * 2];
		int k = 0;
		for (int i = offset; i < len; i++) {
			buf[k++] = digits[((int) byteData[i] & 0xff) >> 4];
			buf[k++] = digits[((int) byteData[i] & 0xff) % 16];
		}
		return new String(buf);
	}

	/*
	 * public static byte[] hex2Bytes( String hex ) { //等效于下面的hex2Bytes，但速度慢8倍以上
	 * if( isEmpty(hex) || hex.length() %2> 0 ) {
	 * log.error("hex2Bytes: invalid HEX string:" + hex ); return null; } int
	 * len = hex.length() /2; byte[] ret = new byte[ len ]; int k = 0; for (int
	 * i = 0; i < len; i++) { ret[i] = (byte)Integer.parseInt(
	 * hex.substring(k,k+2), 16 ); k += 2; } return ret; }
	 */
	/**
	 * 将16进制的字符串转换为byte数组，是toHex的逆运算
	 * 
	 * @param hex
	 *            16进制的字符串
	 * @return byte数组
	 */
	public static byte[] hex2Bytes(String hex) {
		if (isEmpty(hex) || hex.length() % 2 > 0) {
//			log.error("hex2Bytes: invalid HEX string:" + hex);
			return null;
		}
		int len = hex.length() / 2;
		byte[] ret = new byte[len];
		int k = 0;
		for (int i = 0; i < len; i++) {
			int c = hex.charAt(k++);
			if (c >= '0' && c <= '9')
				c = c - '0';
			else if (c >= 'a' && c <= 'f')
				c = c - 'a' + 10;
			else if (c >= 'A' && c <= 'F')
				c = c - 'A' + 10;
			else {
//				log.error("hex2Bytes: invalid HEX string:" + hex);
				return null;
			}
			ret[i] = (byte) (c << 4);
			c = hex.charAt(k++);
			if (c >= '0' && c <= '9')
				c = c - '0';
			else if (c >= 'a' && c <= 'f')
				c = c - 'a' + 10;
			else if (c >= 'A' && c <= 'F')
				c = c - 'A' + 10;
			else {
//				log.error("hex2Bytes: invalid HEX string:" + hex);
				return null;
			}
			ret[i] += (byte) c;
		}
		return ret;
	}


	/**
	 * 判断非负数的整数或者携带一位或者两位的小数
	 * @param obj
	 * @return
     */
	public static boolean judgeTwoDecimal(Object obj){
		boolean flag = false;
		try {
			if(obj != null){
				String source = obj.toString();
				// 判断是否是整数或者是携带一位或者两位的小数
				Pattern pattern = Pattern.compile("^[+]?([0-9]+(.[0-9]{1,2})?)$");
				if(pattern.matcher(source).matches()){
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 字符编码转换
	 * 
	 * @param str
	 *            源字符串
	 * @param srcCharset
	 *            源字符串的编码方式
	 * @param dstCharset
	 *            目标字符串的编码方式（字节真正的编码方式）
	 * @return 转换后的字符串
	 */
	public static String charsetConvert(String str, String srcCharset, String dstCharset) {
		if (isEmpty(str))
			return "";
		try {
			return new String(str.getBytes(srcCharset), dstCharset);
		} catch (Exception e) {
//			log.error("charsetConvert:" + e);
		}
		return str;
	}

	/**
	 * 将字符串从ISO格式转换为UTF-8格式
	 * <p>
	 * <p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String iso2utf8(String str) {
		return charsetConvert(str, "ISO-8859-1", "UTF-8");
	}

	/**
	 * 将字符串从UTF-8格式转换为ISO格式
	 * <p>
	 * <p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String utf82iso(String str) {
		return charsetConvert(str, "UTF-8", "ISO-8859-1");
	}

	/**
	 * 将字符串从ISO格式转换为gb2312格式
	 * <p>
	 * <p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String iso2gbk(String str) {
		return charsetConvert(str, "ISO-8859-1", "GBK");
	}

	/**
	 * 将字符串从gb2312格式转换为ISO格式
	 * <p>
	 * <p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String gbk2iso(String str) {
		return charsetConvert(str, "GBK", "ISO-8859-1");
	}

	/**
	 * 将字符串从UTF-8格式转换为gbk格式
	 * <p>
	 * <p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String utf82gbk(String str) {
		return charsetConvert(str, "UTF-8", "GBK");
	}

	/**
	 * 将字符串从gb2312格式转换为UTF-8格式
	 * <p>
	 * <p>
	 * 
	 * @param str
	 * @return String
	 */
	public static String gbk2utf8(String str) {
		return charsetConvert(str, "GBK", "UTF-8");
	}

	/**
	 * 在字符串的左边添加多个字符pad，直到字符串的长度达到length为止，如果原始长度已经大于length，直接返回源串
	 * 
	 * @param str
	 *            源字符串
	 * @param pad
	 *            新加的站位符，通常是空格或0等参数
	 * @param length
	 *            目标长度
	 * @return 长度大于或等于length的新字符串
	 */
	public static String leftPadString(String str, char pad, int length) {
		if (str.length() >= length)
			return str;
		StringBuffer sb = new StringBuffer();
		while (sb.length() < length - str.length())
			sb.append(pad);
		sb.append(str);
		return sb.toString();
	}

	/**
	 * 在字符串的右边添加多个字符pad，直到字符串的长度达到length为止，如果原始长度已经大于length，直接返回源串
	 * 
	 * @param str
	 *            源字符串
	 * @param pad
	 *            新加的站位符，通常是空格或0等参数
	 * @param length
	 *            目标长度
	 * @return 长度大于或等于length的新字符串
	 */
	public static String rightPadString(String str, char pad, int length) {
		if (str.length() >= length)
			return str;
		StringBuffer sb = new StringBuffer(str);
		while (sb.length() < length)
			sb.append(pad);
		return sb.toString();
	}

	/**
	 * 为int类型的数字限定位数，不足则前边补零，
	 * 
	 * @param num
	 *            源数字
	 * @param strLen
	 *            限定位数
	 * @return String 结果数字的字符串形式 若strLen<=0 返回int对应的字符串
	 */
	public static String intPadString(int num, int strLen) {
		return leftPadString(String.valueOf(num), '0', strLen);
	}

	/**
	 * 从src的搜索出介于begin和end之间的字符串，
	 * 如substring("user=admin&passwd=123&code=888","passwd=","&")将返回"123"
	 * 
	 * @param src
	 * @param begin
	 * @param end
	 * @return
	 */
	public static String subString(String src, String begin, String end) {
		return subString(src, 0, begin, end);
	}

	/**
	 * 从src的offset位置开始搜索出介于begin和end之间的字符串，
	 * 如subString("user=admin&passwd=123&code=888",0,"passwd=","&")将返回"123"
	 * 
	 * @param src
	 * @param offset
	 * @param begin
	 * @param end
	 * @return
	 */
	public static String subString(String src, int offset, String begin, String end) {
		if (isEmpty(src) || offset >= src.length())
			return "";
		int b = offset;
		int e = src.length();
		if (!isEmpty(begin)) {
			b = src.indexOf(begin, offset);
			if (b < 0)
				return "";
			b += begin.length();
		}
		if (!isEmpty(end) && b < e) {
			e = src.indexOf(end, b);
			if (e < 0)
				return "";
		}
		return src.substring(b, e);
	}

	/**
	 * 获取一个随机数字符串，限定位数，不足则前边补零，
	 * 
	 * @param maxValue
	 *            可能的最大随机数
	 * @param strLen
	 *            限定位数
	 * @return String 结果数字的字符串形式 若strLen<=0 返回int对应的字符串
	 */
	public static String getRandomNumberString(int maxValue, int strLen) {
		return intPadString(rnd.nextInt(maxValue), strLen);
	}

	public static String getRandomNumberStringBase36(int strLen) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strLen; i++) {
			sb.append(digitsBase36.charAt(rnd.nextInt(36)));
		}
		return sb.toString();
	}

	/**
	 * 生成随机数字和字母
	 * 
	 * @param strLen
	 * @return
	 */
	public String getRandomNumberString(int strLen) {

		String val = "";
		Random random = new Random();

		// 参数length，表示生成几位随机数
		for (int i = 0; i < strLen; i++) {

			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			// 输出字母还是数字
			if ("char".equalsIgnoreCase(charOrNum)) {
				// 输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (random.nextInt(26) + temp);
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 获取一个随机数
	 * 
	 * @param maxValue
	 *            可能的最大随机数
	 * @return 不大于maxValue的整型数
	 */
	public static int getRandomNumber(int maxValue) {
		return rnd.nextInt(maxValue);
	}

	/**
	 * 将字符串按base64方式编码
	 * 
	 * @param s
	 *            源字符串
	 * @return 编码后的字符串
	 */
	@SuppressWarnings("restriction")
	public static String base64Encode(String s) {
		if (s == null)
			return null;
		return (new sun.misc.BASE64Encoder()).encode(s.getBytes());
	}

	/**
	 * 将 BASE64 编码的字符串 s 进行解码
	 * 
	 * @param s
	 *            base64编码后的字符串
	 * @return 解码后的字符串
	 */
	@SuppressWarnings("restriction")
	public static String base64Decode(String s) {
		if (s == null)
			return null;
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b, "utf-8");
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 计算字符串的md5的摘要信息
	 * 
	 * @param s
	 *            源字符串
	 * @return 32字节的16进制的字符串
	 */
	public static String md5(String s) {
		return md5(s, null);
	}

	/**
	 * 计算字符串的md5的摘要信息
	 * 
	 * @param data
	 *            源字符串
	 * @param key
	 *            salt字符串，
	 * @return 32字节的16进制的字符串
	 */
	public static String md5(String data, String key) {
		return doHash(data, key, "MD5");
	}

	/**
	 * 计算字符串的SHA1的摘要信息
	 * 
	 * @param s
	 *            源字符串
	 * @return 32字节的16进制的字符串
	 */
	public static String sha1(String s) {
		return md5(s, null);
	}

	/**
	 * 计算字符串的SHA1的摘要信息
	 * 
	 * @param data
	 *            源字符串
	 * @param key
	 *            salt字符串，
	 * @return 32字节的16进制的字符串
	 */
	public static String sha1(String data, String key) {
		return doHash(data, key, "SHA1");
	}

	/**
	 * 计算字符串的摘要信息
	 * 
	 * @param data
	 *            源字符串
	 * @param key
	 *            salt字符串，
	 * @param digestName
	 *            摘要算法名称，可以是MD5，SHA1等
	 * @return 32字节的16进制的字符串
	 */
	public static String doHash(String data, String key, String digestName) {
		String ret = "";
		if (isEmpty(data))
			return ret;

		try {
			MessageDigest mgd = MessageDigest.getInstance(digestName);
			mgd.update(data.getBytes());
			byte[] bytes = null;
			if (isEmpty(key)) {
				bytes = mgd.digest();
			}
			else {
				bytes = mgd.digest(key.getBytes());
			}
			mgd.reset();
			ret = toHex(bytes, 0, bytes.length);
		} catch (NoSuchAlgorithmException e) {
//			log.error("hash error:" + e);
		}
		return ret;
	}

	/**
	 * 对html中的特殊字符转换成相应的字符实体<br>
	 * 如 {@literal &} {@literal <} {@literal >} {@literal "} {@literal '} 空格 回车
	 * 换行等
	 * 
	 * <pre>
	 * <b>HTML字符实体(HTML Character Entities)释义</b>
	 * 一个字符实体(Character Entity)分成三部分：
	 * （1）第一部分是一个&符号，英文叫ampersand；
	 * （2）第二部分是实体(Entity)名字或者是#加上实体(Entity)编号；
	 * （3）第三部分是一个分号。
	 * 实例：要显示小于号，就可以写{@literal &lt;}或者{@literal &#60};
	 * 
	 * 用实体(Entity)名字的好处是比较好理解，一看lt，大概就猜出是less than的意思，
	 * 但是其劣势在于并不是所有的浏览器都支持最新的Entity名字。而实体(Entity)编号，各种浏览器都能处理。
	 * 
	 * <font color="red">注意：Entity是区分大小写的。</font>
	 * 
	 * 参考：
	 * http://www.fh888.com/showfile.html?articleid=AA60A6086B7F49E0B3C3610621B29A20&projectid=5&username=cary
	 * http://www.w3.org/TR/html401/charset.html#h-5.3
	 * </pre>
	 * 
	 * @param content
	 *            原始的html代码
	 * @return 转义后的html代码
	 */
	public static String fixHtml(String content) {
		if (content == null || content.trim().length() == 0)
			return content;
		StringBuilder builder = new StringBuilder(content.length());
		for (int i = 0; i < content.length(); ++i) {
			switch (content.charAt(i)) {
			case '<':
				builder.append("&lt;");
				break;
			case '>':
				builder.append("&gt;");
				break;
			case '"':
				builder.append("&quot;");
				break;
			case '\'':
				builder.append("&#39;");
				break;
			case '%':
				builder.append("&#37;");
				break;
			case ';':
				builder.append("&#59;");
				break;
			case '(':
				builder.append("&#40;");
				break;
			case ')':
				builder.append("&#41;");
				break;
			case '&':
				builder.append("&amp;");
				break;
			case '+':
				builder.append("&#43;");
				break;
			case '\r':// do nothing
				break;
			case '\n':
				builder.append("&lt;br&gt;");
				break;
			case '\t':
				builder.append("&#09;");
				break;
			case ' ':
				builder.append("&nbsp;");
				break;
			default:
				builder.append(content.charAt(i));
				break;
			}
		}
		return builder.toString();
	}

	/**
	 * 对html中的特殊字符转义进行还原，如&，<, >, ", ', 空格，回车，换行等
	 * 
	 * @param content
	 *            转义后的html代码
	 * @return 原始的html代码
	 */
	public static String unfixHtml(String content) {
		String ret = content;
		ret = ret.replaceAll("&lt;", "<").replaceAll("&#60;", "<");
		ret = ret.replaceAll("&gt;", ">").replaceAll("&#62;", ">");
		ret = ret.replaceAll("&quot;", "\"").replaceAll("&#34;", "\"");
		ret = ret.replaceAll("&#39;", "'");
		ret = ret.replaceAll("&#37;", "%");
		ret = ret.replaceAll("&#59;", ";");
		ret = ret.replaceAll("&#40;", "(");
		ret = ret.replaceAll("&#41;", ")");
		ret = ret.replaceAll("&nbsp;", " ");
		ret = ret.replaceAll("&amp;", "&");
		ret = ret.replaceAll("&#43;", "+");
		ret = ret.replaceAll("&#09;", "\t");
		ret = ret.replaceAll("<br>", "\n").replaceAll("&#10;", "\n");
		return ret;
	}

	/**
	 * 判断email地址是否符合规范
	 * 
	 * @param email
	 *            待检查的email地址
	 * @return 符合返回true，否则返回false
	 */
	public static boolean isEmail(String email) {
		if (isEmpty(email))
			return false;
		return Pattern.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", email);
	}

	/**
	 * 判断是否是手机号
	 * 
	 * @param s
	 *            待检查的电话号码串
	 * @return 符合返回true，否则返回false
	 */
	public static boolean isMobilePhone(String s) {
		if (isEmpty(s))
			return false;
		return Pattern.matches("^(13|15|18|14|17)\\d{9}$", s);
	}

	public static boolean isPhoneNumber(String s) {
		if (isEmpty(s))
			return false;
		return Pattern.matches("^[0-9\\-\\(\\)\\ ]+$", s);
	}

	public static boolean isDate(String s) {
		if (isEmpty(s))
			return false;
		return Pattern.matches("^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}$", s);
	}

	public static boolean isNumber(String s) {
		if (isEmpty(s))
			return false;
		return Pattern.matches("^[-]*[0-9\\.]+$", s);
	}

	public static boolean isOnlyLetter(String s) {
		if (isEmpty(s))
			return false;
		return Pattern.matches("^[a-zA-Z\\ \\']+$", s);
	}

	public static boolean isImageFile(String s) {
		if (isEmpty(s))
			return false;
		return Pattern.matches("(.*)\\.(jpeg|jpg|bmp|gif|png)$", s);
	}

	public static boolean isOnlyChinese(String s) {
		if (isEmpty(s))
			return false;
		return Pattern.matches("[^u4e00-u9fa5]+$", s);
	}

	public static boolean isUrl(String s) {
		if (isEmpty(s))
			return false;
		return Pattern.matches("^(https|http|ftp|rtsp|mms)?:\\/\\/[^s]*$", s);
	}

	/**
	 * 可识别的windows GUID字符串转换为16位byte数组
	 * 
	 * @param guid
	 *            GUID字符串
	 * @return 16位byte数组
	 */
	public static byte[] guid2bytes(String guid) {
		// windows guid:75B22630-668E-11CF-A6D9-00AA0062CE6C ==>
		// 3026B2758E66CF11A6D900AA0062CE6C
		StringBuffer sb = new StringBuffer(guid);
		sb.replace(0, 2, guid.substring(6, 8)).replace(2, 4, guid.substring(4, 6)).replace(4, 6, guid.substring(2, 4)).replace(6, 8, guid.substring(0, 2));
		sb.replace(9, 11, guid.substring(11, 13)).replace(11, 13, guid.substring(9, 11));
		sb.replace(14, 16, guid.substring(16, 18)).replace(16, 18, guid.substring(14, 16));
		return StringUtil.hex2Bytes(sb.toString().replace("-", ""));
	}

	/**
	 * 转换16位byte数组为可识别的windows GUID字符串
	 * 
	 * @param buf
	 *            16位byte数组
	 * @return GUID字符串
	 */
	public static String bytes2Guid(byte[] buf) {
		return bytes2Guid(buf, 0);
	}

	/**
	 * 转换16位byte数组为可识别的windows GUID字符串
	 * 
	 * @param buf
	 *            byte数组
	 * @param offset
	 *            数组的开始位置
	 * @return GUID字符串
	 */
	public static String bytes2Guid(byte[] buf, int offset) {
		// 3026B2758E66CF11A6D900AA0062CE6C ==>
		// 75B22630-668E-11CF-A6D9-00AA0062CE6C
		final int guidSize = 16;
		if (buf == null || offset < 0 || (offset + guidSize > buf.length))
			return "";

		String hex = StringUtil.toHex(buf, offset, guidSize);
		StringBuffer sb = new StringBuffer();
		sb.append(hex.subSequence(6, 8)).append(hex.subSequence(4, 6)).append(hex.subSequence(2, 4)).append(hex.subSequence(0, 2));
		sb.append("-").append(hex.subSequence(10, 12)).append(hex.subSequence(8, 10));
		sb.append("-").append(hex.subSequence(14, 16)).append(hex.subSequence(12, 14));
		sb.append("-").append(hex.subSequence(16, 20));
		sb.append("-").append(hex.substring(20));
		return sb.toString().toUpperCase();
	}

	/**
	 * 删除字符串的第一个和最后一个字符
	 * 
	 * @param str
	 * @return 去除首字符和最后一个字符后的字符串
	 */
	public static String truncateFirstEnd(String str) {
		if (isEmpty(str))
			return str;

		String tmp = str.substring(1);
		return tmp.substring(0, tmp.length() - 1);
	}

	/**
	 * 正则表达式 获取相关结果
	 * 
	 * @param src
	 * @param reg
	 * @return
	 */
	public static Matcher getMatcherGroup(String src, String reg) {
		if (StringUtil.isEmpty(src))
			return null;
		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		return p.matcher(src.toLowerCase());
	}

	/**
	 * 对字符串进行解码或反转义的。
	 * 
	 * @param src
	 * @return
	 */
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	public static String stringValue(String src, String def) {
		return StringUtil.isEmpty(src) ? def : src;
	}

	public static Long string2Long(Object obj) {
		if (obj == null || false == NumberUtils.isNumber(obj + ""))
			return 0L;
		return Long.valueOf(obj + "");
	}

	// 把如“398万5千”之类的数据转换成为“3985000”
	public static int cnNum2Int(String s) {
		String numStr = "";
		int result = 0;
		int util = 1;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c > 47 && c < 58) {
				numStr += String.valueOf(c);
				continue;
			}
			switch (c) {
			case '十':
				util = 10;
				break;
			case '百':
				util = 100;
				break;
			case '千':
				util = 1000;
				break;
			case '万':
				util = 10000;
				break;
			case '亿':
				util = 100000000;
				break;
			}
			if (!numStr.equals("")) {
				int temp = Integer.parseInt(numStr);
				result += temp * util;
				util = 1;
				temp = 0;
				numStr = "";
				continue;
			}
			result = result * util;
			util = 1;
		}
		if (!numStr.equals(""))
			result += Integer.parseInt(numStr) * util;

		return result;
	}

	/**
	 * 优惠券前缀5位(13177),"13"表示13年,177表示一年中的第177天
	 * 
	 * @return
	 */
	public static String getSnPrefix() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String year = sdf.format(new Date());
		String last2year = year.substring(2, 4);
		String dayOfYear = "";
		int day = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
		if (day < 10) {
			dayOfYear = "00" + day;
		} else if (day < 100) {
			dayOfYear = "0" + day;
		} else {
			dayOfYear = "" + day;
		}
		return last2year + dayOfYear;
	}

	public static String randNum(int n) {
		char[] arrChar = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuilder num = new StringBuilder();
		Random rnd = new Random();
		for (int i = 0; i < n; i++) {
			num.append(arrChar[rnd.nextInt(9)]);
		}
		return num.toString();
	}

	public static int convertStrToInt(String str) {
		int n = -1;
		if (null != str && !"".equals(str.trim())) {
			try {
				n = parseInt(str.trim());
			} catch (Exception e) {
			}
		}
		return n;
	}

	public static int parseInt(String idStr) throws Exception {
		if (StringUtil.isNullOrBlank(idStr)) {
			return -1;
		}
		try {
			idStr = idStr.trim();
			return Integer.parseInt(idStr);
		} catch (Exception e) {
			throw new Exception("抱歉，你输入的id值是非数字型！");
		}
	}

	public static boolean isNullOrBlank(String str) {
		if (str == null) {
			return true;
		}
		str = str.trim();
		if (!str.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 字符串不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isNullOrBlank(str);
	}

	/**
	 * 获取指定长度的随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "BCLMHcdefgDFGhijklEm45IJK6S1WZ7abvwXYxyz02n8opq39ANrstTUVuOPQR";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 以当前时间毫秒数为准获取唯一字符串
	 * 
	 * @return
	 */
	public static String getUniqueString() {
		String key = new Date().getTime() + "";
		String base = "lEm45IJK6S1WZ7abvwXYxyz02";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < key.length(); i++) {
			try {
				Thread.sleep(1l);
				int index = Integer.parseInt(key.charAt(i) + "");
				if (index == 0) {
					sb.append(getRandomString(2));
				}
				sb.append(base.charAt(index));

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sb.toString();
	}

	/**
	 * 以当前时间毫秒数为准获取唯一字符串(数字)
	 * 
	 * @return
	 */
	public static String getUniqueNumber() {
		Date date = new Date();
		String key = date.getTime() + "";
		// System.err.println(key);
		Map<String, String> baseKey = new HashMap<String, String>();
		baseKey.put("0", "3");
		baseKey.put("1", "1");
		baseKey.put("2", "7");
		baseKey.put("3", "5");
		baseKey.put("4", "8");
		baseKey.put("5", "9");
		baseKey.put("6", "4");
		baseKey.put("7", "2");
		baseKey.put("8", "6");
		baseKey.put("9", "0");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < key.length(); i++) {
			try {
				Thread.sleep(1l);
				String index = key.charAt(i) + "";
				sb = sb.append(baseKey.get(index));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return sb.toString();
	}

	/**
	 * 数组去重去空，有空需要抽取到工具类
	 * 
	 * @param array
	 * @return
	 */
	public static String[] trimArray(String[] array) {

		Set<String> set = new TreeSet<String>();

		for (String i : array) {
			if (!StringUtil.isEmpty(i))
				set.add(i);
		}

		String[] des = new String[set.size()];
		int j = 0;
		for (String i : set) {
			des[j++] = i;
		}

		return des;

	}

	/**
	 * 是否为金额
	 * 
	 * @param amount
	 * @return
	 */
	public static boolean isMoney(String amount) {
		if (StringUtil.isNullOrBlank(amount)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
		Matcher match = pattern.matcher(amount);

		return match.matches();
	}

	public static String escapeHtml(String source) {
		if (source == null) {
            return "";
        }
        source = source.replaceAll("<", "&lt;");
        source = source.replaceAll(">", "&gt;");
        return source;
	}

	/**
	 * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HelloWorld->HELLO_WORLD
	 * 
	 * @param name
	 *            转换前的驼峰式命名的字符串
	 * @param caps      是否大写：否时： HelloWorld->hello_world
	 * @return 转换后下划线大写方式命名的字符串
	 */
	public static String underscoreName(String name, boolean caps) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			// 将第一个字符处理成大写
			result.append(name.substring(0, 1).toUpperCase());
			// 循环处理其余字符
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				// 在大写字母前添加下划线
				if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
					result.append("_");
				}
				// 其他字符直接转成大写
				result.append(s.toUpperCase());
			}
		}
		String word = result.toString();
		if (caps) {
			return word;
		} else {
			return word.toLowerCase();
		}
	}

	/**
	 * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
	 * 例如：HELLO_WORLD->HelloWorld
	 * 
	 * @param name
	 *            转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camelName(String name) {
		StringBuilder result = new StringBuilder();
		// 快速检查
		if (name == null || name.isEmpty()) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母小写
			return name.substring(0, 1).toLowerCase() + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String camels[] = name.split("_");
		for (String camel : camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			// 处理真正的驼峰片段
			if (result.length() == 0) {
				// 第一个驼峰片段，全部字母都小写
				result.append(camel.toLowerCase());
			} else {
				// 其他的驼峰片段，首字母大写
				result.append(camel.substring(0, 1).toUpperCase());
				result.append(camel.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}
	// 过滤特殊字符串
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	/**
	 * 获取一个UUID
	 * @return
     */
	public static String getUUID(){
		String s = UUID.randomUUID().toString();
		//去掉“-”符号
		return s.replace("-", "");
	}

	
	/**
	 * dt用MD5加密
	 * @param str
	 * @return
     */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		//16位加密，从第9位到25位
		return md5StrBuff.substring(8, 24).toString().toUpperCase();
	}

	/**
	 * 根据指定位数，格式化数字
	 *
	 * @return
	 */
	public static String formatNumberDigit(Integer number, Integer digit) {
		if(number==null){
			return "";
		}
		StringBuffer resultSb = new StringBuffer(number.toString());
		while(resultSb.length() < digit) {
			resultSb = resultSb.insert(0, "0");
		}
		return resultSb.substring(0, digit);
	}

	/**
	 * 将 1 转换为 D000001 或 P000001
	 * @param userBaseId
	 * @param prefix 'D' 或 'P'
	 * @return
	 */
	public static String toHuatuoNo(Integer userBaseId, String prefix){
		if(userBaseId == null || userBaseId == 0
				|| prefix == null || prefix.trim().length() == 0){
			throw new RuntimeException("参数错误！");
		}
		return prefix + leftPadString(userBaseId.toString(), '0', 6);
	}
	
	/**
	 * D000001 或 P000001 转化为 1
	 * */
	public static Integer fromHuatuoNo(String huatuoNo){
		
		// 1. 校验
		if(huatuoNo == null || huatuoNo.trim().length() == 0){
			throw new RuntimeException("参数错误！");
		}
		// 校验是否符合某种格式
		if(!huatuoNo.matches("[PD]\\d{6}")){
			throw new RuntimeException("华佗号格式不正确！");
		}
		
		Matcher m = Pattern.compile("[PD]0*+([\\d]+)").matcher(huatuoNo);
		
		// 2. 数据处理
		// 3. 数据响应
		if(m.find()){
			return Integer.parseInt(m.group(1));
		}else{
			return null;
		}
	}

	/**
	 * 获取一个指定长度的随机数字
	 * @param length
	 * @return
     */
	public static String getRandomCode(Integer length){
		StringBuffer code = new StringBuffer();
		Random random = new Random();
		for(int i=0;i<length;i++){ // 0-9 的随机数
			code.append(random.nextInt(10)); // 0-9
		}
		return code.toString();
	}
	
	/**
	 * 生成外部交易号码
	 * 
	 * @param id
	 * 			唯一性ID
	 * @return
	 */
	public static String generateOutTradeNo(Integer id) {
		StringBuffer outNo = new StringBuffer();
		outNo.append(String.format("%06d", id));
		outNo.append(DateUtil.convertDateToStr(Calendar.getInstance().getTime(), "yyyyMMddHHmmss"));
		outNo.append(StringUtil.getRandomCode(5));
		return outNo.toString();
	}
		
	 /**
	  * 将流转换为字符串
	  * 
	  * @param is
	  * 		输入流
	  * @return
	  * @throws IOException
	  */
	 public static String inputStreamToString(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }
	 
	 /**
	  * 将xml的字符串，转换为已知的对象
	  * 		（未知字段直接忽略）
	  * 
	  * @param xml
	  * 		xml字符串
	  * @param tClass
	  * 		要转换的目标类
	  * @return
	  */
	 public static Object getObjectFromXML(String xml, Class tClass) {
        //将从API返回的XML数据映射到Java对象
        XStream xStreamForResponseData = new XStream();
        xStreamForResponseData.alias("xml", tClass);
        xStreamForResponseData.ignoreUnknownElements();//暂时忽略掉一些新增的字段
        return xStreamForResponseData.fromXML(xml);
	 }
	 
	 /**
	  * 获取字符数组以 指定分隔符  分隔的字符串
	  * 
	  * @param arr
	  * 		字符数组
	  * @param separator
	  * 		分隔符
	  * @return
	  */
	 public static String convertArrToStr(String[] arr, String separator) {
		 if(arr!=null && arr.length>0) {
			 StringBuffer result = new StringBuffer();
			 for (int i=0; i<arr.length; i++) {
				result.append(arr[i]).append(separator);
			}
			return result.substring(0, result.lastIndexOf(separator));
		 }
		 return "";
	 }
	 
	 /**
	  * 使用默认分隔符获取 分隔后的字符串
	  * 
	  * @param arr
	  * 		字符数组
	  * @return
	  */
	 public static String convertArrToStr(String[] arr) {
		 return convertArrToStr(arr, DEFAULT_SEPARATOR);
	 }
	 
	 public static String[] convertStrToArr(String str) {
		 return convertStrToArr(str, DEFAULT_SEPARATOR);
	 }
	 
	 public static String[] convertStrToArr(String str, String separator) {
		 if(!isEmpty(str)) {
			 return str.split(separator);
		 }
		 return null;
	 }

	public static String captureName(String name) {
		//     name = name.substring(0, 1).toUpperCase() + name.substring(1);
//        return  name;
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);

	}


	 

	public static void main(String[] args) {
		
		System.out.println(convertArrToStr(new String[]{"dddd", "ddfdf", "llll", "rrrr"}, ":::"));
		
		
		
//		String a = "</script></script>";
//		a = escapeHtml(a);
//		System.out.println(a);
		String randomCode = getRandomCode(4);
		System.out.println(randomCode);
	}

}
