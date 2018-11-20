package com.xiaoji.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoji.configuration.config.YmlConfig;
import com.xiaoji.model.SheetData;
import com.xiaoji.service.ProjectService;
import com.xiaoji.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/project")
public class FileController {
    @Autowired
    private ProjectService projectService;

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/file")
    @ResponseBody
    public void initialize(HttpServletRequest request) {

    }


    @RequestMapping("/download")
    @ResponseBody
    public MessageResult download(HttpServletRequest request,HttpServletResponse response) {
        SheetData sheetData = new SheetData();
        // 接收参数
        String groupSubdomain = request.getParameter("groupSubdomain");
        String groupSAPrefix = request.getParameter("groupSAPrefix");
        String templateId = request.getParameter("templateId");
        JSONObject data = JSONObject.parseObject(request.getParameter("data"));

        sheetData = CommonUtil.getData(sheetData, data);
        // 入参检查 入参必须项检查
        if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");
        if (sheetData == null || "".equals(sheetData))    return ResultResponse.makeErrRsp("必须注明");
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        try{
            Map map = projectService.findById(templateId);
            if (map == null || "".equals(map)){
                return ResultResponse.makeErrRsp("目标模板不存在");
            }
            String importFilePath = ExcelUtil.getExcelFileName(map.get("template_file").toString());
            String exportFilePath = ExcelUtil.exportExcelFileName(map.get("template_file").toString());
            String[] rowName = null;

            if (!"success".equals(ExcelUtil.exportExcel(importFilePath,exportFilePath,rowName,sheetData))){
                return ResultResponse.makeErrRsp("模板数据写入失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.makeErrRsp("文件下载失败");
        }
        return ResultResponse.makeOKRsp();
    }

    @RequestMapping("/cache")
    @ResponseBody
    public MessageResult cache(HttpServletRequest request) {
        SheetData sheetData = new SheetData();
        // 接收参数
        String groupSubdomain = request.getParameter("groupSubdomain");
        String groupSAPrefix = request.getParameter("groupSAPrefix");
        String templateId = request.getParameter("templateId");
        String data = request.getParameter("data");

        // 入参检查 入参必须项检查
        if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");
        if (sheetData == null || "".equals(sheetData))    return ResultResponse.makeErrRsp("必须注明");
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        try {
            Map map = projectService.findById(templateId);
            if (map == null || "".equals(map)){
                return ResultResponse.makeErrRsp("目标模板不存在");
            }
            logger.info("生成缓存");
            String name = map.get("template_name").toString();
            CacheBF.cache(name, data, 3600);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.makeErrRsp("生成缓存失败");
        }

        return ResultResponse.makeOKRsp();
    }

    @RequestMapping("/findCache")
    @ResponseBody
    public MessageResult findCache(HttpServletRequest request) {
        SheetData sheetData = new SheetData();
        // 接收参数
        String groupSubdomain = request.getParameter("groupSubdomain");
        String groupSAPrefix = request.getParameter("groupSAPrefix");
        String templateId = request.getParameter("templateId");
        JSONObject data = JSONObject.parseObject(request.getParameter("data"));

        //sheetData = CommonUtil.getData(sheetData, data);

        // 入参检查 入参必须项检查
        if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");
        if (sheetData == null || "".equals(sheetData))    return ResultResponse.makeErrRsp("必须注明");
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        try {
            Map map = projectService.findById(templateId);
            if (map == null || "".equals(map)){
                return ResultResponse.makeErrRsp("目标模板不存在");
            }
            String name = map.get("template_name").toString();
            logger.info("name\t"+name);
            if (CacheBF.check(name)){
                logger.info("true");
                logger.info(CacheBF.get(name,SheetData.class)+"");
            }else {
                logger.info("false");
                return ResultResponse.makeErrRsp("没有缓存");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.makeErrRsp("查询缓存失败");
        }
        return ResultResponse.makeOKRsp();
    }
}
