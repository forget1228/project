package com.xj.ptgd.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 *  gd_order_info 表
 *  @author cp
 *  @since  2018/9/10
 */
public interface OrderDao {

    /**
     * 查询 CUSTOMERID 客户流水号
     * @param certId  统一信用代码
     * @return
     */
    String findCustomerInfoForCustomerId(@Param("certType")String certType,@Param("certId")String certId);

    /**
     * 订单编号 主键 不允许重复
     * @param orderNo  订单编号
     * @return
     */
    String findGdOrderNo(@Param("orderNo")String orderNo);
    /**
     * 查询
     * @param orderNo   订单编号
     * @param customerCode  统一信用代码
     * @return
     */
    Map<String,Object> findGdOrderInfo(@Param("orderNo")String orderNo,@Param("customerCode")String customerCode);

    /**
     * 查询
     * @param orderNo   订单编号
     * @param customerCode  统一信用代码
     * @param customerName  客户名称
     * @return
     */
    Map<String,Object> findGdOrderInfoByThree(@Param("orderNo")String orderNo,@Param("customerCode")String customerCode,@Param("customerName")String customerName);

    /**
     * 添加
     * @param customerCode  统一信用代码
     * @param customerName  客户名称
     * @param orderNo       订单编号
     * @param orderAmount   订单金额
     * @param orderDate     订单日期
     * @param selfPay       自有资金付款金额
     * @param selfPayRate   自有资金支付占比
     * @param branchCompany 所属分公司
     * @param branchAccount 分公司账户
     * @param customerId    用户流水号
     * @param channel       接入机构号
     */
    void addGdOrderInfo(@Param("customerCode")String customerCode,@Param("customerName")String customerName,
                        @Param("orderNo")String orderNo,@Param("orderAmount")Double orderAmount,
                        @Param("orderDate")String orderDate,@Param("selfPay")Double selfPay,
                        @Param("selfPayRate")String selfPayRate,@Param("branchCompany")String branchCompany,
                        @Param("branchAccount")String branchAccount,@Param("customerId")String customerId,
                        @Param("channel")String channel);

    /**
     * 根据 customerCode、orderNo 、customerId 修改
     * @param customerName  客户名称
     * @param orderAmount   订单金额
     * @param orderDate     订单日期
     * @param selfPay       自有资金付款金额
     * @param selfPayRate   自有资金支付占比
     * @param branchCompany 所属分公司
     * @param branchAccount 分公司账户
     * @param customerCode  统一信用代码
     * @param orderNo       订单编号
     */
    void updateGdOrderInfo(@Param("customerName")String customerName,@Param("orderAmount")String orderAmount,
                           @Param("orderDate")String orderDate,@Param("selfPay")String selfPay,
                           @Param("selfPayRate")String selfPayRate,
                           @Param("branchCompany")String branchCompany, @Param("branchAccount")String branchAccount,
                           @Param("customerCode")String customerCode,@Param("orderNo")String orderNo);

    /**
     * 修改订单状态
     * @param customerCode  统一信用代码
     * @param orderNo       订单编号
     * @param orderStatus   订单状态
     * @param channel       接入机构号
     */
    void updateGdStatus(@Param("customerCode")String customerCode,@Param("orderNo")String orderNo,
                        @Param("orderStatus")String orderStatus,@Param("reason")String reason,
                        @Param("channel")String channel);
}
