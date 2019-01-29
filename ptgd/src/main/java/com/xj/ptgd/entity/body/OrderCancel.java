package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;

/**
 *  G362 订单取消通知－中证开发
 *  光大银行将取消的订单信息通过此接口通知中证
 * @author cp
 * @date 2018/8/2
 */
public class OrderCancel extends BaseDto {
    private String CHANNEL_CODE;             // CHANNEL_CODE	接入机构号	VARCHAR(8)	Y	由银行定义
    private String ORDER_NO;                 // ORDER_NO	    订单编号	VARCHAR2(100)	Y
    private String CUSTOMER_NAME;            // CUSTOMER_NAME	客户名称	VARCHAR2(100)	Y
    private String CUSTOMER_CODE;            // CUSTOMER_CODE	客户社会统一信用代码	VARCHAR2(100)	Y	如客户无“社会信用代码”则输入“组织机构代码”
    private String ATTRIBUTE1;              // ATTRIBUTE1 	预留字段1	VARCHAR2(100)	N
    private String ATTRIBUTE2;              // ATTRIBUTE2	    预留字段2	VARCHAR2(100)	N
    private String ATTRIBUTE3;              // ATTRIBUTE3	    预留字段3	VARCHAR2(100)	N
    private String ATTRIBUTE4;              // ATTRIBUTE4	    预留字段4	VARCHAR2(100)	N
    private String ATTRIBUTE5;              // ATTRIBUTE5 	预留字段5	VARCHAR2(100)	N

    @Override
    public String toString() {
        return "OrderCancel{" +
                "CHANNEL_CODE='" + CHANNEL_CODE + '\'' +
                ", ORDER_NO='" + ORDER_NO + '\'' +
                ", CUSTOMER_NAME='" + CUSTOMER_NAME + '\'' +
                ", CUSTOMER_CODE='" + CUSTOMER_CODE + '\'' +
                ", ATTRIBUTE1='" + ATTRIBUTE1 + '\'' +
                ", ATTRIBUTE2='" + ATTRIBUTE2 + '\'' +
                ", ATTRIBUTE3='" + ATTRIBUTE3 + '\'' +
                ", ATTRIBUTE4='" + ATTRIBUTE4 + '\'' +
                ", ATTRIBUTE5='" + ATTRIBUTE5 + '\'' +
                '}';
    }

    public String getCHANNEL_CODE() {
        return CHANNEL_CODE;
    }

    public void setCHANNEL_CODE(String CHANNEL_CODE) {
        this.CHANNEL_CODE = CHANNEL_CODE;
    }

    public String getORDER_NO() {
        return ORDER_NO;
    }

    public void setORDER_NO(String ORDER_NO) {
        this.ORDER_NO = ORDER_NO;
    }

    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }

    public void setCUSTOMER_NAME(String CUSTOMER_NAME) {
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }

    public String getCUSTOMER_CODE() {
        return CUSTOMER_CODE;
    }

    public void setCUSTOMER_CODE(String CUSTOMER_CODE) {
        this.CUSTOMER_CODE = CUSTOMER_CODE;
    }

    public String getATTRIBUTE1() {
        return ATTRIBUTE1;
    }

    public void setATTRIBUTE1(String ATTRIBUTE1) {
        this.ATTRIBUTE1 = ATTRIBUTE1;
    }

    public String getATTRIBUTE2() {
        return ATTRIBUTE2;
    }

    public void setATTRIBUTE2(String ATTRIBUTE2) {
        this.ATTRIBUTE2 = ATTRIBUTE2;
    }

    public String getATTRIBUTE3() {
        return ATTRIBUTE3;
    }

    public void setATTRIBUTE3(String ATTRIBUTE3) {
        this.ATTRIBUTE3 = ATTRIBUTE3;
    }

    public String getATTRIBUTE4() {
        return ATTRIBUTE4;
    }

    public void setATTRIBUTE4(String ATTRIBUTE4) {
        this.ATTRIBUTE4 = ATTRIBUTE4;
    }

    public String getATTRIBUTE5() {
        return ATTRIBUTE5;
    }

    public void setATTRIBUTE5(String ATTRIBUTE5) {
        this.ATTRIBUTE5 = ATTRIBUTE5;
    }
}
