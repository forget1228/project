package com.xj.ptgd.service;

import com.xj.ptgd.entity.in.DealerXMLIn;
import com.xj.ptgd.entity.in.LoanNoticeXMLIn;

/**
 * DealerDataService
 * @author hjd
 * @since 2018/8/2
 */
public interface DealerDataService {
    /**
     * G371 推送经销商数据清单
     * 接口功能概述	供应链通过此接口推送经销商数据清单
     * 调用说明	供应链通过此接口将经销商数据清单信息推送给中证。
     */
    String pushDealerData(DealerXMLIn xmlIn);
}
