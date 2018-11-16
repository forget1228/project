package com.xiaoji.util;


public class ResultResponse {
    private final static String SUCCESS = "success";

    public static <T> MessageResult<T> makeOKRsp() {
        return new MessageResult<T>().setCode(ResultCode.SUCCESS).setMsg(SUCCESS);
    }

    public static <T> MessageResult<T> makeOKRsp(T data) {
        return new MessageResult<T>().setCode(ResultCode.SUCCESS).setMsg(SUCCESS).setData(data);
    }

    public static <T> MessageResult<T> makeErrRsp(String message) {
        return new MessageResult<T>().setCode(ResultCode.FAIL).setMsg(message);
    }

    public static <T> MessageResult<T> makeRsp(int code, String msg) {
        return new MessageResult<T>().setCode(code).setMsg(msg);
    }

    public static <T> MessageResult<T> makeRsp(int code, String msg, T data) {
        return new MessageResult<T>().setCode(code).setMsg(msg).setData(data);
    }
}
