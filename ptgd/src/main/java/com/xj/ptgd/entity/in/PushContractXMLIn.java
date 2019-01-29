package com.xj.ptgd.entity.in;

import com.xj.ptgd.entity.base.XMLBaseDto;
import com.xj.ptgd.entity.base.XMLHeadDto;
import com.xj.ptgd.entity.body.PushContract;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="In")
@XmlType(name="In",propOrder = {"Head","Body"})
@XmlAccessorType(XmlAccessType.FIELD)
public class PushContractXMLIn implements XMLBaseDto<XMLHeadDto,PushContract> {
    private XMLHeadDto Head;
    private PushContract Body;

    public XMLHeadDto getHead() {
        return Head;
    }

    public void setHead(XMLHeadDto head) {
        Head = head;
    }

    public PushContract getBody() {
        return Body;
    }

    public void setBody(PushContract body) {
        Body = body;
    }
}
