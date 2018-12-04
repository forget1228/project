package com.xiaoji.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoji.model.SheetData;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
    private static Workbook workbook = null;//工作薄

    public static String exportExcelFileName(String name){
        StringBuffer fileName=new StringBuffer(System.getProperty("user.dir"))
                .append(File.separator).append("Project")   // TODO 发布项目时删除
                .append(File.separator).append("src")
                .append(File.separator).append("main")
                .append(File.separator).append("webapp")
                .append(File.separator).append("WEB-INF")
                .append(File.separator).append("cache")
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

    public static void excel(String filePath, SheetData... sheetData) throws Exception{
        if (CommonUtil.fileExists(filePath)) {
            boolean isExcel2003 = filePath.toLowerCase().endsWith("xls")?true:false;
            if (isExcel2003) {
                // HSSFWorkbook只能操作excel2003
                workbook = new HSSFWorkbook(new FileInputStream(new File(filePath)));
            } else {
                // XSSFWorkbook只能操作excel2007以上版本
                workbook = new XSSFWorkbook(new FileInputStream(new File(filePath)));
            }
            createExcel(workbook, sheetData);
        }else {
            throw new RuntimeException("模板文件不存在");
        }
    }

    public static void createExcel(Workbook workbook, SheetData... sheetData) throws Exception {
        Sheet sheet = null;
        sheet = workbook.getSheetAt(0);
        if (sheet.getLastRowNum() == 0  &&  sheet.getPhysicalNumberOfRows() == 0) {
            logger.info("空模板");
            throw new RuntimeException("空模板");
        }
        //就一个的话 直接用模板
        int size = sheetData.length ;

        // 如果这行没有了，整个公式都不会有自动计算的效果的
        sheet.setForceFormulaRecalculation(true);
        Sheet source =  workbook.getSheetAt(0); // 模板
        for(int i = 0 ; i < size  ; i++) {
            Sheet toSheet = workbook.createSheet(sheetData[i].getSheetName() == null ? sheet.getSheetName()+i:sheetData[i].getSheetName());
            copySheet(workbook, source, toSheet);

            // 写数据
            if (sheetData[i].getRowName() == null) {
                writeData(workbook.getSheetAt(i+1),sheetData[i]);
            }else {
                elseExcel(workbook.getSheetAt(i+1),sheetData[i].getRowName(),sheetData[i]);
            }
        }
        workbook.removeSheetAt(0);
    }

    private static void elseExcel(Sheet sheet,String[] rowName, SheetData sheetData)throws Exception{
        try {
            sheet.getRow(0).getCell(0).setCellValue(sheetData.get("demandName").toString());
            int rowIndex = 0;
            // 从第 row 行开始写数据
            rowIndex = sheet.getLastRowNum();
            logger.info("rowIndex: " + rowIndex);
            for (Object obj : sheetData.getDatas()) {
                Row row = sheet.createRow(rowIndex);// 设置行
                //logger.info("obj: " + obj);
                Class<?> classType = obj.getClass();
                if (obj instanceof Map) {
                    Map map = (Map) obj;
                    for (int j = 0; j < rowName.length; j++) {
                        Cell cell = null;
                        cell = row.createCell(j);// 创建单元格
                        cell.setCellType(HSSFCell.ENCODING_UTF_16);// 中文处理
                        cell.setCellValue(new XSSFRichTextString(map.get(rowName[j]) == null ? "" : map.get(rowName[j]).toString()));
                    }
                } else {
                    for (int j = 0; j < rowName.length; j++) {
                        String firstLetter = rowName[j].substring(0, 1).toUpperCase();// 将属性首字母转换成大写
                        String getMethodName = "get" + firstLetter + rowName[j].substring(1);
                        Method getMethod = classType.getMethod(getMethodName, new Class[]{});
                        Object value = getMethod.invoke(obj, new Object[]{}); // 获得对象的属性
                        Cell cell = null;
                        cell = row.createCell(j);// 创建单元格
                        cell.setCellType(HSSFCell.ENCODING_UTF_16);// 中文处理
                        cell.setCellValue(new XSSFRichTextString(value == null ? "" : value.toString()));
                    }
                }
                rowIndex = rowIndex + 1;
            }
        }catch (Exception e){
            throw new RuntimeException("数据读写失败");
        }
    }

    /**
     * Sheet复制
     * @param fromSheet
     * @param toSheet
     */
    private static void copySheet(Workbook wb,Sheet fromSheet, Sheet toSheet) {
        // 合并区域处理
        mergerRegion(fromSheet, toSheet);
        int index = 0;
        for (Iterator<Row> rowIt = fromSheet.rowIterator(); rowIt.hasNext();) {
            Row tmpRow =  rowIt.next();
            Row newRow = toSheet.createRow(tmpRow.getRowNum());

            CellStyle style = tmpRow.getRowStyle();
            if(style != null)
                newRow.setRowStyle(tmpRow.getRowStyle());

            newRow.setHeight(tmpRow.getHeight());

            // 针对第一行设置行宽
            if(index == 0) {
                int first = tmpRow.getFirstCellNum();
                int last = tmpRow.getLastCellNum();
                for(int i = first ; i < last ; i++) {
                    int w = fromSheet.getColumnWidth(i);
                    toSheet.setColumnWidth(i, w + 1);
                }
                toSheet.setDefaultColumnWidth(fromSheet.getDefaultColumnWidth());
            }
            // 行复制
            copyRow(wb,tmpRow,newRow);

            index++ ;
        }
    }

    /**
     * 复制原有sheet的合并单元格到新创建的sheet
     * @param fromSheet  新创建sheet
     * @param toSheet      原有的sheet
     */
    private static void mergerRegion(Sheet fromSheet, Sheet toSheet) {
        int sheetMergerCount = fromSheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            CellRangeAddress cra = fromSheet.getMergedRegion(i);
            toSheet.addMergedRegion(cra);
        }
    }

    /**
     * 行复制功能
     * @param fromRow
     * @param toRow
     */
    private static void copyRow(Workbook wb,Row fromRow,Row toRow){
        for (Iterator<Cell> cellIt = fromRow.cellIterator(); cellIt.hasNext();) {
            Cell tmpCell = cellIt.next();
            Cell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(wb,tmpCell, newCell);
        }
    }

    /**
     * 复制单元格
     * @param srcCell
     * @param distCell
     */
    private static void copyCell(Workbook wb,Cell srcCell, Cell distCell) {
        CellStyle newstyle=wb.createCellStyle();
        newstyle.cloneStyleFrom(srcCell.getCellStyle());
        // 样式
        distCell.setCellStyle(newstyle);
        // 评论
        if (srcCell.getCellComment() != null) {
            distCell.setCellComment(srcCell.getCellComment());
        }
        // 不同数据类型处理
        int cType = srcCell.getCellType();
        distCell.setCellType(cType);
        switch (cType) {
            case HSSFCell.CELL_TYPE_STRING:
                distCell.setCellValue(srcCell.getRichStringCellValue());
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                distCell.setCellValue(srcCell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                distCell.setCellFormula(srcCell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                distCell.setCellValue(srcCell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                distCell.setCellValue(srcCell.getErrorCellValue());
                break;
            default:
                distCell.setCellValue(srcCell.getRichStringCellValue());
                break;
        }
    }

    /**
     * 向 sheet 页中写入数据
     * @param sheetData 数据Map
     * @param sheet sheet
     */
    private static void writeData(Sheet sheet,SheetData sheetData) throws Exception{
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
    private static void writeData(SheetData sheetData , String keyWind , Cell cell , Sheet sheet) {
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

    /**
     * 输出 EXCEL 文件
     * @param fileName 文件名
     * @param response
     */
    public static void outputExcel(String fileName, HttpServletResponse response) {
        //String FileName =  fileName+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//不重复文件名
        try {
            response.setContentType("application/vnd.ms-excel");
            fileName = new String(fileName.getBytes(),"ISO8859-1");
            response.setHeader("Content-disposition","attachment; filename=" + fileName);
            ServletOutputStream os=response.getOutputStream();
            workbook.write(os);
            os.flush();
            os.close(); // 关闭
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存 EXCEL 文件
     * @param exportFilePath 保存路径
     */
    public static void outputFile(String exportFilePath) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(exportFilePath);
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
