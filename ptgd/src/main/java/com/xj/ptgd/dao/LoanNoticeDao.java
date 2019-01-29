package com.xj.ptgd.dao;


import java.util.Map;

/**
 * G373 放款通知推送
 * 接口功能概述	供应链将放款通知信息推送至中证
 * 调用说明	供应链发起放款申请后，网贷易放款交易将放款信息返回给供应链，供应链更新订单状态并推送通知
 * @date 2018/8/2
 */
public interface LoanNoticeDao {
   // BUSINESS_CONTRACT 合同信息表
   Map<String,Object> findBusinessContractLoan(Map<String,Object> map);
   Map<String,Object> findBusinessContractOrderId(Map<String,Object> map);

   // acct_loan 借据  贷款-贷款信息
   void insertAcctLoan(Map map);

   Map<String,Object> findBusinessContract(Map<String,Object> map);
   Map<String,Object> findCustomerInfo(Map<String,Object> map);
   Map<String,Object> findIndInfo(Map<String,Object> map);

   void upDateIndInfo(Map in);

   void insertBusinessContract(Map in); //插入合同信息
   void insertCustomerContacts(Map in); //插入联系人信息
   void insertIndInfo(Map in); //插入个人基本信息表信息

}
