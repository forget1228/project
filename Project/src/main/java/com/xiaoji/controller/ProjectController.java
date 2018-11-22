package com.xiaoji.controller;

import com.xiaoji.configuration.config.YmlConfig;
import com.xiaoji.model.SheetData;
import com.xiaoji.service.ProjectService;
import com.xiaoji.util.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @RequestMapping(value = "/initialize",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult initialize(HttpServletRequest request) {
        // 接收参数
        String saPrefix = request.getParameter("saPrefix");
        String templateClassName = request.getParameter("templateClassName");
        String templateId = request.getParameter("templateId");
        String templateName = request.getParameter("templateName");
        String templateGenerator = request.getParameter("templateGenerator");
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("templateFile");
        if (files.size()>=2 || files.size()<= 0)     return ResultResponse.makeErrRsp("请上传一个文件");

        // 入参检查 入参必须项检查
        if (saPrefix == null || "".equals(saPrefix)){ return ResultResponse.makeErrRsp("来源必须注明"); }
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        MultipartFile file = null;
        file = files.get(0);
        if(file.isEmpty() || file.getSize() == 0 )    return ResultResponse.makeErrRsp("文件 ‘"+ file.getOriginalFilename() +"’ 无数据");

        // 参数值入库
        Map<String,Object> map = new HashMap<>();
        map.put("sa_prefix",saPrefix);
        map.put("template_class_name",templateClassName);
        map.put("template_id",templateId);
        map.put("template_name",templateName);
        map.put("template_generator",templateGenerator);
        map.put("template_file",file.getOriginalFilename());
        map.put("template_type","0");
        try {
            File dest = CommonUtil.getFile(ExcelUtil.getExcelFileName(file.getOriginalFilename()));
            file.transferTo(dest); //保存文件
            projectService.abkRecording(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.makeErrRsp("初始化失败");
        }
        return ResultResponse.makeOKRsp();
    }

    @RequestMapping(value = "/find",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult find(HttpServletRequest request) {
        try {
            return ResultResponse.makeOKRsp(projectService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.makeErrRsp("查询失败");
        }
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public void test(HttpServletRequest request,HttpServletResponse response) {
        try {
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
            String strFields1="projectName,demandName,sumNumbers";
            String[] rowName = strFields1.split(",");
            String filePath = "F://Git/project/Project/src/main/webapp/WEB-INF/template/download.xlsx";
            ExcelUtil.excel(filePath,rowName,sds);
            ExcelUtil.outputExcel("test.xlsx",response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
