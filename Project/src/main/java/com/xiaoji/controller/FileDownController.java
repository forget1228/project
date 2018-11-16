package com.xiaoji.controller;

import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/project")
public class FileDownController {
    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
    public void exportData(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //我这是根据前端传来的起始时间来查询数据库里的数据，如果没有输入变量要求，保留前两个就行

        String[] headers = { "ID", "主题", "姓名", "手机","创建时间","开始时间","结束时间"};//导出的Excel头部，这个要根据自己项目改一下

        //下面的完全不动就行了（Excel数据中不包含图片）

        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 18);
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //产生数据行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("姓名");
        row2.createCell(1).setCellValue("班级");
        row2.createCell(2).setCellValue("笔试成绩");
        row2.createCell(3).setCellValue("机试成绩");
        //在sheet里创建第三行
        HSSFRow row3=sheet.createRow(2);
        row3.createCell(0).setCellValue("李明");
        row3.createCell(1).setCellValue("As178");
        row3.createCell(2).setCellValue(87);
        row3.createCell(3).setCellValue(78);

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=createList.xls");//默认Excel名称
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //根据系统的需要获取数据源
        List list = new ArrayList();
        for (int i = 0;i<50;i++){
            list.add(i);
        }
        //获得模版
        String tempFileName = request.getSession().getServletContext().getRealPath("/excelTemplate");
        //将结果放入这个list中
        List values = new ArrayList();
        Map beans = new HashMap();
        Date date = new Date();
        SimpleDateFormat simpl = new SimpleDateFormat("yyyyMMddHHmmss");
        String currntTime = simpl.format(date);
        tempFileName += "/policyDemo.xls";

        //导出列表名
        String fileName = currntTime+"列表.xls";
        values.add(list);
        beans.put("values", values);

        //文件名称统一编码格式
        fileName = URLEncoder.encode(fileName, "utf-8");

        //生成的导出文件
        File destFile = File.createTempFile(fileName, ".xls");



        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //将数据添加到模版中生成新的文件
            //transformer.transformXLS(tempFileName, beans, destFile.getAbsolutePath());
            //将文件输入
            InputStream inputStream = new FileInputStream(destFile);
            // 设置response参数，可以打开下载页面
            response.reset();
            //设置响应文本格式
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            //将文件输出到页面
            OutputStream out = response.getOutputStream();
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // 根据读取并写入
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //使用完成后关闭流
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
            }

        }

/*
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet();
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 18);
        HSSFRow row = sheet.createRow(0);


        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=createList.xls");//默认Excel名称
        response.flushBuffer();
        workbook.write(response.getOutputStream());*/
    }
}
