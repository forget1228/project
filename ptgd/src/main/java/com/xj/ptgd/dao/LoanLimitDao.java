package com.xj.ptgd.dao;

import org.apache.ibatis.annotations.Param;

/**
 * loan_limit
 *  @author cp
 *  @since  2018/9/10
 */
public interface LoanLimitDao {

    /**
     * 查询
     * @param customerId    客户Id
     * @return
     */
    String findLoanLimitCustomerId(@Param("customerId")String customerId);

    /**
     * 添加
     * @param totalLimit        总额度
     * @param limitBeginDate    额度开始日期
     * @param limitEndDate      额度到期日
     * @param creditLineState   状态 UNSUBMITTED-未提交-0;PASSED-通过-1;REJECTED-拒绝-2;WITHDRAW-撤销-3;IN_REVIEW-审批中-4;FROZED-冻结-5;暂停-6
     * @param serialNo          流水号
     * @param customerId        用户Id
     * @param customerName      用户名称
     * @param availableLimit    可用额度
     * @param channel       接入机构号
     */
    void addLoanLimit(@Param("totalLimit")String totalLimit,@Param("limitBeginDate")String limitBeginDate,
                      @Param("limitEndDate")String limitEndDate,@Param("creditLineState")String creditLineState,
                      @Param("serialNo")String serialNo,@Param("customerId")String customerId,
                      @Param("customerName")String customerName,@Param("availableLimit")String availableLimit,
                      @Param("channel")String channel);

    /**
     * 修改  根据customerId、customerName 修改 totalLimit、customerName、availableLimit
     * @param totalLimit        总额度
     * @param availableLimit    可用额度
     * @param limitBeginDate    额度开始日期
     * @param limitEndDate      额度到期日
     * @param customerId        用户ID
     * @param customerName      客户名称
     * @param channel       接入机构号
     */
    void updateLoanLimit(@Param("totalLimit")String totalLimit,@Param("availableLimit")String availableLimit,
                         @Param("limitBeginDate")String limitBeginDate,@Param("limitEndDate")String limitEndDate,
                         @Param("customerId")String customerId,@Param("customerName")String customerName,
                         @Param("channel")String channel);

}
