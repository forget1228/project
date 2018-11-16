package com.xiaoji.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xiaoji.util.MessageResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PMInterceptor implements HandlerInterceptor {
    final Log logger = LogFactory.getLog(this.getClass());
    @Override
    public synchronized boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("PMInterceptor ********* 启动拦截器");
        String servletPath = request.getServletPath();
        logger.info("servletPath ********* : " + servletPath);
        return true;
    }
    private void responseResult(HttpServletResponse response, MessageResult<Object> result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
