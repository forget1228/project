package com.xiaoji.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaoji.model.BbgTax;
import com.xiaoji.model.BbgWage;
import com.xiaoji.service.ProjectService;
import com.xiaoji.util.MessageResult;
import com.xiaoji.util.ReaderExcel;
import com.xiaoji.util.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/projectmap")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @RequestMapping("/findProject")
    @ResponseBody
    public MessageResult<List<Map<String,Object>>> findProject(@RequestBody JSONObject obj) {
        //logger.info("data:"+obj.toJSONString());    // data:{"date":"2018-08"}
        String data=obj.toJSONString();
        //解析json数据
        JSONObject json = JSON.parseObject(data);
        String time=json.getString("date");
        if ("".equals(time) || time == null ||"null".equals(time))    return ResultResponse.makeErrRsp("请选择时间");
        logger.info(time);
        List<Map<String,Object>> list = projectService.findBbgByDate(time);
        return ResultResponse.makeOKRsp(list);
    }

    @RequestMapping(value = "/addProject",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult<List<Object>> add(HttpServletRequest request){
        String str = "";
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
        if (files.size()>2 || files.size()<= 0)     return ResultResponse.makeErrRsp("请选择两个 execl 类型文件");
        String time = request.getParameter("time");
        if ("".equals(time) || time == null ||"null".equals(time))    return ResultResponse.makeErrRsp("请选择时间");

        String version = request.getParameter("switch");
        if ("false".equals(version)) version = "1"; else  version= "0";
        MultipartFile file = null;
        for (MultipartFile f:files) {
            file = f;
            String fileName = file.getOriginalFilename();
            if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$"))
                return ResultResponse.makeErrRsp("上传文件格式错误，请上传后缀为.xls或.xlsx的文件");

            if(file.isEmpty() || file.getSize() == 0 )    return ResultResponse.makeErrRsp( fileName+"无数据");
        }
        MultipartFile file1,file2 = null;
        file1 = files.get(0);
        file2 = files.get(1);

        logger.info("time="+time+"\t version="+version);
        InputStream in = null;

        try {
            // 调用导入工具类ImportExcelUtil，把excel中的数据拿出来
            in = file1.getInputStream();
            MessageResult<List<BbgWage>> wageExcel = new ReaderExcel().getWageExcel(in, file1.getOriginalFilename());
            in.close();
            if (!"success".equals(wageExcel.getMsg())) return ResultResponse.makeErrRsp(wageExcel.getMsg());

            // 第二张表
            in = file2.getInputStream();
            MessageResult<List<BbgTax>> taxExcel = new ReaderExcel().getTaxExcel(in, file2.getOriginalFilename());
            in.close();
            if (!"success".equals(taxExcel.getMsg())) return ResultResponse.makeErrRsp(taxExcel.getMsg());

            str = projectService.insertBbg(wageExcel.getData(),taxExcel.getData(),time,version);

            if (!"success".equals(str))  return ResultResponse.makeErrRsp(str);
        }catch (Exception ex){
            ex.printStackTrace();
            return ResultResponse.makeErrRsp("文件上传失败");
        }
        return ResultResponse.makeOKRsp();
    }
}
