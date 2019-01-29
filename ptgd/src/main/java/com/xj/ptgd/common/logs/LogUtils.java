package com.xj.ptgd.common.logs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


 /**
  * LogUtils 本地日志参考类
  * @author wkm
  * @since 2018/7/31
  */
public class LogUtils {

    /**
     * 获取info日志logger
     *
     * @return
     */
    public static Logger getInfoLogger() {
        return LoggerFactory.getLogger(LogEnum.INFO.getCategory());
    }

    /**
     * 获取异常日志logger
     *
     * @return
     */
    public static Logger getExceptionLogger() {
        return LoggerFactory.getLogger(LogEnum.EXCEPTION.getCategory());
    }



    /**
     * 获取Http日志logger
     *
     * @return
     */
    public static Logger getHttpXMLLogger() {
        return LoggerFactory.getLogger(LogEnum.HTTPXML.getCategory());
    }

    /**
     * 获取MAC日志logger
     *
     * @return
     */
    public static Logger getMacLogger() {
        return LoggerFactory.getLogger(LogEnum.MAC.getCategory());
    }

 }
