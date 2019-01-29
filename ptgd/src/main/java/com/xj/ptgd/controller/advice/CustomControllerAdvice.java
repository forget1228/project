package com.xj.ptgd.controller.advice;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.common.util.MacUtil;
import com.xj.ptgd.entity.body.Error;
import com.xj.ptgd.entity.out.*;
import com.xj.ptgd.entity.base.XMLHeadDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
  * CustomControllerAdvice Controller自定义处理
  * @author wkm
  * @since 2018/7/30
  */
@ControllerAdvice
public class CustomControllerAdvice {
     /**
      * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
      * @param binder
      */
     @InitBinder
     public void initBinder(WebDataBinder binder) {}

     /**
      * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
      * @param model
      */
     @ModelAttribute
     public void addAttributes(Model model) {
//         model.addAttribute("author", "Magical Sam");
     }

     /**
      * 全局异常捕捉处理
      * @param ex
      * @return
      */
     @ResponseBody
     @ExceptionHandler(value = Exception.class)
     public String errorHandler(Exception ex) {
         ErrorXMLOut errorXMLOut = new ErrorXMLOut();
         XMLHeadDto headDto = new XMLHeadDto();
         Error error = new Error();
         error.setErrorCode(ResultEnum.http_status_internal_server_error.getCode());
         error.setErrorInfo(ResultEnum.http_status_internal_server_error.getMsg());
         errorXMLOut.setHead(headDto);
         ex.printStackTrace();
         if (ex instanceof ResultException) {  // 自定义运行时异常，必须要处理
             ResultException re = (ResultException) ex;
             error.setErrorCode(re.getCode());
             error.setErrorInfo(re.getMessage());
             errorXMLOut.setBody(error);
             errorXMLOut.setHead(headDto);
             String retString = MacUtil.addLen(MacUtil.addMac(ResultUtil.getResult(errorXMLOut,ErrorXMLOut.class)));
             System.out.println("********************** start *********************************************");
             System.out.println("应答时间："+new Date().toString()+"\n接口返回报文信息："+retString);
             System.out.println("************************  end ******************************************");
             return  retString;
         }
		
		 if (ex instanceof RuntimeException) {
             String retString = MacUtil.addLen(MacUtil.addMac(ResultUtil.getResult(errorXMLOut,ErrorXMLOut.class)));
             System.out.println("********************** start *********************************************");
             System.out.println("应答时间："+new Date().toString()+"\n接口返回报文信息："+retString);
             System.out.println("************************  end ******************************************");
             return  retString;
         }else{
             String retString = MacUtil.addLen(MacUtil.addMac(ResultUtil.getResult(errorXMLOut,ErrorXMLOut.class)));
             System.out.println("********************** start *********************************************");
             System.out.println("应答时间："+new Date().toString()+"\n接口返回报文信息："+retString);
             System.out.println("************************  end ******************************************");
             return  retString;
         }
     }
}
