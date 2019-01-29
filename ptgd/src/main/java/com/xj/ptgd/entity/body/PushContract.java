package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;

public class PushContract extends BaseDto {
    private String CHANNEL_CODE;                        // CHANNEL_CODE	接入机构号	VARCHAR(8)	Y	由银行定义
    private String OPERATION_TYPE;                      // OPERATION_TYPE	操作类型	VARCHAR(1)	Y	1-新增 2-修改
    private String REFUSAL_REASON;                      // REFUSAL_REASON	拒绝原因	VARCHAR(200)	N	操作类型为2时有值
    private String CONTRACT_BATCH_NUMBER;              // CONTRACT_BATCH_NUMBER	合同批次号	VARCHAR(30)	Y	唯一
    private String CUSTOMER_CODE;                       // CUSTOMER_CODE	客户社会统一信用代码	VARCHAR2(100)	Y	如客户无“社会信用代码”则输入“组织机构代码”
    private String CUSTOMER_NAME;                       // CUSTOMER_NAME	客户名称	VARCHAR2(100)	Y
    private String AGREEMENT_NUMBER;                    // AGREEMENT_NUMBER	四方协议编号	VARCHAR2(30)	Y
    private String CUSTOMER_REPRESENTATIVE;            // CUSTOMER_REPRESENTATIVE	客户法定代表人	VARCHAR2(30)	Y
    private String PRINCIPAL_AGENT;                     // PRINCIPAL_AGENT	客户委托代理人	VARCHAR2(30)	Y
    private String CUSTOMER_ADDRESS;                    // CUSTOMER_ADDRESS	客户地址	VARCHAR2(100)	Y
    private String CUSTOMER_PHONE;                      // CUSTOMER_PHONE	客户电话	VARCHAR2(15)	Y
    private String CONTRACT_NO;                         // CONTRACT_NO	最高额保证合同编号	VARCHAR2(30)	Y
    private String MAX_CREDIT_LINE;                     // MAX_CREDIT_LINE	最高授信额度	NUMBER(16,2)	Y
    private String CUSTOMER_REGISTRATION_ADDRESS;     // CUSTOMER_REGISTRATION_ADDRESS	客户注册地址	VARCHAR2(120)	Y
    private String CUSTOMER_POSTAL_ADDRESS;            // CUSTOMER_POSTAL_ADDRESS	客户通讯地址	VARCHAR2(120)	Y
    private String CUSTOMER_CONTACT;                    // CUSTOMER_CONTACT	客户联系人	VARCHAR2(30)	Y
    private String CONTACT_NUMBER;                      // CONTACT_NUMBER	联系电话	VARCHAR2(20)	Y
    private String CONTACT_FAX;                         // CONTACT_FAX	联系传值	VARCHAR2(20)	Y
    private String CONTACT_POSTAL_CODE;                 // CONTACT_POSTAL_CODE	邮政编码	VARCHAR2(20)	Y
    private String TOTAL_CREDIT;                        // TOTAL_CREDIT	信用增信授信总额度	NUMBER(16,2)	Y
    private String ATTRIBUTE1;
    private String ATTRIBUTE2;
    private String ATTRIBUTE3;
    private String ATTRIBUTE4;
    private String ATTRIBUTE5;
    private String ATTRIBUTE6;
    private String ATTRIBUTE7;
    private String ATTRIBUTE8;
    private String ATTRIBUTE9;
    private String ATTRIBUTE10;

    private String CUSTOMER_ID;
    private String FDBBZHT_NO;
    private String DCDYHT_NO;
    private String CREDIT_AUGMENTED_AGREEMENT_NUMBER;

    @Override
    public String toString() {
        return "PushContract{" +
                "CHANNEL_CODE='" + CHANNEL_CODE + '\'' +
                ", OPERATION_TYPE='" + OPERATION_TYPE + '\'' +
                ", REFUSAL_REASON='" + REFUSAL_REASON + '\'' +
                ", CONTRACT_BATCH_NUMBER='" + CONTRACT_BATCH_NUMBER + '\'' +
                ", CUSTOMER_CODE='" + CUSTOMER_CODE + '\'' +
                ", CUSTOMER_NAME='" + CUSTOMER_NAME + '\'' +
                ", AGREEMENT_NUMBER='" + AGREEMENT_NUMBER + '\'' +
                ", CUSTOMER_REPRESENTATIVE='" + CUSTOMER_REPRESENTATIVE + '\'' +
                ", PRINCIPAL_AGENT='" + PRINCIPAL_AGENT + '\'' +
                ", CUSTOMER_ADDRESS='" + CUSTOMER_ADDRESS + '\'' +
                ", CUSTOMER_PHONE='" + CUSTOMER_PHONE + '\'' +
                ", CONTRACT_NO='" + CONTRACT_NO + '\'' +
                ", MAX_CREDIT_LINE='" + MAX_CREDIT_LINE + '\'' +
                ", CUSTOMER_REGISTRATION_ADDRESS='" + CUSTOMER_REGISTRATION_ADDRESS + '\'' +
                ", CUSTOMER_POSTAL_ADDRESS='" + CUSTOMER_POSTAL_ADDRESS + '\'' +
                ", CUSTOMER_CONTACT='" + CUSTOMER_CONTACT + '\'' +
                ", CONTACT_NUMBER='" + CONTACT_NUMBER + '\'' +
                ", CONTACT_FAX='" + CONTACT_FAX + '\'' +
                ", CONTACT_POSTAL_CODE='" + CONTACT_POSTAL_CODE + '\'' +
                ", TOTAL_CREDIT='" + TOTAL_CREDIT + '\'' +
                ", ATTRIBUTE1='" + ATTRIBUTE1 + '\'' +
                ", ATTRIBUTE2='" + ATTRIBUTE2 + '\'' +
                ", ATTRIBUTE3='" + ATTRIBUTE3 + '\'' +
                ", ATTRIBUTE4='" + ATTRIBUTE4 + '\'' +
                ", ATTRIBUTE5='" + ATTRIBUTE5 + '\'' +
                ", ATTRIBUTE6='" + ATTRIBUTE6 + '\'' +
                ", ATTRIBUTE7='" + ATTRIBUTE7 + '\'' +
                ", ATTRIBUTE8='" + ATTRIBUTE8 + '\'' +
                ", ATTRIBUTE9='" + ATTRIBUTE9 + '\'' +
                ", ATTRIBUTE10='" + ATTRIBUTE10 + '\'' +
                '}';
    }

    public String getFDBBZHT_NO() {
        return FDBBZHT_NO;
    }

    public void setFDBBZHT_NO(String FDBBZHT_NO) {
        this.FDBBZHT_NO = FDBBZHT_NO;
    }

    public String getDCDYHT_NO() {
        return DCDYHT_NO;
    }

    public void setDCDYHT_NO(String DCDYHT_NO) {
        this.DCDYHT_NO = DCDYHT_NO;
    }

    public String getCREDIT_AUGMENTED_AGREEMENT_NUMBER() {
        return CREDIT_AUGMENTED_AGREEMENT_NUMBER;
    }

    public void setCREDIT_AUGMENTED_AGREEMENT_NUMBER(String CREDIT_AUGMENTED_AGREEMENT_NUMBER) {
        this.CREDIT_AUGMENTED_AGREEMENT_NUMBER = CREDIT_AUGMENTED_AGREEMENT_NUMBER;
    }

    public String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(String CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }

    public String getCHANNEL_CODE() {
        return CHANNEL_CODE;
    }

    public void setCHANNEL_CODE(String CHANNEL_CODE) {
        this.CHANNEL_CODE = CHANNEL_CODE;
    }

    public String getOPERATION_TYPE() {
        return OPERATION_TYPE;
    }

    public void setOPERATION_TYPE(String OPERATION_TYPE) {
        this.OPERATION_TYPE = OPERATION_TYPE;
    }

    public String getREFUSAL_REASON() {
        return REFUSAL_REASON;
    }

    public void setREFUSAL_REASON(String REFUSAL_REASON) {
        this.REFUSAL_REASON = REFUSAL_REASON;
    }

    public String getCONTRACT_BATCH_NUMBER() {
        return CONTRACT_BATCH_NUMBER;
    }

    public void setCONTRACT_BATCH_NUMBER(String CONTRACT_BATCH_NUMBER) {
        this.CONTRACT_BATCH_NUMBER = CONTRACT_BATCH_NUMBER;
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

    public String getAGREEMENT_NUMBER() {
        return AGREEMENT_NUMBER;
    }

    public void setAGREEMENT_NUMBER(String AGREEMENT_NUMBER) {
        this.AGREEMENT_NUMBER = AGREEMENT_NUMBER;
    }

    public String getCUSTOMER_REPRESENTATIVE() {
        return CUSTOMER_REPRESENTATIVE;
    }

    public void setCUSTOMER_REPRESENTATIVE(String CUSTOMER_REPRESENTATIVE) {
        this.CUSTOMER_REPRESENTATIVE = CUSTOMER_REPRESENTATIVE;
    }

    public String getPRINCIPAL_AGENT() {
        return PRINCIPAL_AGENT;
    }

    public void setPRINCIPAL_AGENT(String PRINCIPAL_AGENT) {
        this.PRINCIPAL_AGENT = PRINCIPAL_AGENT;
    }

    public String getCUSTOMER_ADDRESS() {
        return CUSTOMER_ADDRESS;
    }

    public void setCUSTOMER_ADDRESS(String CUSTOMER_ADDRESS) {
        this.CUSTOMER_ADDRESS = CUSTOMER_ADDRESS;
    }

    public String getCUSTOMER_PHONE() {
        return CUSTOMER_PHONE;
    }

    public void setCUSTOMER_PHONE(String CUSTOMER_PHONE) {
        this.CUSTOMER_PHONE = CUSTOMER_PHONE;
    }

    public String getCONTRACT_NO() {
        return CONTRACT_NO;
    }

    public void setCONTRACT_NO(String CONTRACT_NO) {
        this.CONTRACT_NO = CONTRACT_NO;
    }

    public String getMAX_CREDIT_LINE() {
        return MAX_CREDIT_LINE;
    }

    public void setMAX_CREDIT_LINE(String MAX_CREDIT_LINE) {
        this.MAX_CREDIT_LINE = MAX_CREDIT_LINE;
    }

    public String getCUSTOMER_REGISTRATION_ADDRESS() {
        return CUSTOMER_REGISTRATION_ADDRESS;
    }

    public void setCUSTOMER_REGISTRATION_ADDRESS(String CUSTOMER_REGISTRATION_ADDRESS) {
        this.CUSTOMER_REGISTRATION_ADDRESS = CUSTOMER_REGISTRATION_ADDRESS;
    }

    public String getCUSTOMER_POSTAL_ADDRESS() {
        return CUSTOMER_POSTAL_ADDRESS;
    }

    public void setCUSTOMER_POSTAL_ADDRESS(String CUSTOMER_POSTAL_ADDRESS) {
        this.CUSTOMER_POSTAL_ADDRESS = CUSTOMER_POSTAL_ADDRESS;
    }

    public String getCUSTOMER_CONTACT() {
        return CUSTOMER_CONTACT;
    }

    public void setCUSTOMER_CONTACT(String CUSTOMER_CONTACT) {
        this.CUSTOMER_CONTACT = CUSTOMER_CONTACT;
    }

    public String getCONTACT_NUMBER() {
        return CONTACT_NUMBER;
    }

    public void setCONTACT_NUMBER(String CONTACT_NUMBER) {
        this.CONTACT_NUMBER = CONTACT_NUMBER;
    }

    public String getCONTACT_FAX() {
        return CONTACT_FAX;
    }

    public void setCONTACT_FAX(String CONTACT_FAX) {
        this.CONTACT_FAX = CONTACT_FAX;
    }

    public String getCONTACT_POSTAL_CODE() {
        return CONTACT_POSTAL_CODE;
    }

    public void setCONTACT_POSTAL_CODE(String CONTACT_POSTAL_CODE) {
        this.CONTACT_POSTAL_CODE = CONTACT_POSTAL_CODE;
    }

    public String getTOTAL_CREDIT() {
        return TOTAL_CREDIT;
    }

    public void setTOTAL_CREDIT(String TOTAL_CREDIT) {
        this.TOTAL_CREDIT = TOTAL_CREDIT;
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
