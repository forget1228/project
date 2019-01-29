package com.xj.ptgd.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * customer_info
 * ent_info
 *  @author cp
 *  @since  2018/9/10
 */
public interface IntendedDao {

   /**
    * 查询
    * @param certType   证件类型
    * @param certId     统一信用代码
    * @return
    */
   Map<String,Object> findCustomerInfo(@Param("certType")String certType, @Param("certId")String certId);

   /**
    * 添加
    * @param customerId    客户流水号    生成
    * @param customerName  客户名称
    * @param customerType  客户类型
    * @param certType      证件类型
    * @param customerCode  证件号码
    * @param channel       接入机构号
    */
   void addCustomerInfo(@Param("customerId")String customerId,@Param("customerName")String customerName,
                        @Param("customerType")String customerType,@Param("certType")String certType,
                        @Param("customerCode")String customerCode,@Param("channel")String channel);

   /**
    * 修改 customerName、channelCode
    * @param customerName  客户名称
    * @param customerId    客户流水号
    * @param customerCode  统一信用代码
    */
   void updateCustomerInfo(@Param("customerName")String customerName,
                           @Param("customerId")String customerId,@Param("certType")String certType,
                           @Param("customerCode")String customerCode);

   /**
    * 备份表添加（CORPID,ENTERPRISENAME,CUSTOMERID,ORGNATURE,INPUTDATE）
    * @param customerCode  统一信用代码
    * @param customerName  客户名称
    * @param customerId    客户流水号
    */
   void addEntInfo(@Param("customerCode")String customerCode,@Param("customerName")String customerName,
                   @Param("customerId")String customerId);

   /**
    * 修改 customerName
    * @param customerName  客户名称
    * @param customerId    客户流水号
    * @param customerCode  统一信用代码
    */
   void updateEntInfo(@Param("customerName")String customerName,@Param("customerId")String customerId,
                      @Param("customerCode")String customerCode);

   /**
    * 查询ID
    * @param customerId
    * @return
    */
   String findEntCustomerId(@Param("customerId")String customerId);
}
