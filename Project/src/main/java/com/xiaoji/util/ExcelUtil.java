package com.xiaoji.util;

import com.xiaoji.model.SheetData;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
    private static HSSFWorkbook hsWb = null;
    private static HSSFSheet hsSheet = null;
    private static XSSFWorkbook xsWb = null;
    private static XSSFSheet xsSheet = null;
    private static InputStream inputStream = null;
    private static FileOutputStream fileOutputStream = null;

    public static String exportExcelFileName(String name){
        StringBuffer fileName=new StringBuffer(System.getProperty("user.dir"))
                .append(File.separator).append("Project")   // TODO 发布项目时删除
                .append(File.separator).append("src")
                .append(File.separator).append("main")
                .append(File.separator).append("webapp")
                .append(File.separator).append("WEB-INF")
                .append(File.separator).append("export")
                .append(File.separator).append(name);//获取文件路径
        return fileName.toString();
    }

    public static String getExcelFileName(String name){
        StringBuffer fileName=new StringBuffer(System.getProperty("user.dir"))
                .append(File.separator).append("Project")   // TODO 发布项目时删除
                .append(File.separator).append("src")
                .append(File.separator).append("main")
                .append(File.separator).append("webapp")
                .append(File.separator).append("WEB-INF")
                .append(File.separator).append("template")
                .append(File.separator).append(name);//获取文件路径
        return fileName.toString();
    }

    /**
     * Excel (2007 xlsx 后缀 导出)
     * @param in
     * @param exportFilePath
     * @param sheetData
     * @throws IOException
     * @return void 返回类型
     */
    public static void createTableXLSX(InputStream in,String exportFilePath,String[] rowName,SheetData... sheetData) throws IOException{
        int rowIndex = 0;
        try {
            // 读取excel模板
            xsWb = new XSSFWorkbook(in);
            // 读取了模板内 sheet(0) 内容
            xsSheet = xsWb.getSheetAt(0);
            if (xsSheet.getLastRowNum() == 0  &&  xsSheet.getPhysicalNumberOfRows() == 0) {
                logger.info("空模板");
            }
            // 从第 row 行开始写数据
            rowIndex = xsSheet.getLastRowNum()+1;
            //String sheetName = xsSheet.getSheetName();
            logger.info("rowIndex: " + rowIndex);
            SheetData sd = sheetData[0];
            for(Object obj: sd.getDatas()){
                Row row = xsSheet.createRow(rowIndex);// 设置行
                //logger.info("obj: " + obj);
                Class<?> classType = obj.getClass();
                if (obj instanceof Map) {
                    Map map = (Map) obj;
                    for(int j=0;j< rowName.length;j++){
                        Cell cell = null;
                        cell = row.createCell(j);// 创建单元格
                        cell.setCellType(HSSFCell.ENCODING_UTF_16);// 中文处理
                        cell.setCellValue(new XSSFRichTextString(map.get(rowName[j])==null?"":map.get(rowName[j]).toString()));
                    }
                }else{
                    for(int j = 0;j< rowName.length;j++) {
                        String firstLetter = rowName[j].substring(0, 1).toUpperCase();// 将属性首字母转换成大写
                        String getMethodName = "get" + firstLetter + rowName[j].substring(1);
                        Method getMethod = classType.getMethod(getMethodName, new Class[]{});
                        Object value = getMethod.invoke(obj, new Object[]{}); // 获得对象的属性
                        Cell cell = null;
                        cell = row.createCell(j);// 创建单元格
                        cell.setCellType(HSSFCell.ENCODING_UTF_16);// 中文处理
                        cell.setCellValue(new XSSFRichTextString(value==null?"":value.toString()));
                    }
                }
                rowIndex = rowIndex+1;
            }
            // 修改模板内容导出新模板
            fileOutputStream = new FileOutputStream(exportFilePath);
            xsWb.write(fileOutputStream);
        }catch (Exception e) {
            logger.error("文件读写错误!");
        }finally {
            fileOutputStream.close();
        }
    }

    /**
     * Excel (2003 xls 后缀 导出)
     * @param in
     * @param exportFilePath
     * @param sheetData
     * @throws IOException
     * @return void 返回类型
     */
    public static void createTableXLS(InputStream in,String exportFilePath,String[] rowName,SheetData... sheetData) throws IOException{
        int rowIndex = 0;
        try {
            // 读取excel模板
            hsWb = new HSSFWorkbook(in);
            // 读取了模板内 sheet(0) 内容
            hsSheet = hsWb.getSheetAt(0);
            if (hsSheet.getLastRowNum() == 0  &&  hsSheet.getPhysicalNumberOfRows() == 0) {
                logger.info("空模板");
            }
            // 从第 row 行开始写数据
            rowIndex = hsSheet.getLastRowNum()+1;
            logger.info("rowIndex: " + rowIndex);
            SheetData sd = sheetData[0];
            for(Object obj: sd.getDatas()){
                Row row = hsSheet.createRow(rowIndex);// 设置行
                //logger.info("obj: " + obj);
                Class<?> classType = obj.getClass();
                if (obj instanceof Map) {
                    Map map = (Map) obj;
                    for(int j=0;j< rowName.length;j++){
                        Cell cell = null;
                        cell = row.createCell(j);// 创建单元格
                        cell.setCellType(HSSFCell.ENCODING_UTF_16);// 中文处理
                        cell.setCellValue(new XSSFRichTextString(map.get(rowName[j])==null?"":map.get(rowName[j]).toString()));
                    }
                }else{
                    for(int j = 0;j< rowName.length;j++) {
                        String firstLetter = rowName[j].substring(0, 1).toUpperCase();// 将属性首字母转换成大写
                        String getMethodName = "get" + firstLetter + rowName[j].substring(1);
                        Method getMethod = classType.getMethod(getMethodName, new Class[]{});
                        Object value = getMethod.invoke(obj, new Object[]{}); // 获得对象的属性
                        Cell cell = null;
                        cell = row.createCell(j);// 创建单元格
                        cell.setCellType(HSSFCell.ENCODING_UTF_16);// 中文处理
                        cell.setCellValue(new XSSFRichTextString(value==null?"":value.toString()));
                    }
                }
                rowIndex = rowIndex+1;
            }
            // 修改模板内容导出新模板
            fileOutputStream = new FileOutputStream(exportFilePath);
            hsWb.write(fileOutputStream);
        }catch (Exception e) {
            logger.error("文件读写错误!");
        }finally {
            fileOutputStream.close();
        }
    }

    /**
     * Excel 格式检验
     * @param importFilePath    模板
     * @param exportFilePath    导出
     * @param rowName           行列名  可为null
     * @param sheetData         数据
     * @return String
     */
    public static synchronized String exportExcel(String importFilePath,String exportFilePath,String[] rowName,SheetData... sheetData){
        String str = "fail";
        if ("success".equals(fileExists(importFilePath,exportFilePath))) {
            try {
                inputStream = new FileInputStream(importFilePath);
                if (rowName != null && !"".equals(rowName)){
                    if (importFilePath.endsWith(".xlsx")){
                        createTableXLSX(inputStream, exportFilePath,rowName,sheetData);
                    }
                    else if (importFilePath.endsWith(".xls")){
                        createTableXLS(inputStream, exportFilePath,rowName, sheetData);
                    }else
                        str = "不支持的文件类型";
                }else {
                    if (importFilePath.endsWith(".xlsx"))
                        createXLSX(inputStream, exportFilePath, sheetData);
                    else if (importFilePath.endsWith(".xls"))
                        createXLS(inputStream, exportFilePath, sheetData);
                    else
                        str = "不支持的文件类型";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return str;
            }
            str = "success";
        }
        return str;
    }

    public static String fileExists(String importFilePath,String exportFilePath){
        // 检测模板文件是否存在
        File mbFile = new File(importFilePath);
        if(!mbFile.exists()){
            //logger.error("模板文件不存在,请确认其位置");
            return "模板文件不存在,请确认其位置";
        }
        // 检测目标路径是否存在，不存在则创建
        File reFile = new File(exportFilePath);
        if(!reFile.exists() && !reFile.isDirectory()){
            reFile.getParentFile().mkdir();
            //logger.info("创建导出文件目录");
        }
        return "success";
    }

    /**
     * Excel (2003 xls后缀 导出)
     * @return void 返回类型
     */
    public static void createXLS(InputStream in,String exportFilePath,SheetData... sheetData) throws IOException{
        try {
            // 读取excel模板
            hsWb = new HSSFWorkbook(in);
            for (int i = 0; i < hsWb.getNumberOfSheets(); i++) {
                // 读取了模板内所有 sheet 内容
                hsSheet = hsWb.getSheetAt(i);
                if (hsSheet.getLastRowNum() == 0  &&  hsSheet.getPhysicalNumberOfRows() == 0) {
                    continue;
                }

                // 如果这行没有了，整个公式都不会有自动计算的效果的
                hsSheet.setForceFormulaRecalculation(true);
                // 写数据
                if ("".equals(sheetData[i]) || sheetData[i] == null){
                    continue;
                }else {
                    ExcelUtil.writeData(sheetData[i], xsWb.getSheetAt(i));
                }
            }
            // 修改模板内容导出新模板
            fileOutputStream = new FileOutputStream(exportFilePath);
            hsWb.write(fileOutputStream);
        }catch (Exception e) {
            logger.error("文件读写错误!");
        }finally {
            fileOutputStream.close();
        }
    }
    /**
     * Excel (2007 xlsx 后缀 导出)
     * @param in
     * @param exportFilePath
     * @param sheetData
     * @throws IOException
     * @return void 返回类型
     */
    public static void createXLSX(InputStream in,String exportFilePath,SheetData... sheetData) throws IOException{
        try {
            // 读取excel模板
            xsWb = new XSSFWorkbook(in);
            for (int i = 0; i < xsWb.getNumberOfSheets(); i++) {
                // 读取了模板内所有 sheet 内容
                xsSheet = xsWb.getSheetAt(i);
                if (xsSheet.getLastRowNum() == 0  &&  xsSheet.getPhysicalNumberOfRows() == 0) {
                    continue;
                }
                // 如果这行没有了，整个公式都不会有自动计算的效果的
                xsSheet.setForceFormulaRecalculation(true);
                // 写数据
                if (sheetData[i] == null || "".equals(sheetData[i])){
                    continue;
                }else {
                    ExcelUtil.writeData(sheetData[i], xsWb.getSheetAt(i));
                }
            }
            // 修改模板内容导出新模板
            fileOutputStream = new FileOutputStream(exportFilePath);
            xsWb.write(fileOutputStream);
        }catch (Exception e) {
            logger.error("文件读写错误!");
        }finally {
            fileOutputStream.close();
        }
    }

    /**
     * 向 sheet 页中写入数据
     * @param sheetData 数据Map
     * @param sheet sheet
     */
    public static void writeData(SheetData sheetData,Sheet sheet) {
        // 从 sheet 中找到匹配符 #{}表示单个 , ${} 表示集合,从该单元格开始向下追加
        for(Iterator<Row> rowIt = sheet.rowIterator(); rowIt.hasNext();) {
            Row row = rowIt.next();
            // 取cell
            for(int j = row.getFirstCellNum() ; j < row.getLastCellNum() ; j++) {
                Cell cell = row.getCell(j);
                // 判断cell的内容是否包含 $ 或者 #
                if(cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue() != null
                        && (cell.getStringCellValue().contains("$") || cell.getStringCellValue().contains("#") )) {
                    // 剥离# $
                    String[] winds = CommonUtil.getWildcard(cell.getStringCellValue().trim());
                    for(String wind : winds) {
                        writeData(sheetData, wind , cell , sheet);
                    }
                }
            }
        }
    }
    /**
     * 填充数据
     * @param sheetData
     * @param keyWind #{name}只替换当前 or ${names} 从当前行开始向下替换
     */
    public static void writeData(SheetData sheetData , String keyWind , Cell cell , Sheet sheet) {
        String key = keyWind.substring(2 , keyWind.length() - 1);
        if(keyWind.startsWith("#")) {
            //简单替换
            Object value = sheetData.get(key);
            //为空则替换为空字符串
            if(value == null)   value = "" ;
            String cellValue = cell.getStringCellValue();
            cellValue = cellValue.replace(keyWind, value.toString());
            cell.setCellValue(cellValue);
        } else  if(keyWind.startsWith("$")) {
            // 从list中每个实体开始解,行数从当前开始
            int rowindex = cell.getRowIndex();
            int columnindex = cell.getColumnIndex();
            List<? extends Object> listdata = sheetData.getDatas();
            // 不为空的时候开始填充
            if(listdata != null && !listdata.isEmpty()){
                for(Object o : listdata) {
                    Object cellValue = CommonUtil.getValue(o, key);
                    Row row = sheet.getRow(rowindex);
                    if(row == null) {
                        row = sheet.createRow(rowindex);
                    }
                    // 取出cell
                    Cell c = row.getCell(columnindex);
                    if(c == null)
                        c = row.createCell(columnindex);
                    if(cell.getCellStyle() != null){
                        c.setCellStyle(cell.getCellStyle());
                    }
                    if("".equals(cell.getCellType())) {
                        c.setCellType(cell.getCellType());
                    }
                    if(cellValue != null) {
                        if(cellValue instanceof Number || CommonUtil.isNumber(cellValue) )
                            c.setCellValue( Double.valueOf(cellValue.toString()));
                        else if(cellValue instanceof Boolean)
                            c.setCellValue((Boolean)cellValue);
                        else if(cellValue instanceof Date)
                            c.setCellValue((Date)cellValue);
                        else
                            c.setCellValue(cellValue.toString());
                    } else {
                        // 数据为空 如果当前单元格已经有数据则重置为空
                        if(c.getStringCellValue() != null) {
                            c.setCellValue("");
                        }
                    }
                    rowindex++ ;
                }
            } else {
                // list 数据为空则将$ 全部替换空字符串
                String cellValue = "" ;
                cell.setCellValue(cellValue);
            }
        }
    }

}
