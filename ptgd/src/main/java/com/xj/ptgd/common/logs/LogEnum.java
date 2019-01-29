package com.xj.ptgd.common.logs;

 /**
  * LogEnum 本地日志枚举
  * @author wkm
  * @since 2018/7/31
  */
public enum LogEnum {

    INFO("info"),
    HTTPXML("httpXML"),
    MAC("mac"),
    EXCEPTION("exception"),;


    private String category;


    LogEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
