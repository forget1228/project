package com.xj.ptgd.entity.body;

/**
 * G348 推送客户额度信息
 * 供应链手动将额度信息推送给中证，只在批复生效，额度启用后，由业务人员手工触发推送客户
 * @author cp
 * @since 2018/9/3
 */
public class Intention {
    private String CHANNEL_CODE;     	// CHANNEL_CODE	接入机构号	VARCHAR(8)	Y	由银行定义
    private String CUSTOMER_CODE;    	// CUSTOMER_CODE	客户社会信用代码	VARCHAR2(20)	Y	如客户无“社会信用代码”则输入“组织机构代码”　
    private String CUSTOMER_NAME;    	// CUSTOMER_NAME	客户名称	VARCHAR2(120)	Y
    private String CREDITLINE_STATE;    	// CREDITLINE_STATE	额度状态	VARCHAR2(10)	Y	正常
    private String TOTAL_LIMIT;    		// TOTAL_LIMIT	总额度	NUMBER（16,2）	Y	客户初始额度
    private String AVAILABLE_LIMIT;    	// AVAILABLE_LIMIT	可用额度	NUMBER（16,2）	Y	当前可用的额度
    private String LIMIT_BEGIN_DATE;    	// LIMIT_BEGIN_DATE	额度开始日期	VARCHAR2(8)	Y	格式：yyyymmdd
    private String LIMIT_END_DATE;    	// LIMIT_END_DATE	额度到期日	VARCHAR2(8)	Y	格式：yyyymmdd
    private String ATTRIBUTE1;      	// ATTRIBUTE1	    备用字段1	VARCHAR2(100)	N
    private String ATTRIBUTE2;      	// ATTRIBUTE2	    备用字段2	VARCHAR2(100)	N
    private String ATTRIBUTE3;      	// ATTRIBUTE3	    备用字段3	VARCHAR2(100)	N
    private String ATTRIBUTE4;      	// ATTRIBUTE4	    备用字段4	VARCHAR2(100)	N
    private String ATTRIBUTE5;      	// ATTRIBUTE5	    备用字段5	VARCHAR2(100)	N

    @Override
    public String toString() {
        return "Intention{" +
                "CHANNEL_CODE='" + CHANNEL_CODE + '\'' +
                ", CUSTOMER_CODE='" + CUSTOMER_CODE + '\'' +
                ", CUSTOMER_NAME='" + CUSTOMER_NAME + '\'' +
                ", CREDITLINE_STATE='" + CREDITLINE_STATE + '\'' +
                ", TOTAL_LIMIT='" + TOTAL_LIMIT + '\'' +
                ", AVAILABLE_LIMIT='" + AVAILABLE_LIMIT + '\'' +
                ", LIMIT_BEGIN_DATE='" + LIMIT_BEGIN_DATE + '\'' +
                ", LIMIT_END_DATE='" + LIMIT_END_DATE + '\'' +
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

    public String getCREDITLINE_STATE() {
        return CREDITLINE_STATE;
    }

    public void setCREDITLINE_STATE(String CREDITLINE_STATE) {
        this.CREDITLINE_STATE = CREDITLINE_STATE;
    }

    public String getTOTAL_LIMIT() {
        return TOTAL_LIMIT;
    }

    public void setTOTAL_LIMIT(String TOTAL_LIMIT) {
        this.TOTAL_LIMIT = TOTAL_LIMIT;
    }

    public String getAVAILABLE_LIMIT() {
        return AVAILABLE_LIMIT;
    }

    public void setAVAILABLE_LIMIT(String AVAILABLE_LIMIT) {
        this.AVAILABLE_LIMIT = AVAILABLE_LIMIT;
    }

    public String getLIMIT_BEGIN_DATE() {
        return LIMIT_BEGIN_DATE;
    }

    public void setLIMIT_BEGIN_DATE(String LIMIT_BEGIN_DATE) {
        this.LIMIT_BEGIN_DATE = LIMIT_BEGIN_DATE;
    }

    public String getLIMIT_END_DATE() {
        return LIMIT_END_DATE;
    }

    public void setLIMIT_END_DATE(String LIMIT_END_DATE) {
        this.LIMIT_END_DATE = LIMIT_END_DATE;
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
