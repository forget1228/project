package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;

public class BodyEntityNc extends BaseDto{
    private String RESULT_FLAG;      // RESULT_FLAG	成功标志	VARCHAR2(1)	Y	0：失败 1：成功
    private String MSG;         // MESSAGE	结果信息	VARCHAR2(200)	N	失败时有错误信息
    private String ATTRIBUTE11;      // ATTRIBUTE11	备用字段11	VARCHAR2(100)	N
    private String ATTRIBUTE12;      // ATTRIBUTE12	备用字段12	VARCHAR2(100)	N

    public String getRESULT_FLAG() {
        return RESULT_FLAG;
    }

    public void setRESULT_FLAG(String RESULT_FLAG) {
        this.RESULT_FLAG = RESULT_FLAG;
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public String getATTRIBUTE11() {
        return ATTRIBUTE11;
    }

    public void setATTRIBUTE11(String ATTRIBUTE11) {
        this.ATTRIBUTE11 = ATTRIBUTE11;
    }

    public String getATTRIBUTE12() {
        return ATTRIBUTE12;
    }

    public void setATTRIBUTE12(String ATTRIBUTE12) {
        this.ATTRIBUTE12 = ATTRIBUTE12;
    }
}
