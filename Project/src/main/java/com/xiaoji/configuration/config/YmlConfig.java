package com.xiaoji.configuration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class YmlConfig {
    @Autowired
    private Environment _environment;

    /* 保存地址路径 */
    public static String FILE_PATH;

    /* abl 地址 */
    public static String ABL_PATH;


    @PostConstruct
    public void initialization() {
        //sms server
        FILE_PATH = _environment.getProperty("file.path");
        ABL_PATH  = _environment.getProperty("abl.path");
    }
}
