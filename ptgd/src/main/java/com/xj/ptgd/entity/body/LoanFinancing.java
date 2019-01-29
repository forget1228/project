package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;

/**
 * G353 订单融资申请转发中证
 * @author cp
 * @date 2018/8/2
 */
public class LoanFinancing extends BaseDto{
    private String CHANNEL_CODE;             	// CHANNEL_CODE	接入机构号	VARCHAR(8)	Y	由银行定义
    private String CUSTOMER_CODE;            	// CUSTOMER_CODE	客户社会统一信用代码	VARCHAR2(100)	Y	如客户无“社会信用代码”则输入“组织机构代码”
    private String CUSTOMER_NAME;            	// CUSTOMER_NAME	客户名称	VARCHAR2(100)	Y
    private String ORDER_NO;                 	// ORDER_NO	    订单编号	VARCHAR2(100)	Y	订单编号唯一、防重
    private String ORDER_AMOUNT;            	// ORDER_AMOUNT	订单金额	NUMBER(16,2)	Y
    private String ORDER_DATE;               	// ORDER_DATE	订单日期	VARCHAR2(8)	Y	格式：yyyyMMddHHmmss
    private String SELF_PAY;                 // SELF_PAY	自有资金付款金额	NUMBER(16,2)	Y
    private String SELF_PAY_RATE;           // SELF_PAY_RATE	自有资金支付占比	NUMBER(5，2)	Y	单位：%
    private String BRACH_COMPANY;            	// BRACH_COMPANY	所属分公司	VARCHAR2(120)	Y
    private String BRACH_ACCOUNT;            	// BRACH_ACCOUNT	分公司账户	VARCHAR2(32)	Y
    //private String manageProvince;          	// MANAGE_PROVINCE	经营省份	VARCHAR2(50)	Y
    //private String manageCity;              	// MANAGE_CITY	经营地市	VARCHAR2(50)	Y
    private String ATTRIBUTE1;              	// ATTRIBUTE1 	预留字段1	VARCHAR2(100)	N
    private String ATTRIBUTE2;              	// ATTRIBUTE2	    预留字段2	VARCHAR2(100)	N
    private String ATTRIBUTE3;              	// ATTRIBUTE3	    预留字段3	VARCHAR2(100)	N
    private String ATTRIBUTE4;              	// ATTRIBUTE4	    预留字段4	VARCHAR2(100)	N
    private String ATTRIBUTE5;              	// ATTRIBUTE5 	预留字段5	VARCHAR2(100)	N

    @Override
    public String toString() {
        return "LoanFinancing{" +
                "CHANNEL_CODE='" + CHANNEL_CODE + '\'' +
                ", CUSTOMER_CODE='" + CUSTOMER_CODE + '\'' +
                ", CUSTOMER_NAME='" + CUSTOMER_NAME + '\'' +
                ", ORDER_NO='" + ORDER_NO + '\'' +
                ", ORDER_AMOUNT='" + ORDER_AMOUNT + '\'' +
                ", ORDER_DATE='" + ORDER_DATE + '\'' +
                ", SELF_PAY='" + SELF_PAY + '\'' +
                ", SELF_PAY_RATE='" + SELF_PAY_RATE + '\'' +
                ", BRACH_COMPANY='" + BRACH_COMPANY + '\'' +
                ", BRACH_ACCOUNT='" + BRACH_ACCOUNT + '\'' +
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

    public String getCUSTOMER_CODE() {
        return CUSTOMER_CODE;
    }

    public void setCUSTOMER_CODE(String CUSTOMER_CODE) {
        this.CUSTOMER_CODE = CUSTOMER_CODE;
    }

    public String getCUSTOMER_NAME() {
        return CUSTOMER_NAME;
    }

    public void setCUSTOMER_NAME(String CUSTOMER_NAME) {
        this.CUSTOMER_NAME = CUSTOMER_NAME;
    }

    public String getORDER_NO() {
        return ORDER_NO;
    }

    public void setORDER_NO(String ORDER_NO) {
        this.ORDER_NO = ORDER_NO;
    }

    public String getORDER_AMOUNT() {
        return ORDER_AMOUNT;
    }

    public void setORDER_AMOUNT(String ORDER_AMOUNT) {
        this.ORDER_AMOUNT = ORDER_AMOUNT;
    }

    public String getORDER_DATE() {
        return ORDER_DATE;
    }

    public void setORDER_DATE(String ORDER_DATE) {
        this.ORDER_DATE = ORDER_DATE;
    }

    public String getSELF_PAY() {
        return SELF_PAY;
    }

    public void setSELF_PAY(String SELF_PAY) {
        this.SELF_PAY = SELF_PAY;
    }

    public String getSELF_PAY_RATE() {
        return SELF_PAY_RATE;
    }

    public void setSELF_PAY_RATE(String SELF_PAY_RATE) {
        this.SELF_PAY_RATE = SELF_PAY_RATE;
    }

    public String getBRACH_COMPANY() {
        return BRACH_COMPANY;
    }

    public void setBRACH_COMPANY(String BRACH_COMPANY) {
        this.BRACH_COMPANY = BRACH_COMPANY;
    }

    public String getBRACH_ACCOUNT() {
        return BRACH_ACCOUNT;
    }

    public void setBRACH_ACCOUNT(String BRACH_ACCOUNT) {
        this.BRACH_ACCOUNT = BRACH_ACCOUNT;
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
