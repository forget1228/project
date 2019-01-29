package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;

/**
 *  3.7 订单取消通知-应答报文统
 *  @author cp  G362
 *  @date 2018/8/2
 */
public class BodyOrderCancel extends BaseDto {
    private String RESULT_FLAG;      // RESULT_FLAG	成功标志	VARCHAR2(1)	Y	0：失败 1：成功
    private String MSG;         // MESSAGE	结果信息	VARCHAR2(200)	N	失败时有错误信息
    private String ATTRIBUTE6;      // ATTRIBUTE6	备用字段6	VARCHAR2(100)	N
    private String ATTRIBUTE7;      // ATTRIBUTE7	备用字段7	VARCHAR2(100)	N
    private String ATTRIBUTE8;      // ATTRIBUTE8	备用字段8	VARCHAR2(100)	N
    private String ATTRIBUTE9;      // ATTRIBUTE9	备用字段9	VARCHAR2(100)	N
    private String ATTRIBUTE10;      // ATTRIBUTE10	备用字段10	VARCHAR2(100)	N

    @Override
    public String toString() {
        return "BodyOrderCancel{" +
                "RESULT_FLAG='" + RESULT_FLAG + '\'' +
                ", MSG='" + MSG + '\'' +
                ", ATTRIBUTE6='" + ATTRIBUTE6 + '\'' +
                ", ATTRIBUTE7='" + ATTRIBUTE7 + '\'' +
                ", ATTRIBUTE8='" + ATTRIBUTE8 + '\'' +
                ", ATTRIBUTE9='" + ATTRIBUTE9 + '\'' +
                ", ATTRIBUTE10='" + ATTRIBUTE10 + '\'' +
                '}';
    }

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public String getRESULT_FLAG() {
        return RESULT_FLAG;
    }

    public void setRESULT_FLAG(String RESULT_FLAG) {
        this.RESULT_FLAG = RESULT_FLAG;
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
