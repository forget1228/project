package com.xj.ptgd.service;

import com.xj.ptgd.entity.in.NoticeContractXMLIn;

/**
 * 3.15.G392 合同生效通知
 * 接口功能概述	合同生效后，供应链生成PDF文件，通过此接口将文件名称告知中证
 * 调用说明	合同生效后，供应链生成PDF文件，通过此接口将文件名称告知中证
 */
public interface NoticeContractService {
    String noticeContract(NoticeContractXMLIn xml);
}
