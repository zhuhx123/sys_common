package com.ivymei.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelWriter<T> {

	public void exportExcel(
			String title,
			String[] headerRealNames,
			String[] headers,
			Collection<T> dataset,
			OutputStream out,
			String datePattern) {

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);

		// // 设置表格默认列宽度为15个字节
		// sheet.setDefaultColumnWidth((short) 15);

		// // 生成一个样式
		// HSSFCellStyle style = workbook.createCellStyle();
		// style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		// style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		// style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		// style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		// style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// // 生成一个字体
		// HSSFFont font = workbook.createFont();
		// font.setColor(HSSFColor.VIOLET.index);
		// font.setFontHeightInPoints((short) 12);
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// // 把字体应用到当前的样式
		// style.setFont(font);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headerRealNames[i]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (int i = 0; i < headers.length; i++) {
				HSSFCell cell = row.createCell(i);
				// cell.setCellStyle(style2);
//				Field field = fields[i];
				String fieldName = headers[i];
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class<? extends Object> tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					if (value instanceof Boolean) {
						boolean bValue = (Boolean) value;
						textValue = "是";
						if (!bValue) {
							textValue = "否";
						}
					} else if (value instanceof Date) {
						Date date = (Date) value;
						if (StringUtil.isNullOrBlank(datePattern)) {
							datePattern = DateUtil.DEFAULT_DATE_TIME_FORMAT;
						}
						SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) i, index, (short) i, index);
						anchor.setAnchorType(3);
						patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					} else {
						// 其它数据类型都当作字符串简单处理
						textValue = value.toString();
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class Person {
		private int id;
		private String name;
		private byte[] image;

		public int getId() {
			return id;
		}

		public void setId(
				int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(
				String name) {
			this.name = name;
		}

		public byte[] getImage() {
			return image;
		}

		public void setImage(
				byte[] image) {
			this.image = image;
		}
	}

	public static void main(
			String[] args) throws IOException {

		File file = new File("D:\\image.png");
		long fileSize = file.length();
		byte[] bytes = new byte[(int) fileSize];
		FileInputStream in = new FileInputStream(file);
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead = in.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		Person p = new Person();
		p.setId(1);
		p.setName("show");
		p.setImage(bytes);

		Person p2 = new Person();
		p2.setId(2);
		p2.setName("show.so");
		p2.setImage(bytes);

		List<Person> datas = new ArrayList<Person>();
		datas.add(p);
		datas.add(p2);

		OutputStream out = new FileOutputStream("D://a.xls");

		ExcelWriter<Person> w = new ExcelWriter<Person>();

		w.exportExcel("sheet1", new String[] { "id", "xxx", "fc" }, new String[] { "id", "name", "image" },datas, out, "");
	}
}
