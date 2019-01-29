package com.xj.ptgd.common.exception;


import com.xj.ptgd.common.result.ResultEnum;

/**
  * ResultException
  * @author cp
  * @since 2018/8/14
  */
public class ResultException extends RuntimeException{
     private Integer code;

     public ResultException(ResultEnum resultEnum,String msg){
         super(msg);
         this.code = resultEnum.getCode();
     }

     public Integer getCode() {
         return code;
     }

     public void setCode(Integer code) {
         this.code = code;
     }
 }
