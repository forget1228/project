package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;

public class NoticeCs extends BaseDto {
    private String FILE_NAME;       // FILE_NAME	文件名	VARCHAR(200)	Y
    private String FILE_PATH;       // FILE_PATH	文件路径	VARCHAR(200)	Y
    private String CONTRACT_TYPE;   // CONTRACT_TYPE	合同类型	VARCHAR(1)	Y	1-最高质押合同    2-四方协议  3-动产抵押合同    4-信用增信协议
    private String ATTRIBUTE6;      // ATTRIBUTE6	备用字段6	VARCHAR2(100)	N
    private String ATTRIBUTE7;      // ATTRIBUTE7	备用字段7	VARCHAR2(100)	N
    private String ATTRIBUTE8;      // ATTRIBUTE8	备用字段8	VARCHAR2(100)	N
    private String ATTRIBUTE9;      // ATTRIBUTE9	备用字段9	VARCHAR2(100)	N
    private String ATTRIBUTE10;     // ATTRIBUTE10	备用字段10	VARCHAR2(100)	N

    @Override
    public String toString() {
        return "{" +
                "FILE_NAME='" + FILE_NAME + '\'' +
                ", FILE_PATH='" + FILE_PATH + '\'' +
                ", CONTRACT_TYPE='" + CONTRACT_TYPE + '\'' +
                ", ATTRIBUTE6='" + ATTRIBUTE6 + '\'' +
                ", ATTRIBUTE7='" + ATTRIBUTE7 + '\'' +
                ", ATTRIBUTE8='" + ATTRIBUTE8 + '\'' +
                ", ATTRIBUTE9='" + ATTRIBUTE9 + '\'' +
                ", ATTRIBUTE10='" + ATTRIBUTE10 + '\'' +
                '}';
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getFILE_PATH() {
        return FILE_PATH;
    }

    public void setFILE_PATH(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }

    public String getCONTRACT_TYPE() {
        return CONTRACT_TYPE;
    }

    public void setCONTRACT_TYPE(String CONTRACT_TYPE) {
        this.CONTRACT_TYPE = CONTRACT_TYPE;
    }

    public String getATTRIBUTE6() {
        return ATTRIBUTE6;
    }

    public void setATTRIBUTE6(String ATTRIBUTE6) {
        this.ATTRIBUTE6 = ATTRIBUTE6;
    }

    public String getATTRIBUTE7() {
        return ATTRIBUTE7;
    }

    public void setATTRIBUTE7(String ATTRIBUTE7) {
        this.ATTRIBUTE7 = ATTRIBUTE7;
    }

    public String getATTRIBUTE8() {
        return ATTRIBUTE8;
    }

    public void setATTRIBUTE8(String ATTRIBUTE8) {
        this.ATTRIBUTE8 = ATTRIBUTE8;
    }

    public String getATTRIBUTE9() {
        return ATTRIBUTE9;
    }

    public void setATTRIBUTE9(String ATTRIBUTE9) {
        this.ATTRIBUTE9 = ATTRIBUTE9;
    }

    public String getATTRIBUTE10() {
        return ATTRIBUTE10;
    }

    public void setATTRIBUTE10(String ATTRIBUTE10) {
        this.ATTRIBUTE10 = ATTRIBUTE10;
    }
}
