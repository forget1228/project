package com.xiaoji.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoji.ExportExcelDemo;
import com.xiaoji.model.SheetData;
import com.xiaoji.service.HttpService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xiaoji.configuration.config.YmlConfig.ABL_PATH;

@Controller
@CrossOrigin
public class ProjectController {
    @Autowired
    private HttpService httpauth;

    @Autowired
    private ProjectService projectService;

    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @RequestMapping(value = "/init",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult init(@RequestParam("file") MultipartFile file,
                              @RequestParam(value="saPrefix", required=true) String saPrefix,
                              @RequestParam(value="templateClassName", required=true) String templateClassName,
                              @RequestParam(value="templateId", required=true) String templateId,
                              @RequestParam(value="group", required=true) String group) {
        // 接收参数
        // 入参检查 入参必须项检查
        /*if (saPrefix == null || "".equals(saPrefix)){ return ResultResponse.makeErrRsp("来源必须注明"); }
        if (templateId == null || "".equals(templateId))    throw new RuntimeException("必须注明");*/
        if(file.isEmpty() || file.getSize() == 0 )    return ResultResponse.makeErrRsp("文件 ‘"+ file.getOriginalFilename() +"’ 无数据");
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        /*String host = request.getHeader("x-forwarded-host");
        String subDomain = host.substring(0, host.indexOf('.'));*/
        String subDomain = null;
        String groupName = subDomain == null ? "www" : subDomain;

        String fileName = file.getOriginalFilename();
        String name = null;
        String hou = null;
        String fileType = null;
        name = fileName.substring( 0,fileName.indexOf("."));
        hou = fileName.substring( fileName.indexOf(".")+1,fileName.length()).toLowerCase();

        if ("xlsx".equals(hou) || "xls".equals(hou)){
            fileType = "excel";
        }

        // 参数值入库
        Map<String,Object> map = new HashMap<>();
        map.put("group_name",groupName);
        map.put("is_public","0");
        map.put("sa_prefix", saPrefix == null ? "abk":saPrefix);
        map.put("template_id",templateId);
        map.put("template_name",name);
        map.put("template_class",templateClassName);
        map.put("is_old",0);
        map.put("document_name",fileName);
        map.put("document_type",fileType);
        try {
            String path = ExcelUtil.getExcelFileName(fileName);
            File dest = new File(path);
            if (dest.exists()){
                logger.info("文件保存一份旧文件");
                File nf = new File(ExcelUtil.getExcelFileName("old_" + fileName));
                if (nf.exists()) nf.delete();
                dest.renameTo(nf); // 修改文件名
                map.put("is_old",1);
            }
            file.transferTo(dest); // 保存文件

            // 文件持久化
            Map<String, String> data = new HashMap<>();
            data.put("saPrefix",saPrefix);
            data.put("group","www");
            data.put("storeType","");
            data.put("username","abk");

            Map<String, Object> res = httpauth.uploadFile(dest,data, ABL_PATH);

            map.put("abl_id",/*res.get("data")*/ 1 );

            projectService.abkRecording(map);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.makeErrRsp("初始化失败");
        }
        return ResultResponse.makeOKRsp();
    }

    @RequestMapping(value = "/initialize",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult initialize(HttpServletRequest request) {
        // 接收参数
        String saPrefix = request.getParameter("saPrefix");
        String templateClassName = request.getParameter("templateClassName");
        String templateId = request.getParameter("templateId");
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("templateFile");
        if (files.size()>=2 || files.size()<= 0)     return ResultResponse.makeErrRsp("请上传一个文件");

        // 入参检查 入参必须项检查
        /*if (saPrefix == null || "".equals(saPrefix)){ return ResultResponse.makeErrRsp("来源必须注明"); }
        if (templateId == null || "".equals(templateId))    throw new RuntimeException("必须注明");*/
        MultipartFile file = null;
        file = files.get(0);
        if(file.isEmpty() || file.getSize() == 0 )    return ResultResponse.makeErrRsp("文件 ‘"+ file.getOriginalFilename() +"’ 无数据");
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查

        /*String host = request.getHeader("x-forwarded-host");
        String subDomain = host.substring(0, host.indexOf('.'));*/
        String subDomain = null;
        String groupName = subDomain == null ? "www" : subDomain;

        String fileName = file.getOriginalFilename();
        String name = null;
        String hou = null;
        String fileType = null;
        name = fileName.substring( 0,fileName.indexOf("."));
        hou = fileName.substring( fileName.indexOf(".")+1,fileName.length()).toLowerCase();

        if ("xlsx".equals(hou) || "xls".equals(hou)){
            fileType = "excel";
        }

        // 参数值入库
        Map<String,Object> map = new HashMap<>();
        map.put("group_name",groupName);
        map.put("is_public","0");
        map.put("sa_prefix", saPrefix == null ? "abk":saPrefix);
        map.put("template_id",templateId);
        map.put("template_name",name);
        map.put("template_class",templateClassName);
        map.put("is_old",0);
        map.put("document_name",fileName);
        map.put("document_type",fileType);
        try {
            String path = ExcelUtil.getExcelFileName(fileName);
            File dest = new File(path);
            if (dest.exists()){
                logger.info("文件保存一份旧文件");
                File nf = new File(ExcelUtil.getExcelFileName("old_" + fileName));
                if (nf.exists()) nf.delete();
                dest.renameTo(nf); // 修改文件名
                map.put("is_old",1);
            }
            file.transferTo(dest); // 保存文件

            // 文件持久化
            Map<String, String> data = new HashMap<>();
            data.put("saPrefix",saPrefix);
            data.put("group","www");
            data.put("storeType","");
            data.put("username","abk");

            Map<String, Object> res = httpauth.uploadFile(dest,data, ABL_PATH);

            map.put("abl_id",res.get("data"));

            projectService.abkRecording(map);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.makeErrRsp("初始化失败");
        }
        return ResultResponse.makeOKRsp();
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult upload(HttpServletRequest request) {
        return ResultResponse.makeOKRsp();
    }

    @RequestMapping(value = "/find",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult find(HttpServletRequest request) {
        /*String host = req.getHeader("x-forwarded-host");
        String subDomain = host.substring(0, host.indexOf('.'));*/
        String subDomain = null;
        String groupName = subDomain == null ? "www" : subDomain;
        try {
            return ResultResponse.makeOKRsp(projectService.findAll(groupName));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.makeErrRsp("查询失败");
        }
    }

    @RequestMapping("/template")
    @ResponseBody
    public void template(HttpServletRequest request,HttpServletResponse response) {
        // 接收参数
        String abkId = request.getParameter("abkId");

        // 入参检查 入参必须项检查
        // 入参类型检查
        // 入参长度检查
        // 入参关联检查
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        OutputStream os = null;
        InputStream is = null;
        try{
            Map map = projectService.findByAbkId(abkId);
            if(map == null){
                throw new RuntimeException("模板不存在");
            }

            String fileName = map.get("document_name").toString();
            String importFilePath = ExcelUtil.getExcelFileName(fileName);

            System.out.println(importFilePath);

            File file = new File(importFilePath);

            if (!file.exists()){
                logger.info("文件不存在");
            }
            response.setContentType("application/vnd.ms-excel");
            fileName = new String(fileName.getBytes(),"ISO8859-1");
            response.setHeader("Content-disposition","attachment; filename=" + fileName);
            is = new FileInputStream(file);
            bis = new BufferedInputStream(is);
            os = response.getOutputStream();
            bos = new BufferedOutputStream(os);
            int bytesRead = 0;
            byte[] b = new byte[ 5 * 1024 ];
            while ((bytesRead = bis.read(b))!=-1){
                bos.write(b,0,bytesRead);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("异常抛出");
        }finally {
            try {
                if (is != null){
                    is.close();
                }
                if (os != null){
                    os.close();
                }
                if (bos != null){
                    bos.close();
                }
                if (bis != null){
                    bis.close();
                }
            }catch (Exception e){

            }

        }
    }





    @RequestMapping(value = "/fileTest",method = RequestMethod.POST)
    @ResponseBody
    public void fileTest(@RequestParam("file") MultipartFile file,@RequestParam(value="group", required=true) String group) {
        System.out.println("lll");
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public void test(HttpServletRequest request,HttpServletResponse response) {

    }
}
