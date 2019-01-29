package com.xj.ptgd.dao;

import com.xj.ptgd.entity.body.PaymentNotice;

import java.util.Map;

/**
 * G374 推送还款信息
 * 接口功能概述	供应链通过此接口推送借据及还款信息
 * 调用说明	融资客户通过对公网银发起还款，银行成功受理后，供应链通过此接口将借据及还款信息推送给中证。
 * @date 2018/8/2
 */
public interface PaymentNoticeDao {
    Map<String,Object> findBusinessContract(String condition);
    Map<String,Object> findLoan(String condition);
    void insertPaymentLog(Map<String,Object> data);

    void UpDateLoan(Map<String,Object> data);
    void insertLoan(Map<String,Object> data);
}
