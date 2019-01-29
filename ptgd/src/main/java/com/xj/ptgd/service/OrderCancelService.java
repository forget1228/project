package com.xj.ptgd.service;

import com.xj.ptgd.entity.in.OrderCancelXMLIn;

/**
 *  G362 订单取消通知－中证开发
 *  光大银行将取消的订单信息通过此接口通知中证
 * @author cp
 * @date 2018/8/2
 */
public interface OrderCancelService {

    /**
     *  G362 订单取消通知－中证开发
     *  光大银行将取消的订单信息通过此接口通知中证
     * @param xml   OrderCancelXMLIn OrderCancel
     * @return
     */
    String save(OrderCancelXMLIn xml);
}
