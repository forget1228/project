package com.xiaoji;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoji.model.SheetData;
import com.xiaoji.model.TestData;
import com.xiaoji.util.CacheBF;
import com.xiaoji.util.ExcelUtil;
import com.xiaoji.util.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportExcelDemo {

    public static void main(String[] args) throws Exception {
        test();
    }




    public void cach(){
        Data data= new Data();
        data.setInfo("hello");
        data.setName("world");
        try {
            CacheBF.cache("123",data,100);

            System.out.println("缓存是否存在："+CacheBF.check("123"));
            Data da = CacheBF.get("123",Data.class);
            System.out.println(da);

            CacheBF.delete("123");
            System.out.println("缓存是否存在："+CacheBF.check("123"));
            Data dd = CacheBF.get("123",Data.class);
            System.out.println(dd);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //测试使用
    static class Data {
        private String name;
        private String info;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }

    public static void test (){
        String importFilePath= "E://file/demo/test.xlsx";
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
        logger.info(ExcelUtil.exportExcel(importFilePath,exportFilePath,null,sds));
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
