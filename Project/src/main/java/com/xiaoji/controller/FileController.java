package com.xiaoji.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoji.model.SheetData;
import com.xiaoji.model.TestData;
import com.xiaoji.service.ProjectService;
import com.xiaoji.util.CommonUtil;
import com.xiaoji.util.ExcelUtil;
import com.xiaoji.util.MessageResult;
import com.xiaoji.util.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@CrossOrigin
@RequestMapping("/project")
public class FileController {
    @Autowired
    private ProjectService projectService;

    Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "/download",method = RequestMethod.POST)
    @ResponseBody
    public void download(@RequestBody String str,HttpServletResponse response) {
        // 解析json数据
        JSONObject json = JSON.parseObject(str);

        // 接收参数
        String groupSubdomain = json.getString("groupSubdomain");
        String groupSAPrefix = json.getString("groupSAPrefix");
        String abkId = json.getString("templateId");
        String data = json.getString("data");

        // 入参检查 入参必须项检查
        /*if (groupSubdomain == null || "".equals(groupSubdomain))    throw new RuntimeException("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    throw new RuntimeException("必须注明");
        if (templateId == null || "".equals(templateId))    throw new RuntimeException("必须注明");
        if (data == null || "".equals(data))    throw new RuntimeException("必须注明");*/
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查
        try{
            JSONArray jsonArray = (JSONArray) JSONArray.parse(data);
            SheetData[] sheetData = new SheetData[jsonArray.size()];
            sheetData = CommonUtil.getExcelData(jsonArray, sheetData);

            Map map = projectService.findByAbkId(abkId);
            if(map == null){
                throw new RuntimeException("模板不存在");
            }

            String fileName = map.get("template_file").toString();
            String importFilePath = ExcelUtil.getExcelFileName(fileName);

            ExcelUtil.excel(importFilePath, sheetData);
            ExcelUtil.outputExcel(fileName, response);

        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof ConcurrentModificationException){
                throw new RuntimeException("模板读写错误");
            }
            throw new RuntimeException("异常抛出");
        }finally {

        }
    }

    @RequestMapping(value = "/downloadCache",method = RequestMethod.POST)
    @ResponseBody
    public void downloadCache(@RequestBody String str,HttpServletResponse response) throws Exception {
        // 解析json数据
        JSONObject json = JSON.parseObject(str);

        // 接收参数
        String groupSubdomain = json.getString("groupSubdomain");
        String groupSAPrefix = json.getString("groupSAPrefix");
        String templateId = json.getString("templateId");
        String instanceId = json.getString("instanceId");

        // 入参检查 入参必须项检查
        /*if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");
        if (instanceId == null || "".equals(instanceId))    return ResultResponse.makeErrRsp("必须注明");*/
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
                //return ResultResponse.makeErrRsp("findCache----模板缓存不存在");
            }
            String fileName = map.get("template_file").toString();
            ExcelUtil.outputExcel(fileName, response);

        }catch (Exception e){
            e.printStackTrace();
            //return ResultResponse.makeErrRsp("findCache----查询缓存失败");
        }
    }

    @RequestMapping(value = "/downloadMany",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult downloadMany(@RequestBody String str,HttpServletResponse response) {
        // 解析json数据
        JSONObject json = JSON.parseObject(str);

        // 接收参数
        String groupSubdomain = json.getString("groupSubdomain");
        String groupSAPrefix = json.getString("groupSAPrefix");
        String templateId = json.getString("templateId");
        String instanceId = json.getString("instanceId");

        // 入参检查 入参必须项检查
        if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (instanceId == null || "".equals(instanceId))    return ResultResponse.makeErrRsp("必须注明");
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        try {

            return ResultResponse.makeOKRsp();
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.makeErrRsp("findCache----查询缓存失败");
        }
    }

    @RequestMapping(value = "/cache",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult cache(@RequestBody String str) {
        // 解析json数据
        JSONObject json = JSON.parseObject(str);

        // 接收参数
        String groupSubdomain = json.getString("groupSubdomain");
        String groupSAPrefix = json.getString("groupSAPrefix");
        String templateId = json.getString("templateId");
        String data = json.getString("data");

        // 入参检查 入参必须项检查
        if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");
        if (data == null || "".equals(data))    return ResultResponse.makeErrRsp("必须注明");

        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        try{
            JSONArray jsonArray = (JSONArray) JSONArray.parse(data);
            SheetData[] sheetData = new SheetData[jsonArray.size()];
            sheetData = CommonUtil.getExcelData(jsonArray, sheetData);

            Map map = projectService.findByAbkId(templateId);
            if(map == null){
                throw new RuntimeException("模板不存在");
            }

            String fileName = map.get("template_file").toString();
            String importFilePath = ExcelUtil.getExcelFileName(fileName);
            fileName =  fileName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());   // 不重复文件名
            String exportFilePath = ExcelUtil.exportExcelFileName(fileName);

            ExcelUtil.excel(importFilePath, sheetData);
            ExcelUtil.outputFile(exportFilePath);

            Map param = new HashMap();
            param.put("group_name",groupSubdomain);
            param.put("sa_prefix",groupSAPrefix);
            param.put("template_id",templateId);
            param.put("file_name",fileName);
            param.put("instance_url","url");
            param.put("instance_status",0);

            Map out = new HashMap();
            Map result = projectService.abkUCache(param);
            if (result == null){
                Map cacheMap = projectService.findAbkUCache(param);
                out.put("instance_id",cacheMap.get("instance_url").toString());
                out.put("instance_url",cacheMap.get("instance_id").toString());
            }else {
                out.put("instance_id",result.get("instance_url").toString());
                out.put("instance_url",result.get("instance_id").toString());
            }
            return ResultResponse.makeOKRsp(out);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.makeErrRsp("cache----文件下载失败");
        }
    }

    @RequestMapping(value = "/findCache",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult findCache(@RequestBody String str) {
        // 解析json数据
        JSONObject json = JSON.parseObject(str);

        // 接收参数
        String groupSubdomain = json.getString("groupSubdomain");
        String groupSAPrefix = json.getString("groupSAPrefix");
        String templateId = json.getString("templateId");

        // 入参检查 入参必须项检查
        if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");
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
                return ResultResponse.makeErrRsp("findCache----模板缓存不存在");
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
    public void findCacheStatus(@RequestBody String str) {
        // 解析json数据
        JSONObject json = JSON.parseObject(str);

        // 接收参数
        String groupSubdomain = json.getString("groupSubdomain");
        String groupSAPrefix = json.getString("groupSAPrefix");
        String templateId = json.getString("templateId");

        // 入参检查 入参必须项检查
        /*if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");*/
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

    }

}
