package com.xj.ptgd.entity.in;

import com.xj.ptgd.entity.base.XMLBaseDto;
import com.xj.ptgd.entity.base.XMLHeadDto;
import com.xj.ptgd.entity.body.NoticeContract;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "In")
@XmlType(name="In",propOrder = {"Head","Body"})
@XmlAccessorType(XmlAccessType.FIELD)
public class NoticeContractXMLIn implements XMLBaseDto<XMLHeadDto,NoticeContract> {
    private XMLHeadDto Head;
    private NoticeContract Body;

    @Override
    public XMLHeadDto getHead() {
        return Head;
    }

    @Override
    public void setHead(XMLHeadDto head) {
        Head = head;
    }

    @Override
    public NoticeContract getBody() {
        return Body;
    }

    @Override
    public void setBody(NoticeContract body) {
        Body = body;
    }
}
