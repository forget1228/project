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


    @PostConstruct
    public void initialization() {
        //sms server
        FILE_PATH = _environment.getProperty("file.path");
    }
}
