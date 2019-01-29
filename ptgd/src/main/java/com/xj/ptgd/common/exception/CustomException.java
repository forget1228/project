package com.xj.ptgd.common.exception;


 /**
  * CustomException
  * @author wkm
  * @since 2018/7/30
  */
public class CustomException extends RuntimeException{
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
