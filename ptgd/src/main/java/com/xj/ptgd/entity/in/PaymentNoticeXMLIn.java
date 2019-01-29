package com.xj.ptgd.entity.in;

import com.xj.ptgd.entity.base.XMLBaseDto;
import com.xj.ptgd.entity.base.XMLHeadDto;
import com.xj.ptgd.entity.body.PaymentNotice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 订单融资申请转发中证
 * @author cp
 * @date 2018/8/2
 */
@XmlRootElement(name = "In")
@XmlType(name="In",propOrder = {"Head","Body"})
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentNoticeXMLIn implements XMLBaseDto<XMLHeadDto,PaymentNotice> {
    private XMLHeadDto Head;
    private PaymentNotice Body;

    @Override
    public XMLHeadDto getHead() {
        return Head;
    }

    @Override
    public void setHead(XMLHeadDto head) {
        Head = head;
    }

    @Override
    public PaymentNotice getBody() {
        return Body;
    }

    @Override
    public void setBody(PaymentNotice body) {
        Body = body;
    }
}
