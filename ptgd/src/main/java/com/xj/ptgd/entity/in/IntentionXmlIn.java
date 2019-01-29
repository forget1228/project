package com.xj.ptgd.entity.in;

import com.xj.ptgd.entity.base.XMLBaseDto;
import com.xj.ptgd.entity.base.XMLHeadDto;
import com.xj.ptgd.entity.body.Intention;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * G348 推送客户额度信息
 * 供应链手动将额度信息推送给中证，只在批复生效，额度启用后，由业务人员手工触发推送客户
 * @author cp
 * @since 2018/9/3
 */
@XmlRootElement(name = "In")
@XmlType(name="In",propOrder = {"Head","Body"})
@XmlAccessorType(XmlAccessType.FIELD)
public class IntentionXmlIn implements XMLBaseDto<XMLHeadDto,Intention> {
    private XMLHeadDto Head;
    private Intention Body;

    @Override
    public XMLHeadDto getHead() {
        return Head;
    }

    @Override
    public void setHead(XMLHeadDto head) {
        Head = head;
    }

    @Override
    public Intention getBody() {
        return Body;
    }

    @Override
    public void setBody(Intention body) {
        Body = body;
    }
}