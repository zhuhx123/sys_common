package com.ivymei.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 操作Excel表格的功能类
 * 
 * 
 */
public class ExcelReader {
	private static Logger log = LoggerFactory.getLogger(ExcelReader.class);
	private static Workbook book;
	private static Sheet sheet;
	private static Row row;

	public static String[] readExcelTitle(String execelFile) {
		File f = new File(execelFile);
		FileInputStream is;
		try {
			is = new FileInputStream(f);
			book = WorkbookFactory.create(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		sheet = book.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			title[i] = getStringCellValue(row.getCell((short) i));
		}
		return title;
	}

	/**
	 * 读取Excel数据内容
	 * 
	 * @param InputStream
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public static Map<Integer, String> readExcelContent(FileInputStream is) {
		Map<Integer, String> content = new HashMap<Integer, String>();
		String str = "";
		// File f = new File(execelFile);
		// FileInputStream is;
		try {
			// is = new FileInputStream(f);
			book = WorkbookFactory.create(is);
		} catch (Exception e) {
			log.error("[Excel读取]读取错误或未找到文件", e);
		}
		sheet = book.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			while (j < colNum) {
				// 每个单元格的数据内容用"-"分割开，以后需要时用String类的split()方法还原数据
				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				str += getStringCellValue(row.getCell((short) j)).trim() + "-";
				j++;
			}
			content.put(i, str);
			str = "";
		}
		return content;
	}
	
	public static List<Map<Integer, String>> readExcelData(InputStream is) {
		log.debug("开始读取文件...");
		long start = System.currentTimeMillis();
		List<Map<Integer, String>> dataList = new ArrayList<Map<Integer, String>>();
		Workbook book;
		Sheet sheet;
		Row row;
		try {
			book = WorkbookFactory.create(is);
		} catch (Exception e) {
			log.error("[Excel读取]读取错误或未找到文件", e);
			return dataList;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		// 读取第一个Sheet
		sheet = book.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getPhysicalNumberOfRows();
		// int rowNum = rowSize;
		// 读取第一行，以第一行当列数
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		// int colNum = colSize;
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 0; i < rowNum; i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			int j = 0;
			// 读取每行数据
			SortedMap<Integer, String> cellData = new TreeMap<Integer, String>();
			while (j < colNum) {
				cellData.put(j, getStringCellValue(row.getCell((short) j)).trim());
				j++;
			}
			dataList.add(cellData);
		}
		long end = System.currentTimeMillis();
		log.debug("文件读取完毕，总耗时:" + (end - start) + "ms...");

		return dataList;
	}

	// 返回每行的数据，以Map显示，整个数据以List显示
	public static List<Map<Integer, String>> readExcelData(String path) {
		File file = new File(path);
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(file);
			return readExcelData(fileInputStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private static String getStringCellValue(Cell cell) {
		String strCell = "";
		if (cell == null) {
			return strCell;
		}
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
			strCell = dataFormatter.formatCellValue(cell);
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 去掉小数点后的0和小数点
	 * 
	 * @param s
	 * @return
	 */
	public static String subZeroAndDot(String s) {
		if (s.indexOf(".") > 0) {
			s = s.replaceAll("0+?$", "");// 去掉多余的0
			s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return s;
	}

	public static void main(String[] args) {

		List<Map<Integer, String>> s = ExcelReader.readExcelData("C:\\Users\\jht\\Desktop\\采购模版-中药饮片.xlsx");
		for (Map<Integer, String> map : s) {
			System.out.println(map.toString());
		}
	}
}
