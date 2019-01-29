package com.xj.ptgd.entity.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(name = "Head", propOrder = {"ChnlNo","FTranCode","InstID","TrmSeqNum","TranDateTime","ErrCode"})
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLHeadDto {
    @XmlElement(name ="ChnlNo")
    private String ChnlNo;  //渠道号

    @XmlElement(name="FTranCode")
    private String FTranCode;  //交易码

    @XmlElement(name="InstID")
    private String InstID;  //机构号

    @XmlElement(name="TrmSeqNum")
    private String TrmSeqNum;  //终端流水号

    @XmlElement(name="TranDateTime")
    private String TranDateTime;  //交易日期时间

    @XmlElement(name="ErrCode")
    private String ErrCode;  //错误码

    public String getChnlNo() {
        return ChnlNo;
    }

    public void setChnlNo(String chnlNo) {
        ChnlNo = chnlNo;
    }

    public String getFTranCode() {
        return FTranCode;
    }

    public void setFTranCode(String FTranCode) {
        this.FTranCode = FTranCode;
    }

    public String getInstID() {
        return InstID;
    }

    public void setInstID(String instID) {
        InstID = instID;
    }

    public String getTrmSeqNum() {
        return TrmSeqNum;
    }

    public void setTrmSeqNum(String trmSeqNum) {
        TrmSeqNum = trmSeqNum;
    }

    public String getTranDateTime() {
        return TranDateTime;
    }

    public void setTranDateTime(String tranDateTime) {
        TranDateTime = tranDateTime;
    }

    public String getErrCode() {
        return ErrCode;
    }

    public void setErrCode(String errCode) {
        ErrCode = errCode;
    }

    @Override
    public String toString() {
        return "XMLHeadDto{" +
                "ChnlNo='" + ChnlNo + '\'' +
                ", FTranCode='" + FTranCode + '\'' +
                ", InstID='" + InstID + '\'' +
                ", TrmSeqNum='" + TrmSeqNum + '\'' +
                ", TranDateTime='" + TranDateTime + '\'' +
                ", ErrCode='" + ErrCode + '\'' +
                '}';
    }
}
