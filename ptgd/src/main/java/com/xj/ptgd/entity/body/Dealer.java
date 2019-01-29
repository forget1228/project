package com.xj.ptgd.entity.body;

/**
 * 3.11.G371 推送经销商数据清单
 * 接口功能概述	供应链通过此接口推送经销商数据清单
 * 调用说明	供应链通过此接口将经销商数据清单信息推送给中证。
 */
public class Dealer {
    private String CUST_NO;              				// CUST_NO	客户编号	VARCHAR2(30)	Y
    private String CUST_NAME;            				// CUST_NAME	客户名称	VARCHAR2(100)	Y
    private String CUST_SORT_NAME;        				// CUST_SORT_NAME	客户简称	VARCHAR2(100)	N
    private String LICENCE_NO;           				// LICENCE_NO	营业执照	VARCHAR2(50)	Y
    private String CUSTOMER_CODE;        				// CUSTOMER_CODE	统一社会代码	VARCHAR2(30)	Y
    //private String phoneEntryTime;      				// PHONE_ENTRY_TIME	进入手机行业时间	VARCHAR2(8)	N	格式为YYYYMMDD
    //private String firstCooperationTime;    			// FIRST_COOPERATION_TIME	首次合作时间	VARCHAR2(8)	N	格式为YYYYMMDD
    //private double rcCurrency;                  		// RC_CURRENCY	注册资本（万元）	NUMBER(16,2)	N
    //private double taskCompletionRate;         		// TASK_COMPLETION_RATE	近一年任务完成率（%）	NUMBER(5，2)	N
    private String HUAWEI_EXPERIENCE_STORE_NUMBER;   // HUAWEI_EXPERIENCE_STORE_NUMBER	华为体验店数量	NUMBER(10)	N
    private String STORE_NUMBER;                    	// STORE_NUMBER	门店数量	NUMBER(10)	N
    private String LEGAL_COMMISS;                    	// LEGAL_COMMISS	法定代表人姓名	VARCHAR2(100)	N
    private String CERTIFICATE_TYPE;                 	// CERTIFICATE_TYPE	证件类型	VARCHAR2(3)	N
    private String CERTIFICATE_NUMBER;               	// CERTIFICATE_NUMBER	证件号码	VARCHAR2(30)	N
    private String OWNER_PHONENO;                   	// OWNER_PHONENO	手机号	VARCHAR2(11)	N
    /*private Integer isCreditUser;                   	// IS_CREDIT_USER	是否信用客户	VARCHAR2(1)	N	1-是，0-否
    private double creditLimePt;                    	// CREDIT_LINE_PT	信用额度-普天	NUMBER(16,2)	N
    private double creditLimePa;                    	// CREDIT_LINE_PA	信用额度-平安	NUMBER(16,2)	N
    private Integer creditTermTime;                  // CREDIT_TERM_TIME	信用期限-最长	NUMBER(10)	N
    private double amountUser1;                    	// AMOUNT_USE1	前年使用额度	NUMBER(16,2)	N
    private double amountUser2;                    	// AMOUNT_USE2	去年使用额度	NUMBER(16,2)	N
    private Integer isOverdue;                        // IS_OVERDUE	是否曾逾期	VARCHAR2(1)	N	1-是，0-否
    private Integer overdueDay;                    	// OVERDUE_DAY	最长逾期天数	NUMBER(10)	N
    private Integer overdueTimes;                    	// OVERDUE_TIMES	历史累计逾期次数	NUMBER(10)	N*/

    //private double monthlyAverageRebate;           // MONTHLY_AVERAGE_REBATE	近一年月均返利金额	NUMBER(16,2)	N
    //private String averageReturnTime;               // AVERAGE_RETURN_TIME	近一年平均回款时间	VARCHAR2(8)	N
    private String BRACH_COMPANY;                    // BRACH_COMPANY	分公司	VARCHAR2(120)	N
    private String MANAGE_PROVINCE;                  // MANAGE_PROVINCE	经营省份	VARCHAR2(50)	N
    private String MANAGE_CITY;                      // MANAGE_CITY	经营地市	VARCHAR2(50)	N
    //private String keyManageArea;                   // KEY_MANAGE_AREA	重点经营区域	VARCHAR2(120)	N
    //private String cityLevel;                       // CITY_LEVEL	城市级别	VARCHAR2(30)	N
    private String CUSTOMER_TYPE;                    // CUSTOMER_TYPE	客户分类	VARCHAR2(30)	N
    private String TRADING_VOLUME_YEAR;              // TRADING_VOLUME_YEAR	近2年年化交易额	NUMBER(16,2)	N
    private String TRADING_VOLUME_MONTH;             // TRADING_VOLUME_MONTH	近2年月均交易额	NUMBER(16,2)	N
    private String TRADING_TIMES_YEAR;               // TRADING_TIMES_YEAR	近2年年化交易频次	NUMBER(10)	N
    private String TRADING_TIMES_MONTH;              // TRADING_TIMES_MONTH	近2年月均交易频次	NUMBER(10)	N
    private String SINGLE_TRADING_AMOUNT;            // SINGLE_TRADING_AMOUNT	近2年平均单笔交易额	NUMBER(16,2)	N
    private String HUAWEI_TRADING_VOLUME;            // HUAWEI_TRADING_VOLUME	近2年华为品牌交易额	NUMBER(16,2)	N
    private String HUAWEI_TRADING_VOLUME_MONTH;       // HUAWEI_TRADING_VOLUME_MONTH	近2年华为品牌月均交易额	NUMBER(16,2)	N
    private String HUAWEI_BRAND_RATE;                 // HUAWEI_BRAND_RATE	近2年华为品牌提货占比	NUMBER(5，2)	N

    private String CUSTOMER_ID;

    private String CHANNEL_CODE;   //接入机构号

    public String getCHANNEL_CODE() {
        return CHANNEL_CODE;
    }

    public void setCHANNEL_CODE(String CHANNEL_CODE) {
        this.CHANNEL_CODE = CHANNEL_CODE;
    }

    public String getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public void setCUSTOMER_ID(String CUSTOMER_ID) {
        this.CUSTOMER_ID = CUSTOMER_ID;
    }
    /*private String attribute1;              	// ATTRIBUTE1 	预留字段1	VARCHAR2(100)	N
    private String attribute2;              	// ATTRIBUTE2	    预留字段2	VARCHAR2(100)	N
    private String attribute3;              	// ATTRIBUTE3	    预留字段3	VARCHAR2(100)	N
    private String attribute4;              	// ATTRIBUTE4	    预留字段4	VARCHAR2(100)	N
    private String attribute5;              	// ATTRIBUTE5 	预留字段5	VARCHAR2(100)	N   */
/*
Z_BISHU	总笔数	NUMBER(10)	Y	返回总笔数
AMOUNT_MONTH_LIST(近2年月度交易金额/频次)
AMT_LIST_MONTH	月份	VARCHAR2(6)	Y	格式为YYYYMM
MONTH_AMT	交易金额	NUMBER(16,2)	N
MONTH_TIMES	交易频次	NUMBER(10)	N
 */

    @Override
    public String toString() {
        return "Dealer{" +
                "CUST_NO='" + CUST_NO + '\'' +
                ", CUST_NAME='" + CUST_NAME + '\'' +
                ", CUST_SORT_NAME='" + CUST_SORT_NAME + '\'' +
                ", LICENCE_NO='" + LICENCE_NO + '\'' +
                ", CUSTOMER_CODE='" + CUSTOMER_CODE + '\'' +
                ", HUAWEI_EXPERIENCE_STORE_NUMBER='" + HUAWEI_EXPERIENCE_STORE_NUMBER + '\'' +
                ", STORE_NUMBER='" + STORE_NUMBER + '\'' +
                ", LEGAL_COMMISS='" + LEGAL_COMMISS + '\'' +
                ", CERTIFICATE_TYPE='" + CERTIFICATE_TYPE + '\'' +
                ", CERTIFICATE_NUMBER='" + CERTIFICATE_NUMBER + '\'' +
                ", OWNER_PHONENO='" + OWNER_PHONENO + '\'' +
                ", BRACH_COMPANY='" + BRACH_COMPANY + '\'' +
                ", MANAGE_PROVINCE='" + MANAGE_PROVINCE + '\'' +
                ", MANAGE_CITY='" + MANAGE_CITY + '\'' +
                ", CUSTOMER_TYPE='" + CUSTOMER_TYPE + '\'' +
                ", TRADING_VOLUME_YEAR='" + TRADING_VOLUME_YEAR + '\'' +
                ", TRADING_VOLUME_MONTH='" + TRADING_VOLUME_MONTH + '\'' +
                ", TRADING_TIMES_YEAR='" + TRADING_TIMES_YEAR + '\'' +
                ", TRADING_TIMES_MONTH='" + TRADING_TIMES_MONTH + '\'' +
                ", SINGLE_TRADING_AMOUNT='" + SINGLE_TRADING_AMOUNT + '\'' +
                ", HUAWEI_TRADING_VOLUME='" + HUAWEI_TRADING_VOLUME + '\'' +
                ", HUAWEI_TRADING_VOLUME_MONTH='" + HUAWEI_TRADING_VOLUME_MONTH + '\'' +
                ", HUAWEI_BRAND_RATE='" + HUAWEI_BRAND_RATE + '\'' +
                '}';
    }

    public String getCUST_NO() {
        return CUST_NO;
    }

    public void setCUST_NO(String CUST_NO) {
        this.CUST_NO = CUST_NO;
    }

    public String getCUST_NAME() {
        return CUST_NAME;
    }

    public void setCUST_NAME(String CUST_NAME) {
        this.CUST_NAME = CUST_NAME;
    }

    public String getCUST_SORT_NAME() {
        return CUST_SORT_NAME;
    }

    public void setCUST_SORT_NAME(String CUST_SORT_NAME) {
        this.CUST_SORT_NAME = CUST_SORT_NAME;
    }

    public String getLICENCE_NO() {
        return LICENCE_NO;
    }

    public void setLICENCE_NO(String LICENCE_NO) {
        this.LICENCE_NO = LICENCE_NO;
    }

    public String getCUSTOMER_CODE() {
        return CUSTOMER_CODE;
    }

    public void setCUSTOMER_CODE(String CUSTOMER_CODE) {
        this.CUSTOMER_CODE = CUSTOMER_CODE;
    }

    public String getHUAWEI_EXPERIENCE_STORE_NUMBER() {
        return HUAWEI_EXPERIENCE_STORE_NUMBER;
    }

    public void setHUAWEI_EXPERIENCE_STORE_NUMBER(String HUAWEI_EXPERIENCE_STORE_NUMBER) {
        this.HUAWEI_EXPERIENCE_STORE_NUMBER = HUAWEI_EXPERIENCE_STORE_NUMBER;
    }

    public String getSTORE_NUMBER() {
        return STORE_NUMBER;
    }

    public void setSTORE_NUMBER(String STORE_NUMBER) {
        this.STORE_NUMBER = STORE_NUMBER;
    }

    public String getLEGAL_COMMISS() {
        return LEGAL_COMMISS;
    }

    public void setLEGAL_COMMISS(String LEGAL_COMMISS) {
        this.LEGAL_COMMISS = LEGAL_COMMISS;
    }

    public String getCERTIFICATE_TYPE() {
        return CERTIFICATE_TYPE;
    }

    public void setCERTIFICATE_TYPE(String CERTIFICATE_TYPE) {
        this.CERTIFICATE_TYPE = CERTIFICATE_TYPE;
    }

    public String getCERTIFICATE_NUMBER() {
        return CERTIFICATE_NUMBER;
    }

    public void setCERTIFICATE_NUMBER(String CERTIFICATE_NUMBER) {
        this.CERTIFICATE_NUMBER = CERTIFICATE_NUMBER;
    }

    public String getOWNER_PHONENO() {
        return OWNER_PHONENO;
    }

    public void setOWNER_PHONENO(String OWNER_PHONENO) {
        this.OWNER_PHONENO = OWNER_PHONENO;
    }

    public String getBRACH_COMPANY() {
        return BRACH_COMPANY;
    }

    public void setBRACH_COMPANY(String BRACH_COMPANY) {
        this.BRACH_COMPANY = BRACH_COMPANY;
    }

    public String getMANAGE_PROVINCE() {
        return MANAGE_PROVINCE;
    }

    public void setMANAGE_PROVINCE(String MANAGE_PROVINCE) {
        this.MANAGE_PROVINCE = MANAGE_PROVINCE;
    }

    public String getMANAGE_CITY() {
        return MANAGE_CITY;
    }

    public void setMANAGE_CITY(String MANAGE_CITY) {
        this.MANAGE_CITY = MANAGE_CITY;
    }

    public String getCUSTOMER_TYPE() {
        return CUSTOMER_TYPE;
    }

    public void setCUSTOMER_TYPE(String CUSTOMER_TYPE) {
        this.CUSTOMER_TYPE = CUSTOMER_TYPE;
    }

    public String getTRADING_VOLUME_YEAR() {
        return TRADING_VOLUME_YEAR;
    }

    public void setTRADING_VOLUME_YEAR(String TRADING_VOLUME_YEAR) {
        this.TRADING_VOLUME_YEAR = TRADING_VOLUME_YEAR;
    }

    public String getTRADING_VOLUME_MONTH() {
        return TRADING_VOLUME_MONTH;
    }

    public void setTRADING_VOLUME_MONTH(String TRADING_VOLUME_MONTH) {
        this.TRADING_VOLUME_MONTH = TRADING_VOLUME_MONTH;
    }

    public String getTRADING_TIMES_YEAR() {
        return TRADING_TIMES_YEAR;
    }

    public void setTRADING_TIMES_YEAR(String TRADING_TIMES_YEAR) {
        this.TRADING_TIMES_YEAR = TRADING_TIMES_YEAR;
    }

    public String getTRADING_TIMES_MONTH() {
        return TRADING_TIMES_MONTH;
    }

    public void setTRADING_TIMES_MONTH(String TRADING_TIMES_MONTH) {
        this.TRADING_TIMES_MONTH = TRADING_TIMES_MONTH;
    }

    public String getSINGLE_TRADING_AMOUNT() {
        return SINGLE_TRADING_AMOUNT;
    }

    public void setSINGLE_TRADING_AMOUNT(String SINGLE_TRADING_AMOUNT) {
        this.SINGLE_TRADING_AMOUNT = SINGLE_TRADING_AMOUNT;
    }

    public String getHUAWEI_TRADING_VOLUME() {
        return HUAWEI_TRADING_VOLUME;
    }

    public void setHUAWEI_TRADING_VOLUME(String HUAWEI_TRADING_VOLUME) {
        this.HUAWEI_TRADING_VOLUME = HUAWEI_TRADING_VOLUME;
    }

    public String getHUAWEI_TRADING_VOLUME_MONTH() {
        return HUAWEI_TRADING_VOLUME_MONTH;
    }

    public void setHUAWEI_TRADING_VOLUME_MONTH(String HUAWEI_TRADING_VOLUME_MONTH) {
        this.HUAWEI_TRADING_VOLUME_MONTH = HUAWEI_TRADING_VOLUME_MONTH;
    }

    public String getHUAWEI_BRAND_RATE() {
        return HUAWEI_BRAND_RATE;
    }

    public void setHUAWEI_BRAND_RATE(String HUAWEI_BRAND_RATE) {
        this.HUAWEI_BRAND_RATE = HUAWEI_BRAND_RATE;
    }
}
