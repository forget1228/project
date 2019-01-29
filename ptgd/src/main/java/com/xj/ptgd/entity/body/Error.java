package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;

public class Error extends BaseDto {
    Integer ErrorCode;
    String ErrorInfo;

    public Integer getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(Integer errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorInfo() {
        return ErrorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        ErrorInfo = errorInfo;
    }
}
