package com.xiaoji.controller;

import com.xiaoji.configuration.config.YmlConfig;
import com.xiaoji.model.SheetData;
import com.xiaoji.service.ProjectService;
import com.xiaoji.util.ExcelUtil;
import com.xiaoji.util.MessageResult;
import com.xiaoji.util.ResultResponse;
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
import java.io.*;
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

    @RequestMapping("/initialize")
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

        Map<String,Object> map = new HashMap<>();
        map.put("sa_prefix",saPrefix);
        map.put("template_class_name",templateClassName);
        map.put("template_id",templateId);
        map.put("template_name",templateName);
        map.put("template_generator",templateGenerator);

        String fileName = file.getOriginalFilename();
        String path = YmlConfig.FILE_PATH ;
        // 检测目标路径是否存在，不存在则创建
        File dest = new File(path + fileName);
        if(!dest.exists() && !dest.isDirectory()){  //判断文件父目录是否存在
            dest.getParentFile().mkdir();
            logger.info("创建导出文件目录");
        }
        try {
            file.transferTo(dest); //保存文件
            logger.info("保存路径" + path );
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        map.put("template_file",path);
        map.put("template_type","初始化");
        //projectService.insert(map);
        /*if (templateId == null || "".equals(templateId)) {

        }else if (dest.exists()){
            logger.info("文件已存在");
            map.put("template_type","修改");
            projectService.update(map);
        }*/
        List<Map<String,Object>> list = null;
        return ResultResponse.makeOKRsp(list);
    }

    @RequestMapping("/update")
    @ResponseBody
    public MessageResult update(HttpServletRequest request) {
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

        Map<String,Object> map = new HashMap<>();
        map.put("sa_prefix",saPrefix);
        map.put("template_class_name",templateClassName);
        map.put("template_id",templateId);
        map.put("template_name",templateName);
        map.put("template_generator",templateGenerator);

        String fileName = file.getOriginalFilename();
        String path = YmlConfig.FILE_PATH ;
        // 检测目标路径是否存在，不存在则创建
        File dest = new File(path + fileName);
        if(!dest.exists() && !dest.isDirectory()){  //判断文件父目录是否存在
            dest.getParentFile().mkdir();
            logger.info("创建导出文件目录");
        }
        try {
            file.transferTo(dest); //保存文件
            logger.info("保存路径" + path );
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        map.put("template_file",path);
        map.put("template_type","修改");
        try {
            projectService.update(map);
        }catch (Exception e){
            e.printStackTrace();
            return ResultResponse.makeErrRsp("更新失败");
        }
        return ResultResponse.makeOKRsp();
    }


    @RequestMapping("/upload")
    public void upload(HttpServletRequest request,HttpServletResponse response) {
        // 接收参数
        String groupSubdomain = request.getParameter("groupSubdomain");
        String groupSAPrefix = request.getParameter("groupSAPrefix");
        String templateId = request.getParameter("templateId");
        String data = request.getParameter("data");
        // 入参检查 入参必须项检查
        /*if (groupSubdomain == null || "".equals(groupSubdomain))    return ResultResponse.makeErrRsp("必须注明");
        if (groupSAPrefix == null || "".equals(groupSAPrefix))    return ResultResponse.makeErrRsp("必须注明");
        if (templateId == null || "".equals(templateId))    return ResultResponse.makeErrRsp("必须注明");
        if (data == null || "".equals(data))    return ResultResponse.makeErrRsp("必须注明");*/
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

    }
}
