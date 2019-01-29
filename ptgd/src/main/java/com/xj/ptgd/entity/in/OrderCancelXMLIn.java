package com.xj.ptgd.entity.in;

import com.xj.ptgd.entity.base.XMLBaseDto;
import com.xj.ptgd.entity.base.XMLHeadDto;
import com.xj.ptgd.entity.body.OrderCancel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *  G362 订单取消通知－中证开发
 *  光大银行将取消的订单信息通过此接口通知中证
 */
@XmlRootElement(name="In")
@XmlType(name="In",propOrder = {"Head","Body"})
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderCancelXMLIn implements XMLBaseDto<XMLHeadDto,OrderCancel> {
    private XMLHeadDto Head;
    private OrderCancel Body;

    public XMLHeadDto getHead() {
        return Head;
    }

    public void setHead(XMLHeadDto head) {
        Head = head;
    }

    public OrderCancel getBody() {
        return Body;
    }

    public void setBody(OrderCancel body) {
        Body = body;
    }
}
