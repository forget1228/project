package com.xj.ptgd.entity.out;

import com.xj.ptgd.entity.base.XMLBaseDto;
import com.xj.ptgd.entity.base.XMLHeadDto;
import com.xj.ptgd.entity.body.BodyEntityMSG;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Out")
@XmlType(name="Out",propOrder = {"Head","Body"})
@XmlAccessorType(XmlAccessType.FIELD)
public class BodyEntityMSGXMLOut implements XMLBaseDto<XMLHeadDto,BodyEntityMSG> {
    private XMLHeadDto Head;
    private BodyEntityMSG Body;

    @Override
    public XMLHeadDto getHead() {
        return Head;
    }

    @Override
    public void setBody(BodyEntityMSG bodyEntityMSG) {
        Body = bodyEntityMSG;
    }

    @Override
    public BodyEntityMSG getBody() {
        return Body;
    }

    @Override
    public void setHead(XMLHeadDto head) {
        Head = head;
    }
}
