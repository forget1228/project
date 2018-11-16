package com.xiaoji.util;

import com.xiaoji.model.SheetData;
import com.xiaoji.model.TestData;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportExcelDemo {

    public static void main(String[] args) throws IOException {
        /*String importFilePath= "E://file/demo/test2.xls";

        logger.info(System.getProperty("user.dir")+"/Project/src/doc");
        ExcelUtil.fileExists(importFilePath,System.getProperty("user.dir")+"/Project/src/doc/ccc.xls");*/
        //Excel2003();
        //Excel2007();
        String importFilePath= "E://file/demo/test2.xlsx";
        String exportFilePath= System.getProperty("user.dir")+"/Project/src/doc/ccc.xls";

        String strFields1="projectName,demandName,sumNumbers";
        String[] rowName = strFields1.split(",");
        SheetData[] sds = new SheetData[2];
        SheetData sd = new SheetData();
        sd.put("title","12345678中国");
        List list = new ArrayList();
        for (int i=0;i < 10;i++){
            TestData testData = new TestData(i,"test"+i,"-"+i);
            list.add(testData);
        }
        sd.addDatas(list);

        /*for (int i=0;i < 50;i++) {
            Map map = new HashMap();
            map.put("projectName","Id"+i);
            map.put("demandName","姓名"+i);
            map.put("sumNumbers","年龄"+i);
            sd.addData(map);
        }*/
        sds[0] = sd;
        logger.info(ExcelUtil.exportExcel(importFilePath,exportFilePath,rowName,sds));
    }

    private static Logger logger = LoggerFactory.getLogger(ExportExcelDemo.class);

    // 显示的导出表的 sheet 名称
    private String title;
    // 导出表的列数据名称
    private String[] rowName;
    // 数据集合
    private List<Object[]> dataList = new ArrayList<>();

    /**
     * 构造方法，传入要导出的数据
     * @param title
     * @param rowName
     * @param dataList
     */
    public ExportExcelDemo(String title, String[] rowName, List<Object[]> dataList) {
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
    }


    static void Excel2007(){
        String importFilePath= "E://file/demo/test2.xlsx";
        String exportFilePath= "E://file/result/demo1.xlsx";
        SheetData[] sds = new SheetData[2];

        SheetData sd = new SheetData();
        sd.put("title","12345678中国");
        for (int i=0;i < 50;i++) {
            Map map = new HashMap();
            map.put("projectName","Id"+i);
            map.put("demandName","姓名"+i);
            map.put("sumNumbers","年龄"+i);
            sd.addData(map);
        }
        sds[0] = sd;

        String str = ExcelUtil.exportExcel(importFilePath,exportFilePath,null,sds);
        logger.info(str);
    }
    static void Excel2003(){
        String importFilePath= "E://file/demo/test.xls";
        String exportFilePath= "E://file/result/demo.xls";
        SheetData[] sds = new SheetData[2];

        SheetData sd = new SheetData();
        sd.put("title","12345678中国");
        for (int i=0;i < 50;i++) {
            Map map = new HashMap();
            map.put("id","Id"+i);
            map.put("name","姓名"+i);
            map.put("age","年龄"+i);
            map.put("sex","性别"+i);
            sd.addData(map);
        }
        sds[0] = sd;

        SheetData sd2 = new SheetData();
        sd.put("title","中国12345678");
        for (int i=0;i < 50;i++) {
            Map map = new HashMap();
            map.put("id","Id  -"+i);
            map.put("name","姓名  -"+i);
            map.put("age","年龄  -"+i);
            map.put("sex","性别  -"+i);
            sd2.addData(map);
        }
        sds[1] = sd2;
        String str = ExcelUtil.exportExcel(importFilePath,exportFilePath,null,sds);
        logger.info(str);
    }
}
