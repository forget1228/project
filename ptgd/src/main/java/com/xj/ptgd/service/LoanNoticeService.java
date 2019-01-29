package com.xj.ptgd.service;

import com.xj.ptgd.entity.in.LoanNoticeXMLIn;

/**
 * G373 放款通知推送
 * 接口功能概述	供应链将放款通知信息推送至中证
 * 调用说明	供应链发起放款申请后，网贷易放款交易将放款信息返回给供应链，供应链更新订单状态并推送通知
 * @date 2018/8/2
 */
public interface LoanNoticeService {
    String loanNotice(LoanNoticeXMLIn in);
}
