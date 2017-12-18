package com.ivymei.framework.util;

import java.util.regex.Pattern;

/**
 * html工具 
 *
 */
public class HtmlUtil {
	/**
	 * 把字符串中含有的Html特殊字符转化为常规字符，如果Http请求的参数值是字符串， 则需调用该方法进行字符转码处理。
	 * @param str  要转换的字符串
	 * @return 转换后的字符串
	 */
	public static String encodeHtml(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		// 把要处理的字符串转换为字符数组
		char content[] = new char[str.length()];
		str.getChars(0, str.length(), content, 0);
		// 循环处理字符数组
		StringBuffer result = new StringBuffer(content.length + 50);
		char preChar = ' ';
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			case ' ':
				result.append("&nbsp;");
				break;
			case '\r':
				result.append("<br>");
				break;
			case '\n':
				//检查前一个字符，是否'\r'，对'\r\n'我们只转换成一个<br>
				if(preChar != '\r'){
					result.append("<br>");
				}
				break;
			default:
				result.append(content[i]);
			}
			preChar = content[i];
		}
		return (result.toString());
	}

	/**
	 * 防止xss攻击，将特殊字符转化成全角
	 * @param s
	 * @return
	 */
	public static String xssEncode(String s){
		if (s == null){
			return null;
		}
		if (s.length() == 0){
			return "";
		}
		StringBuilder sb = new StringBuilder(s.length() + 50);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
				case '>':
					sb.append('＞');//全角大于号
					break;
				case '<':
					sb.append('＜');//全角小于号
					break;
				case '\'':
					sb.append('＇');//全角单引号
					break;
				case '\"':
					sb.append('＂');//全角双引号
					break;
				case '&':
					sb.append('＆');//全角&
					break;
				case '\\':
					sb.append('＼');//全角斜线
					break;
				case '#':
					sb.append('＃');//全角井号
					break;
				case ':':
					sb.append('：');//全角冒号
					break;
				case '(':
					sb.append("（");//全角左括号
					break;
				case ')':
					sb.append("）");//全角右括号
					break;
				default:
					sb.append(c);
					break;
			}
		}
		return sb.toString();
	}

	/**
	 * 把字符串中含有的Html特殊字符转化为常规字符，如果Http请求的参数值是字符串， 则需调用该方法进行字符转码处理。
	 * 注意：该方法与encodeHtml的不同在于默认会对字符串按照论坛发帖的需求进行格式化，所以该方法主要用于类似论坛发帖的需求
	 * @param str  要转换的字符串
	 * @return 转换后的字符串
	 */
	@Deprecated
	public static String encodeHtmlTag(String str) {
		if (str == null || str.equals("")) {
			return "";
		}
		str=formatContent(str);
		return encodeHtml(str);
	}
	
	
	/**
	 * 过滤font标签
	 * @param str
	 * @return
	 */
	public static String filterFontTag(String str){
		if(str == null || str.length() == 0){
			return str;
		}
		return str.replaceAll("<font.*?</font>", "");
	}
	/**
	 * 过滤href标签
	 * @param str
	 * @return
	 */
	public static String filterHrefTag(String str){
		if(str == null || str.length() == 0){
			return str;
		}
		return str.replaceAll("<a.*?</a>", "");
	}

	
	
	/**
	 * 过滤javaScript标签
	 * @param str
	 * @return
	 */
	public static String filterJavaScriptTag(String str){
        if( str == null || str.length() == 0 || (str.toLowerCase().indexOf("script")<0 && str.toLowerCase().indexOf("javascript")<0)) {
            return str;
        }
        return Pattern.compile("\\<(\\s*)(script)(\\s.*?)?\\>((\\s|.)*?)\\<\\/(\\s*)(script)(\\s.*?)?\\>",Pattern.CASE_INSENSITIVE).matcher(str).replaceAll("");
	}
	/**
	 * 过滤换行符标签,在内容修改和回复修改时用到
	 * @param str
	 * @return
	 */
	public static String filterRowTag(String str){
		if(str == null || str.length() == 0){
			return str;
		}
		return str.replaceAll("\r\n", "<br>").replaceAll("\n", "<br>").replaceAll("\r", "<br>");
	}
	/**
	 * 格式帖子内容信息
	 * @param str
	 * @return
	 */
	public static String formatContent(String str){
		//得到数组
		String[] contents=str.split("\r");
		if(contents==null){
			return "　　"+str.trim().replace("　　", "");
		}
		StringBuffer content=new StringBuffer("");
		for(int i=0;i<contents.length;i++){
			if(i == 0){
				// 内容最前只插入两个空格
				content.append("　　"+contents[i].trim().replace("　　", ""));
			}else{
				content.append("\r　　"+contents[i].trim().replace("　　", ""));
			}
		}
		return content.toString();
	}
	
	/**
	 * 判断是否bmp文件
	 * @param fileName
	 * @return
	 */
	public static boolean isBmp(String  fileName) {
		if (fileName.toLowerCase().endsWith(".bmp")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否gif文件
	 * @param fileName
	 * @return
	 */
	public static boolean isGif(String  fileName) {
		if (fileName.toLowerCase().endsWith(".gif")) {
			return true;
		}
		return false;
	}
	/**
	 * 判断是否jpg文件
	 * @param fileName
	 * @return
	 */
	public static boolean isJpg(String  fileName) {
		if (fileName.toLowerCase().endsWith(".jpg")) {
			return true;
		}
		return false;
	}
	/**
	 * 判断是否png文件
	 * @param fileName
	 * @return
	 */
	public static boolean isPng(String  fileName) {
		if (fileName.toLowerCase().endsWith(".png")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否Jpeg文件
	 * @param fileName
	 * @return
	 */
	public static boolean isJpeg(String fileName){
		if (fileName.toLowerCase().endsWith(".jpeg")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否有效的图片url.
	 * @param url
	 * @return
	 */
	public static boolean isPicUrl(String url){
		if(StringUtil.isNullOrBlank(url)){
			return false;
		}
		//当后缀为.png,.bmp,.gif,.jpg是返回true。
		return isPng(url)||isBmp(url)||isGif(url)||isJpg(url)||isJpeg(url);
	}
	
	/**
	 * 反向转换html字符串，一般用在textarea中正确显示编辑内容
	 * @param str
	 * @return 转换后的字符串
	 */
	public static String decodeHtml(String str) {	
		if (str == null || str.equals("")) {
			return str;
		}

		//str = str.replaceAll("''", "'");
		str = str.replaceAll("<br>", "\r");
		str = str.replaceAll("&nbsp;", " ");
		str = str.replaceAll("&quot;", "\"");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&amp;", "&");
		return str;		
		
	}

	/**
	 * 反向转换html字符串，一般用在textarea中正确显示编辑内容。
	 * 
	 * @param str
	 * @return 转换后的字符串
	 * @deprecated 用decodeHtml代替，两方法实现一样，命名不一样
	 */
	@Deprecated
	public static String unEncodeHtmlTag(String str) {
		return decodeHtml(str);
	}
	/**
	 * 判断url地址是否包含:register.tianya.cn,loginsubmit.asp,loginout.asp,
	 * 主要是针对图片输入.
	 * @param url
	 * @return 如果包含返回true,否则false;
	 */
	public static boolean isSpecUrl(String url){
		if(url.indexOf("register.tianya.cn")>0){
			return true;
		}
		if(url.indexOf("loginsubmit.asp")>0){
			return true;
		}
		if(url.indexOf("loginout.asp")>0){
			return true;
		}
		return false;
	}

}
