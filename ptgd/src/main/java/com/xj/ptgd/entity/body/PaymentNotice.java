package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;

/**
 * 推送借据及还款信息
 * @author cp
 * @date 2018/8/2
 */
public class PaymentNotice extends BaseDto {
    private String CHANNEL_CODE;             	// CHANNEL_CODE	接入机构号	VARCHAR2(8)	Y
    private String REPAY_NO;                 	// REPAY_NO	还款流水号	VARCHAR2(20)	Y
    private String LOAN_NO;                  	// LOAN_NO	借据号	VARCHAR2(17)	Y
    private String LOAN_BALANCE;             	// LOAN_BALANCE	借据余额	NUMBER(16,2)	Y
    private String REPAY_AMOUNT;             	// REPAY_AMOUNT	还款本金金额	NUMBER(16,2)	Y
    //private String INTEREST_MONEY;           // INTEREST_MONEY	还息金额	NUMBER(16.2)	N	正常网银还款时有值
    private String REFUND_INTEREST;         //REFUND_INTEREST	还息金额	NUMBER(16.2)	N
    private String REPAY_DATETIME;           // REPAY_DATETIME	 还款时间	VARCHAR2(8)	Y	格式为yyyyMMddHHmmss
    private String HQ_FLAG;                   // HQ_FLAG	借据是否结清	VARCHAR2(2)	Y	0：未结清 1：已结清
    private String JIEJU_END_DATE;           // JIEJU_END_DATE	借据到期日	VARCHAR2(8)	Y	格式：YYYYMMDD
    private String ATTRIBUTE1;              	// ATTRIBUTE1 	预留字段1	VARCHAR2(100)	N
    private String ATTRIBUTE2;              	// ATTRIBUTE2	    预留字段2	VARCHAR2(100)	N
    private String ATTRIBUTE3;              	// ATTRIBUTE3	    预留字段3	VARCHAR2(100)	N
    private String ATTRIBUTE4;              	// ATTRIBUTE4	    预留字段4	VARCHAR2(100)	N
    private String ATTRIBUTE5;              	// ATTRIBUTE5 	预留字段5	VARCHAR2(100)	N

    @Override
    public String toString() {
        return "PaymentNotice{" +
                "CHANNEL_CODE='" + CHANNEL_CODE + '\'' +
                ", REPAY_NO='" + REPAY_NO + '\'' +
                ", LOAN_NO='" + LOAN_NO + '\'' +
                ", LOAN_BALANCE='" + LOAN_BALANCE + '\'' +
                ", REPAY_AMOUNT='" + REPAY_AMOUNT + '\'' +
                ", REFUND_INTEREST='" + REFUND_INTEREST + '\'' +
                ", REPAY_DATETIME='" + REPAY_DATETIME + '\'' +
                ", HQ_FLAG='" + HQ_FLAG + '\'' +
                ", JIEJU_END_DATE='" + JIEJU_END_DATE + '\'' +
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

    public String getREPAY_NO() {
        return REPAY_NO;
    }

    public void setREPAY_NO(String REPAY_NO) {
        this.REPAY_NO = REPAY_NO;
    }

    public String getLOAN_NO() {
        return LOAN_NO;
    }

    public void setLOAN_NO(String LOAN_NO) {
        this.LOAN_NO = LOAN_NO;
    }

    public String getLOAN_BALANCE() {
        return LOAN_BALANCE;
    }

    public void setLOAN_BALANCE(String LOAN_BALANCE) {
        this.LOAN_BALANCE = LOAN_BALANCE;
    }

    public String getREPAY_AMOUNT() {
        return REPAY_AMOUNT;
    }

    public void setREPAY_AMOUNT(String REPAY_AMOUNT) {
        this.REPAY_AMOUNT = REPAY_AMOUNT;
    }

    public String getREFUND_INTEREST() {
        return REFUND_INTEREST;
    }

    public void setREFUND_INTEREST(String REFUND_INTEREST) {
        this.REFUND_INTEREST = REFUND_INTEREST;
    }

    public String getREPAY_DATETIME() {
        return REPAY_DATETIME;
    }

    public void setREPAY_DATETIME(String REPAY_DATETIME) {
        this.REPAY_DATETIME = REPAY_DATETIME;
    }

    public String getHQ_FLAG() {
        return HQ_FLAG;
    }

    public void setHQ_FLAG(String HQ_FLAG) {
        this.HQ_FLAG = HQ_FLAG;
    }

    public String getJIEJU_END_DATE() {
        return JIEJU_END_DATE;
    }

    public void setJIEJU_END_DATE(String JIEJU_END_DATE) {
        this.JIEJU_END_DATE = JIEJU_END_DATE;
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
