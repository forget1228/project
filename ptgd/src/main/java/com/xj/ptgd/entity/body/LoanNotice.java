package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;

/**
 * 放款通知推送中证
*  @author cp
 * @date 2018/8/2
 */
public class LoanNotice extends BaseDto {
    private String CHANNEL_CODE;                // CHANNEL_CODE	接入机构号	VARCHAR(8)	Y	由银行定义
    private String CUSTOMER_CODE;               // CUSTOMER_CODE	社会统一信用代码	VARCHAR2(30)	Y
    private String CUSTOMER_NAME;               // CUSTOMER_NAME	客户名称	VARCHAR2(100)	Y
    /*private String OWNER_NAME;                    // OWNER_NAME	企业主姓名	VARCHAR2(100)	Y
    private String OWNER_IDCARD;                    // OWNER_IDCARD	企业主身份证号	VARCHAR2(18)	Y
    private String OWNER_PHONENO;                   // OWNER_PHONENO	企业主手机号	VARCHAR2(11)	Y*/
    private String ORDER_NO;                    // ORDER_NO	订单编号	VARCHAR2(30)	Y
    private String ORDER_STATUS;                // ORDER_STATUS	订单状态	VARCHAR2(2)	Y	4-已支付 9-支付失败
    private String REJECT_REASON;               // REJECT_REASON	受理拒绝原因	VARCHAR2(200)	N
    private String EXECUTE_RATE;                // EXECUTE_RATE	执行利率	NUMBER(16,2)	Y
    private String LOUS_NO;                     // LOUS_NO	借据编号	NUMBER(30)	Y
    private String LOAN_MONEY;                  // LOAN_MONEY	放款金额	NUMBER(16,2)	Y
    private String LOAN_START_DATE;             // LOAN_START_DATE	放款起始日期	VARCHAR2(8)	Y	格式：yyyymmdd
    private String LOAN_END_DATE;               // LOAN_END_DATE	放款到期日期	VARCHAR2(8)	Y	格式：yyyymmdd
    //private String AVAILABLE_LIMIT;             // AVAILABLE_LIMIT	可用额度	NUMBER(16,2)	Y
    private String ATTRIBUTE1;                    // ATTRIBUTE1 	预留字段1	VARCHAR2(100)	N
    private String ATTRIBUTE2;                      // ATTRIBUTE2	    预留字段2	VARCHAR2(100)	N
    private String ATTRIBUTE3;                      // ATTRIBUTE3	    预留字段3	VARCHAR2(100)	N
    private String ATTRIBUTE4;                      // ATTRIBUTE4	    预留字段4	VARCHAR2(100)	N
    private String ATTRIBUTE5;                      // ATTRIBUTE5 	预留字段5	VARCHAR2(100)	N

    @Override
    public String toString() {
        return "LoanNotice{" +
                "CHANNEL_CODE='" + CHANNEL_CODE + '\'' +
                ", CUSTOMER_CODE='" + CUSTOMER_CODE + '\'' +
                ", CUSTOMER_NAME='" + CUSTOMER_NAME + '\'' +
                ", ORDER_NO='" + ORDER_NO + '\'' +
                ", ORDER_STATUS='" + ORDER_STATUS + '\'' +
                ", REJECT_REASON='" + REJECT_REASON + '\'' +
                ", EXECUTE_RATE='" + EXECUTE_RATE + '\'' +
                ", LOUS_NO='" + LOUS_NO + '\'' +
                ", LOAN_MONEY='" + LOAN_MONEY + '\'' +
                ", LOAN_START_DATE='" + LOAN_START_DATE + '\'' +
                ", LOAN_END_DATE='" + LOAN_END_DATE + '\'' +
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

    public String getORDER_STATUS() {
        return ORDER_STATUS;
    }

    public void setORDER_STATUS(String ORDER_STATUS) {
        this.ORDER_STATUS = ORDER_STATUS;
    }

    public String getREJECT_REASON() {
        return REJECT_REASON;
    }

    public void setREJECT_REASON(String REJECT_REASON) {
        this.REJECT_REASON = REJECT_REASON;
    }

    public String getEXECUTE_RATE() {
        return EXECUTE_RATE;
    }

    public void setEXECUTE_RATE(String EXECUTE_RATE) {
        this.EXECUTE_RATE = EXECUTE_RATE;
    }

    public String getLOUS_NO() {
        return LOUS_NO;
    }

    public void setLOUS_NO(String LOUS_NO) {
        this.LOUS_NO = LOUS_NO;
    }

    public String getLOAN_MONEY() {
        return LOAN_MONEY;
    }

    public void setLOAN_MONEY(String LOAN_MONEY) {
        this.LOAN_MONEY = LOAN_MONEY;
    }

    public String getLOAN_START_DATE() {
        return LOAN_START_DATE;
    }

    public void setLOAN_START_DATE(String LOAN_START_DATE) {
        this.LOAN_START_DATE = LOAN_START_DATE;
    }

    public String getLOAN_END_DATE() {
        return LOAN_END_DATE;
    }

    public void setLOAN_END_DATE(String LOAN_END_DATE) {
        this.LOAN_END_DATE = LOAN_END_DATE;
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
