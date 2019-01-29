package com.xj.ptgd.service;

import com.xj.ptgd.entity.in.PushContractXMLIn;

/**
 * 3.13.G390推送合同信息
 * 接口功能概述	供应链通过此接口接收中证推送《动产抵押合同》、《最高额保证合同》《信用增进服务协议》、《四方合作协议》信息字段
 * 调用说明	小微客户经理维护合同后 ，将通过此接口接收中证推送《动产抵押合同》、《最高额保证合同》《信用增进服务协议》、《四方合作协议》信息字段
 */
public interface PushContractService {
    String pushContract(PushContractXMLIn xml);
}
