package com.xiaoji.controller;

import com.xiaoji.service.ProjectService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/project")
public class FileController {
    @Autowired
    private ProjectService projectService;

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/file")
    public void upload(HttpServletRequest request,HttpServletResponse response) {
        /*HSSFWorkbook wb = new HSSFWorkbook();
        OutputStream os = null;
        String fileName = "doctorImportStand.xls";
        try {
            os = response.getOutputStream();
            wb =new ExcelUtil().createWorkbookModel(os);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            wb.write(os);
            wb.close();
            os.close();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //模板路径及名称
        String temp = "D://file";

        String tempPath = temp + "/" + "test.xls";
        logger.info("模板文件存放目录:"+tempPath);

        //目标路径及名称
        String mbFileName = temp + "/" + "t.xls";
        logger.info("目标文件生成目录:"+mbFileName);
        List list = new ArrayList<>();
        for (int i=0;i<50;i++) {
            Map map = new HashMap();
            map.put("id","序号"+i);
            map.put("name","姓名"+i);
            map.put("age","年龄"+i);
            map.put("sex","性别"+i);
            list.add(map);
        }

        export(tempPath, temp, response, list);


        //exportExcel("D://file","test.xls","D://file","t.xls", list);
    }


    public void export(String tempPath, String path, HttpServletResponse response, List list) {
        String fileName ="t.xls";
        File newFile = createNewFile(tempPath, path);
        InputStream is = null;
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
        try {
            is = new FileInputStream(newFile);// 将excel文件转为输入流
            wb = new HSSFWorkbook(is);// 创建个workbook，
            // 获取第一个sheet
            sheet = wb.createSheet("test");

            // 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
            HSSFRow row = sheet.createRow(0);
            // 写数据
            FileOutputStream fos = new FileOutputStream(newFile);
            if (row == null) {
                row = sheet.createRow(0);
            }
            HSSFCell cell = row.getCell(0);
            if (cell == null) {
                cell = row.createCell(0);
            }
            cell.setCellValue("我是标题");

            for (int i = 0; i < list.size(); i++) {
                Object vo = list.get(i);
                row = sheet.createRow(i+2); //从第三行开始
                //根据excel模板格式写入数据....
                //创建单元格并设置单元格内容
                row.createCell(0).setCellValue(list.get(i).toString());
            }

            wb.write(fos);
            fos.flush();
            fos.close();

            // 下载
            InputStream fis = new BufferedInputStream(new FileInputStream(
                    newFile));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            response.setContentType("text/html;charset=UTF-8");
            OutputStream toClient = new BufferedOutputStream(
                    response.getOutputStream());
            response.setContentType("application/x-msdownload");
            String newName = URLEncoder.encode(
                      System.currentTimeMillis() + ".xlsx",
                    "UTF-8");
            response.addHeader("Content-Disposition",
                    "attachment;filename=\"" + newName + "\"");
            response.addHeader("Content-Length", "" + newFile.length());
            toClient.write(buffer);
            toClient.flush();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        //写入到输出流
        /*try {
            OutputStream outputStream = response.getOutputStream();
            try {
                fileName = URLEncoder.encode(fileName, "utf8");
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/msexcel;charset=utf-8");// 设置contentType为excel格式
            response.setHeader("Content-Disposition", "Attachment;Filename="+ fileName+".xls");
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException e) {
            workbook = null;
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        // 删除创建的新文件
        this.deleteFile(newFile);
    }




    /**
     *根据当前row行，来创建index标记的列数,并赋值数据
     */
    private void createRowAndCell(Object obj, XSSFRow row, XSSFCell cell, int index) {
        cell = row.getCell(index);
        if (cell == null) {
            cell = row.createCell(index);
        }

        if (obj != null)
            cell.setCellValue(obj.toString());
        else
            cell.setCellValue("");
    }

    /**
     * 复制文件
     *
     * @param s
     *            源文件
     * @param t
     *            复制到的新文件
     */

    public void fileChannelCopy(File s, File t) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(s), 1024);
                out = new BufferedOutputStream(new FileOutputStream(t), 1024);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取excel模板，并复制到新文件中供写入和下载
     *
     * @return
     */
    public File createNewFile(String tempPath, String rPath) {
        // 读取模板，并赋值到新文件************************************************************
        // 文件模板路径
        String path = (tempPath);
        File file = new File(path);
        // 保存文件的路径
        String realPath = rPath;
        // 新的文件名
        String newFileName = System.currentTimeMillis() + ".xlsx";
        // 判断路径是否存在
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 写入到新的excel
        File newFile = new File(realPath, newFileName);
        try {
            newFile.createNewFile();
            // 复制模板到新文件
            fileChannelCopy(file, newFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * 下载成功后删除
     *
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }


    public int exportExcel(String tempPath, String tempName, String objFilePath, String objFileName, List resourceList){
        // 参数非空
        if(tempPath.equals(null)||tempName.equals(null)||objFilePath.equals(null)||objFileName.equals(null)||resourceList.equals("null")||resourceList.size()<1||resourceList==null){
            System.out.println("传入参数有误或有空值，中断执行");
            return 1;
        }

        //模板路径及名称
        tempPath=tempPath + "/" + tempName;
        logger.info("模板文件存放目录:"+tempPath);

        //目标路径及名称
        String mbFileName = objFilePath + "/" + objFileName;
        logger.info("目标文件生成目录:"+mbFileName);
        //添加要生成的值
        //ExcelUtil.addValue("reportList", resourceList);
        try {
            //检测模板文件是否存在
            File lcfile = new File(tempPath);
            if(!lcfile.exists()){
                logger.error("模板文件不存在,请确认其位置");
                return -1;

            }
            //检测目标路径是否存在，不存在则创建
            File file =new File(objFilePath);
            if(!file.exists()  && !file.isDirectory()){
                file .mkdir();
            }
            //导出Excel
            //ExcelUtil.export(tempPath, new FileOutputStream(mbFileName));
        }catch (Exception e) {
            logger.error("导出异常:",e.fillInStackTrace());
            return 2;
        }
        logger.info("导出成功,文件生成目录:"+mbFileName);
        return 0;
    }
}
