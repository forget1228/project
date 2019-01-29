package com.xj.ptgd.service;

import com.xj.ptgd.entity.in.IntentionXmlIn;

/**
 * G348 推送客户额度信息
 * 供应链手动将额度信息推送给中证，只在批复生效，额度启用后，由业务人员手工触发推送客户
 * @author cp
 * @date 2018/8/2
 */
public interface IntendedService {
    /**
     * G348 推送客户额度信息
     * 供应链手动将额度信息推送给中证，只在批复生效，额度启用后，由业务人员手工触发推送客户
     * @param xml IntentionXmlIn  Intention
     * @return
     */
    String save(IntentionXmlIn xml);
}
