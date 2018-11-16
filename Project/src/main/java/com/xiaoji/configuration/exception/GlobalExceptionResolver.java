package com.xiaoji.configuration.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xiaoji.configuration.WebMvcConfig;
import com.xiaoji.util.MessageResult;
import com.xiaoji.util.ResultCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionResolver {
    private final static Logger LOGGER =  LogManager.getLogger(WebMvcConfig.class.getName());

    /**
     * 业务异常的处理
     */
    @ExceptionHandler(value = ServiceException.class)
    public void serviceExceptionHandler(HttpServletResponse response, ServiceException e) {
        MessageResult<Object> result = new MessageResult<>();
        result.setCode(ResultCode.FAIL).setMsg(e.getMessage()).setData(null);
        responseResult(response, result);
    }

    /**
     * 其他异常统一处理
     */
    @ExceptionHandler(value = Exception.class)
    public void exceptionHandler(HttpServletResponse response, Exception e) {
        MessageResult<Object> result = new MessageResult<>();
        result.setCode(ResultCode.INTERNAL_SERVER_ERROR).setMsg("服务器打酱油了，请稍后再试~");
        LOGGER.error(e.getMessage(), e);
        responseResult(response, result);
    }

    /**
     * @param response
     * @param result
     * @Title: responseResult
     * @Description: 响应结果
     * @Reutrn void
     */
    private void responseResult(HttpServletResponse response, MessageResult<Object> result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
