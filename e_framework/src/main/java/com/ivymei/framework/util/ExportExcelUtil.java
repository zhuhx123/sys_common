package com.ivymei.framework.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ivymei.framework.enums.export.CouponDetailExportEnum;
import com.ivymei.framework.enums.export.ExportEnum;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Nathy on 2017/9/28.
 */
public class ExportExcelUtil {
    public final static String[] time = {"addTime", "recentArriveTime", "userStartDay", "userBirthday", "userInviteTime", "saleTime", "projectBuyTime"};


    public static File export(JSONObject data, Map<String, Object> major, Map<String, Object> extend, String fileName, String code, String savePath, Boolean pageCount) throws Exception {
   // public synchronized static File export(ExportVO vo) throws Exception {
//        JSONObject data=vo.getData();
//        Map<String, Object> major=vo.getMajor();
//        Map<String, Object> extend=vo.getExtend();
//        String fileName=vo.getFileName();
//        String code=vo.getCode(   );
//        String savePath=vo.getSavePath();
//        Boolean pageCount=vo.getTemp();
        File file = null;
        file = new File(savePath);
        if(!file.exists()){
            file.mkdirs();
        }
        if (pageCount) {
            file = new File(savePath + fileName + ".xls");
        } else {
            file = new File(savePath + code + ".xls");
        }
        Integer lastRow = 0;//最后一行
        // 声明一个工作薄
        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;
        HSSFRow row = null;   //行
        FileInputStream fs = null;
        POIFSFileSystem ps = null;
        if (!file.exists()) {
            file.createNewFile();
            //files.add(file);
            // 声明一个工作薄
            workbook = new HSSFWorkbook();
            // 生成一个表格
            sheet = workbook.createSheet(fileName.substring(0, fileName.indexOf("_") > 1 ? fileName.indexOf("_") : fileName.length()));
            // 设置表格默认列宽度为20个字节
            sheet.setDefaultColumnWidth((short) 20);

            // 产生表格标题行
            row = sheet.createRow(0);
            int k = 0;
            for (String s : major.keySet()) {
                HSSFCell cell = row.createCell(k);
                //  cell.setCellStyle(style);
                Object obj = major.get(s);
                if (obj instanceof LinkedHashMap) {
                    //Map<String,Object> array=(Map)obj;
                    for (String o : extend.keySet()) {
                        cell = row.createCell(k);
                        //  cell.setCellStyle(style);
                        String title = extend.get(o).toString();
                        HSSFRichTextString text = new HSSFRichTextString(title);
                        cell.setCellValue(text);
                        k++;
                    }
                } else {
                    HSSFRichTextString text = new HSSFRichTextString(obj.toString());
                    cell.setCellValue(text);
                    k++;
                }
            }
        } else {
            workbook = getHSSFWorkbook(fs, ps, workbook, file);
            sheet = workbook.getSheetAt(0);  //获取到工作表，因为一个excel可能有多个工作表
            lastRow = sheet.getLastRowNum();
        }
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();

        if (data != null) {
            JSONArray list = data.getJSONArray("data");
            //数据
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    JSONObject obj = list.getJSONObject(i);
                    row = sheet.createRow(lastRow + 1 + i);
                    int j = 0;
                    String value = null;
                    for (Object s : major.keySet()) {
                        HSSFCell cell = row.createCell(j);
                        if (s.toString().contains(".")) {
                            String key1 = s.toString().substring(0, s.toString().indexOf("."));
                            String key2 = s.toString().substring(s.toString().indexOf(".") + 1, s.toString().length());
                            Map map = (Map) obj.get(key1);
                            Object str = map.get(key2);
                            value = checkData(str, key2, fileName);
                            HSSFRichTextString text = new HSSFRichTextString(value);
                            cell.setCellValue(text);
                            j++;
                            continue;
                        }
                        Object object = obj.get(s);
                        if (object instanceof JSONObject) {
                            Map extendMap = (Map) object;
                            for (Object o : extend.keySet()) {
                                cell = row.createCell(j);
                                Object str = extendMap.get(o);
                                value = checkData(str, o, fileName);
                                HSSFRichTextString text = new HSSFRichTextString(value);
                                cell.setCellValue(text);
                                j++;
                            }
                        } else {
                            value = checkData(object, s, fileName);
                            //     cell.setCellStyle(style);
                            HSSFRichTextString text = new HSSFRichTextString(value);
                            cell.setCellValue(text);
                            j++;
                        }
                    }
                }
            }
        }
        FileOutputStream out = null;
        if (pageCount) {
            out = new FileOutputStream(savePath + fileName + ".xls");
        } else {
            out = new FileOutputStream(savePath + code + ".xls");
        }
        workbook.write(out);
        out.close();


        return file;
        //压缩文件
        /*
        List<File> srcfile=new ArrayList<>();
        srcfile.add(new File("E://"+fileName+".xls"));
        zipFiles(srcfile,new File("E://"+fileName+".zip"));
        return srcfile
        */
    }


    public static HSSFWorkbook getHSSFWorkbook(FileInputStream fs, POIFSFileSystem ps, HSSFWorkbook workbook, File file) throws Exception {
        if (fs == null) {
            fs = new FileInputStream(file);
        }
        if (ps == null) {
            ps = new POIFSFileSystem(fs);  //使用POI提供的方法得到excel的信息
        }
        if (workbook == null) {
            workbook = new HSSFWorkbook(ps);
        }
        return workbook;
    }


    /**
     * 加入合计行
     *
     * @param file
     * @param data
     * @throws Exception
     */
    public static void addSummation(File file, JSONObject data, Map<String, Object> summation) throws Exception {
        FileInputStream fs = new FileInputStream(file);
        POIFSFileSystem ps = new POIFSFileSystem(fs);  //使用POI提供的方法得到excel的信息
        HSSFWorkbook workbook = new HSSFWorkbook(ps);
        HSSFSheet sheet = workbook.getSheetAt(0);
       // HSSFSheet sheet1 = workbook.getSheetAt(0);//获取到工作表，因为一个excel可能有多个工作表
        Integer lastRow = sheet.getLastRowNum();
        HSSFRow row = sheet.createRow(lastRow + 1);
        HSSFRow FirstColumn = sheet.getRow(0);
        HSSFRichTextString text = new HSSFRichTextString("合计");
        row.createCell(0).setCellValue(text);
        for (int i = 1; i < FirstColumn.getPhysicalNumberOfCells(); i++) {
            if (summation.containsKey(FirstColumn.getCell(i).getStringCellValue())) {
                HSSFCell cell = row.createCell(i);
                Object obj=data.get(summation.get(FirstColumn.getCell(i).getStringCellValue()));
                HSSFRichTextString text1=new HSSFRichTextString("");
                if(obj!=null&&!obj.toString().contains("%")){
                     text1 = new HSSFRichTextString(new BigDecimal(obj + "").setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                }else if(obj!=null){
                    text1=new HSSFRichTextString(obj+"");
                }
                cell.setCellValue(text1);
            }
        }
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
    }


    /**
     * 压缩文件
     *
     * @param srcfile File[] 需要压缩的文件列表
     * @param zipfile File 压缩后的文件
     */
    public static void zipFiles(List<File> srcfile, File zipfile) throws Exception {
        byte[] buf = new byte[1024];
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
        for (int i = 0; i < srcfile.size(); i++) {
            File file = srcfile.get(i);
            FileInputStream in = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(file.getName()));
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.closeEntry();
            in.close();
            file.delete();//清除临时文档
        }
        out.close();
    }

    /**
     * 压缩文件
     *
     * @param
     * @param
     */
    public static void zipFile(File file, ZipOutputStream out, byte[] buf) throws Exception {
        FileInputStream in = new FileInputStream(file);
        out.putNextEntry(new ZipEntry(file.getName()));
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.closeEntry();
        in.close();
        file.delete();//清除临时文档
    }

    /**
     * 数据格式处理
     *
     * @return
     */
    public static String checkData(Object value, Object key, String fileName) {
        if (value == null) {
            value = "";
        }
        String temp = "";
        if (Arrays.asList(time).contains(key.toString()) && value != null && !StringUtil.isNullOrBlank(value.toString())) {
            temp = DateUtil.convertUnixTimeStampToDateStr(Integer.valueOf(value.toString()), DateUtil.DEFAULT_SHORT_DATE_FORMAT);
        } else if (value instanceof JSONArray) {
            temp = value.toString().replace("[", "").replace("]", "").replace("\"", "").replace("\"", "");
        } else if (key.equals("consumeType")) {
            if (fileName.equals("顾客代金券赠送划扣明细表")) {
                temp = CouponDetailExportEnum.getByKey(value + "").getValue();
            } else {
                temp = ExportEnum.getByKey(value + "").getValue();
            }
        } else {
            temp = value + "";
        }
        if (!StringUtil.isNullOrBlank(temp) && StringUtil.isNumber(temp + "") && !StringUtil.judgeTwoDecimal(temp + "") && temp.indexOf(".") == temp.lastIndexOf(".")) {
            temp = new BigDecimal(temp).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
        if (!StringUtil.isNullOrBlank(temp) && StringUtil.isNumber(temp.substring(0, 1)) && temp.contains(".") && temp.contains("E") && temp.indexOf(".") == temp.lastIndexOf(".")) {
            temp = new BigDecimal(temp).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        }
        return temp;
    }


    /**
     * 判断是否为字母
     *
     * @param fstrData
     * @return
     */
    public static boolean check(String fstrData) {
        char c = fstrData.charAt(0);
        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 合并excel
     *
     * @param list
     * @param
     * @param savePath
     * @throws Exception
     */
    public static Integer mergeExcel(List<Integer> list, String fileName, String savePath, Integer rate, String code,String url) throws Exception {
        Collections.sort(list);
        int i = 0;
        int version = 0;
        File newFile = null;
        File oldFile = null;
        Map<String, String> param = new HashMap<>();
        int count = list.size() % 4 == 0 ? list.size() / 4 : (list.size() / 4) + 1;
        double rate1 = rate;
        while (i < list.size()) {
            newFile = new File(savePath + list.get(i) + ".xls");
            FileInputStream fs = new FileInputStream(newFile);
            POIFSFileSystem ps = new POIFSFileSystem(fs);
            HSSFWorkbook newWorkbook = new HSSFWorkbook(ps);
            newWorkbook.setSheetName(0,fileName);
            HSSFSheet sheet = newWorkbook.getSheetAt(0);
            HSSFRow FirstColumn = sheet.getRow(i);
            i++;
            version++;
            int size = i + 3;
            for (; i < size; i++) {
                if (!(i < list.size())) {
                    break;
                }
                oldFile = new File(savePath + list.get(i) + ".xls");
                FileInputStream fs1 = new FileInputStream(oldFile);
                POIFSFileSystem ps1 = new POIFSFileSystem(fs1);
                HSSFWorkbook oldWorkbook = new HSSFWorkbook(ps1);
                HSSFSheet sheet1 = oldWorkbook.getSheetAt(0);//获取到工作表，因为一个excel可能有多个工作表
                int lastRow = sheet.getLastRowNum() + 1;
                int dataRow = sheet1.getLastRowNum();
                for (int j = 0; j < dataRow; j++) {
                    HSSFRow row = sheet.createRow(lastRow + j);
                    for (int k = 0; k < FirstColumn.getPhysicalNumberOfCells(); k++) {
                        HSSFCell cell = row.createCell(k);
                        HSSFRichTextString text1 = new HSSFRichTextString(sheet1.getRow(j + 1).getCell(k) != null ? sheet1.getRow(j + 1).getCell(k).getStringCellValue() : "");
                        cell.setCellValue(text1);
                    }
                }
                oldFile.delete();
            }
            File newFile1=null;
            if(list.size()>4) {
                 newFile1 = new File(savePath + fileName + "_" + version + ".xls");
                rate1 += (100 / count)/(count+2);
            }else {
                newFile1 = new File(savePath + code  + ".xls");
                rate1 += (100 / count)/(count+1);
            }
            FileOutputStream out = new FileOutputStream(newFile1);
            newWorkbook.write(out);
            out.close();
            newFile.delete();
            param.put("code", code);
            param.put("status", 1 + "");
            param.put("rate", new BigDecimal(rate1).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
            HttpUtil.httpPost(url, param);
        }
        return new BigDecimal(rate1).setScale(2,BigDecimal.ROUND_HALF_UP).intValue();
    }




}
