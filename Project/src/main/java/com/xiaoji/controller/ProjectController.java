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
import java.text.SimpleDateFormat;
import java.util.Date;
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

        return ResultResponse.makeOKRsp();
    }

    @RequestMapping(value = "/initialize",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult initialize(HttpServletRequest request) {
        // 接收参数
        String saPrefix = request.getParameter("saPrefix") == null ? "abk": request.getParameter("saPrefix");
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

        String host = request.getHeader("x-forwarded-host");
        logger.info("init   host = "+host);
        String subDomain =  host == null ? "www" : host.substring(0, host.indexOf('.'));

        String ablId = null;
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
        map.put("group_name",subDomain);
        map.put("sa_prefix", saPrefix);
        map.put("template_id",templateId);
        map.put("template_name",name);
        map.put("template_class",templateClassName);
        map.put("document_name",fileName);
        name = name + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +"."+ hou;
        map.put("file_name",name);
        map.put("document_type",fileType);
        try {
            Map init = projectService.findByInit(map);
            if (init == null){
                String path = ExcelUtil.getExcelFileName(name);     // 不重复文件名
                File dest = new File(path);
                file.transferTo(dest); // 保存文件
                // 文件持久化
                Map<String, Object> res = uploadFile(saPrefix,subDomain,"","abk",dest);
                ablId = res == null ? "":res.get("data").toString();
                // 绑定数据
                map.put("abl_id",ablId);
                map.put("is_old",0);
                map.put("old_abl_id",null);
                projectService.insertAbkRecording(map);
            }else {
                logger.info("文件保存一份旧文件");
                name = init.get("file_name").toString();
                String path = ExcelUtil.getExcelFileName(name);     // 模板文件
                File dest = new File(path);
                File nf = new File(ExcelUtil.getExcelFileName("old_" + name));
                if (nf.exists()) nf.delete();
                dest.renameTo(nf); // 修改文件名(旧文件)
                file.transferTo(dest); // 保存文件
                // 文件持久化
                Map<String, Object> res = uploadFile(saPrefix,subDomain,"","abk",dest);
                ablId = res == null ? "":res.get("data").toString();
                // 绑定数据
                map.put("abl_id",ablId);
                map.put("is_old",1);
                map.put("old_abl_id",init.get("abl_id") == null ? null:init.get("abl_id").toString());
                map.put("abk_id",init.get("abk_id") == null ? null:init.get("abk_id").toString());

                projectService.updateAbkRecording(map);
            }
            logger.info("模板入库成功:"+ name);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.makeErrRsp("初始化失败");
        }
        return ResultResponse.makeOKRsp();
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult upload(HttpServletRequest request) {
        String host = request.getHeader("x-forwarded-host");
        logger.info("upload   host = "+host);
        String subDomain =  host == null ? "xiaoji" : host.substring(0, host.indexOf('.'));
        // 接收参数
        String saPrefix = request.getParameter("saPrefix") == null ? "abk": request.getParameter("saPrefix");
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

        String ablId = null;
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
        map.put("group_name",subDomain);
        map.put("sa_prefix", saPrefix);
        map.put("template_id",templateId);
        map.put("template_name",name);
        map.put("template_class",templateClassName);
        map.put("document_name",fileName);
        name = name + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +"."+ hou;
        map.put("file_name",name);
        map.put("document_type",fileType);
        try {
            Map upload = projectService.findByInit(map);
            if (upload == null){
                String path = ExcelUtil.getExcelFileName(name);     // 不重复文件名
                File dest = new File(path);
                file.transferTo(dest); // 保存文件
                // 文件持久化
                Map<String, Object> res = uploadFile(saPrefix,subDomain,"","abk",dest);
                ablId = res == null ? "":res.get("data").toString();
                // 绑定数据
                map.put("abl_id",ablId);
                map.put("is_old",0);
                map.put("old_abl_id",null);
                projectService.insertAbkRecording(map);
            }else {
                logger.info("文件保存一份旧文件");
                name = upload.get("file_name").toString();
                String path = ExcelUtil.getExcelFileName(name);     // 模板文件
                File dest = new File(path);
                File nf = new File(ExcelUtil.getExcelFileName("old_" + name));
                if (nf.exists()) nf.delete();
                dest.renameTo(nf); // 修改文件名(旧文件)
                file.transferTo(dest); // 保存文件
                // 文件持久化
                Map<String, Object> res = uploadFile(saPrefix,subDomain,"","abk",dest);
                ablId = res == null ? "":res.get("data").toString();
                // 绑定数据
                map.put("abl_id",ablId);
                map.put("is_old",1);
                map.put("old_abl_id",upload.get("abl_id") == null ? null:upload.get("abl_id").toString());
                map.put("abk_id",upload.get("abk_id") == null ? null:upload.get("abk_id").toString());

                projectService.updateAbkRecording(map);
            }
            logger.info(subDomain + "更新成功:"+ name);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultResponse.makeErrRsp("更新失败");
        }
        return ResultResponse.makeOKRsp();
    }

    @RequestMapping(value = "/find",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult find(HttpServletRequest request) {
        String host = request.getHeader("x-forwarded-host");
        logger.info("find   host = "+host);
        String subDomain =  host == null ? "www" : host.substring(0, host.indexOf('.'));
        subDomain = "xiaoji";
        try {
            return ResultResponse.makeOKRsp(projectService.findAll(subDomain));
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


    public Map uploadFile(String saPrefix,String subDomain,String storeType,String userName,File dest){
        // 文件持久化
        Map<String, String> data = new HashMap<>();
        data.put("saPrefix",saPrefix);
        data.put("group",subDomain);
        data.put("storeType",storeType);
        data.put("username",userName);
        try{
            return httpauth.uploadFile(dest,data, ABL_PATH);
        }catch (Exception e){
            System.out.println("文件持久化失败");
            return null;
        }
    }
}
