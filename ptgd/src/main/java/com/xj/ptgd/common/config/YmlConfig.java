package com.xj.ptgd.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class YmlConfig {
    @Autowired
    private Environment _environment;

    /*文件上传路径*/
    public static String UP_FILE_PATH;
    public static String DOWNLOAD_FILE_PATH;

    /* Mac加密包路径 */
    public static String MAC_PAW_PATH;

    /* 交易接口地址 */
    public static String TRANSACTION_URL;

    /* 反欺诈手机评分  */
    public static String RAIN_SCORE_URL;

    /* 自动风控规则 */
    public static String UNDER_TAKER_URL;

    /*  shell 地址 */
    public static String SHELL_FILE_URL;

    public static String SHELL_FILE_NAME = "gd_file_download.sh";

    @PostConstruct
    public void initialization() {
        //sms server
        UP_FILE_PATH = _environment.getProperty("fileConfig.upFilePath");
        DOWNLOAD_FILE_PATH = _environment.getProperty("fileConfig.downloadFilePath");
        MAC_PAW_PATH = _environment.getProperty("mac.paw.path");
        TRANSACTION_URL = _environment.getProperty("transaction.url");
        RAIN_SCORE_URL = _environment.getProperty("rainScore.url");
        UNDER_TAKER_URL = _environment.getProperty("underTaker.url");
        SHELL_FILE_URL = _environment.getProperty("gdFileDownload.url");

    }
    /* 项目编号 */
    public static String PROJECT_NO = "TP2018092100000001";
    /* 合作机构编号 */
    public static String COOPERATION_NO = "2018092100000002";
    /* 增信产品号 */
    public static String BUSINESS_TYPE = "BT2018092600000001";

    /* 编码格式 */
    public static String SYS_CHARSET = "GB18030";
}
