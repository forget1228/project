package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;

/**
 *  应答报文统一entity
 *  3.3、3.6、3.8、3.10、3.11、3.12
 *  @author cp
 *  @date 2018/8/2
 */
public class BodyEntity extends BaseDto {
    private String RESULT_FLAG;      // RESULT_FLAG	成功标志	VARCHAR2(1)	Y	0：失败 1：成功
    private String MESSAGE;         // MESSAGE	结果信息	VARCHAR2(200)	N	失败时有错误信息
    private String ATTRIBUTE6;      // ATTRIBUTE6	备用字段6	VARCHAR2(100)	N
    private String ATTRIBUTE7;      // ATTRIBUTE7	备用字段7	VARCHAR2(100)	N

    @Override
    public String toString() {
        return "BodyEntity{" +
                "RESULT_FLAG='" + RESULT_FLAG + '\'' +
                ", MESSAGE='" + MESSAGE + '\'' +
                ", ATTRIBUTE6='" + ATTRIBUTE6 + '\'' +
                ", ATTRIBUTE7='" + ATTRIBUTE7 + '\'' +
                '}';
    }

    public String getRESULT_FLAG() {
        return RESULT_FLAG;
    }

    public void setRESULT_FLAG(String RESULT_FLAG) {
        this.RESULT_FLAG = RESULT_FLAG;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
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
}
