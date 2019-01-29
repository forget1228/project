package com.xj.ptgd.service;

import com.xj.ptgd.entity.in.LoanFinancingXMLIn;

/**
 *  G353 订单融资申请转发中证
 *  供应链订单融资信息转发至中证
 * @author cp
 * @date 2018/8/2
 */
public interface LoanFinancingService {
    /**
     *  G353 订单融资申请转发中证
     *  供应链订单融资信息转发至中证
     * @param xml   LoanFinancingXMLIn  LoanFinancing
     * @return
     */
    String save(LoanFinancingXMLIn xml);
}
