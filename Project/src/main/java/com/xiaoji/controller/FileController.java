package com.xiaoji.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoji.configuration.config.YmlConfig;
import com.xiaoji.model.SheetData;
import com.xiaoji.model.TestData;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/project")
public class FileController {
    @Autowired
    private ProjectService projectService;

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("/download")
    @ResponseBody
    public MessageResult download(HttpServletRequest request) {
        // 接收参数
        String groupSubdomain = request.getParameter("groupSubdomain");
        String groupSAPrefix = request.getParameter("groupSAPrefix");
        String templateId = request.getParameter("templateId");
        String data = request.getParameter("data");

        // 入参检查 入参必须项检查
        if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");
        if (data == null || "".equals(data))    return ResultResponse.makeErrRsp("必须注明");
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        try {
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.makeErrRsp("download----失败");
        }
        return ResultResponse.makeOKRsp();
    }

    @RequestMapping("/downloadCache")
    @ResponseBody
    public MessageResult downloadCache(HttpServletRequest request) {
        // 接收参数
        String groupSubdomain = request.getParameter("groupSubdomain");
        String groupSAPrefix = request.getParameter("groupSAPrefix");
        String templateId = request.getParameter("templateId");
        String instanceId = request.getParameter("instanceId");

        // 入参检查 入参必须项检查
        if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");
        if (instanceId == null || "".equals(instanceId))    return ResultResponse.makeErrRsp("必须注明");
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        try {

            Map out = new HashMap();
            out.put("instance_id","1");
            out.put("instance_url","2");
            return ResultResponse.makeOKRsp(out);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.makeErrRsp("findCache----查询缓存失败");
        }
    }

    @RequestMapping("/downloadMany")
    @ResponseBody
    public MessageResult downloadMany(HttpServletRequest request) {
        // 接收参数
        String groupSubdomain = request.getParameter("groupSubdomain");
        String groupSAPrefix = request.getParameter("groupSAPrefix");
        String templateId = request.getParameter("templateId");
        String instanceId = request.getParameter("instanceId");

        // 入参检查 入参必须项检查
        if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");
        if (instanceId == null || "".equals(instanceId))    return ResultResponse.makeErrRsp("必须注明");
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        try {
            Map out = new HashMap();
            out.put("instance_id","1");
            out.put("instance_url","2");
            out.put("instanceStatus","3");
            return ResultResponse.makeOKRsp(out);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.makeErrRsp("findCache----查询缓存失败");
        }
    }

    @RequestMapping("/cache")
    @ResponseBody
    public MessageResult cache(HttpServletRequest request,HttpServletResponse response) {
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
        if (data == null || "".equals(data))    return ResultResponse.makeErrRsp("必须注明");

        sheetData = CommonUtil.getData(sheetData, JSONObject.parseObject(data));

        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        try{
            Map map = projectService.findAbkRecording(templateId);
            if (map == null){
                return ResultResponse.makeErrRsp("cache----目标模板不存在");
            }
            String fileName = map.get("template_file").toString();
            String importFilePath = ExcelUtil.getExcelFileName(fileName);
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String time = df.format(new Date());    // new Date()为获取当前系统时间
            fileName = time +"_"+fileName;
            String exportFilePath = ExcelUtil.exportExcelFileName(fileName);

            if (!"success".equals(ExcelUtil.exportExcel(importFilePath,exportFilePath,null,sheetData))){
                return ResultResponse.makeErrRsp("cache----模板数据写入失败");
            }
            Map param = new HashMap();
            param.put("group_name",groupSubdomain);
            param.put("sa_prefix",groupSAPrefix);
            param.put("template_id",templateId);
            param.put("file_name",fileName);
            param.put("instance_url","url");
            param.put("instance_status",0);

            Map result = projectService.abkUCache(param);
            Map out = new HashMap();
            String instance_id = "";
            String instance_url = "";
            if (result == null){
                Map cacheMap = projectService.findAbkUCache(param);
                instance_url = cacheMap.get("instance_url").toString();
                instance_id = cacheMap.get("instance_id").toString();
            }else {
                instance_url = result.get("instance_url").toString();
                instance_id = result.get("instance_id").toString();
            }
            out.put("instance_id",instance_id);
            out.put("instance_url",instance_url);
            return ResultResponse.makeOKRsp(out);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.makeErrRsp("cache----文件下载失败");
        }
    }

    @RequestMapping("/findCache")
    @ResponseBody
    public MessageResult findCache(HttpServletRequest request) {
        // 接收参数
        String groupSubdomain = request.getParameter("groupSubdomain");
        String groupSAPrefix = request.getParameter("groupSAPrefix");
        String templateId = request.getParameter("templateId");
        String data = request.getParameter("data");

        // 入参检查 入参必须项检查
        if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");
        if (data == null || "".equals(data))    return ResultResponse.makeErrRsp("必须注明");
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        try {
            Map param = new HashMap();
            param.put("group_name",groupSubdomain);
            param.put("sa_prefix",groupSAPrefix);
            param.put("template_id",templateId);
            Map map = projectService.findAbkUCache(param);
            if (map == null){
                return ResultResponse.makeErrRsp("findCache----目标模板不存在");
            }
            Map out = new HashMap();
            out.put("instance_id",map.get("instance_id"));
            out.put("instance_url",map.get("instance_url"));
            return ResultResponse.makeOKRsp(out);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.makeErrRsp("findCache----查询缓存失败");
        }
    }

    @RequestMapping("/findCacheStatus")
    @ResponseBody
    public MessageResult findCacheStatus(HttpServletRequest request) {
        // 接收参数
        String groupSubdomain = request.getParameter("groupSubdomain");
        String groupSAPrefix = request.getParameter("groupSAPrefix");
        String templateId = request.getParameter("templateId");
        String instanceId = request.getParameter("instanceId");

        // 入参检查 入参必须项检查
        if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");
        if (instanceId == null || "".equals(instanceId))    return ResultResponse.makeErrRsp("必须注明");
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        try {
            Map out = new HashMap();
            out.put("instance_id","1");
            out.put("instance_url","2");
            return ResultResponse.makeOKRsp(out);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.makeErrRsp("findCache----查询缓存失败");
        }
    }

}
