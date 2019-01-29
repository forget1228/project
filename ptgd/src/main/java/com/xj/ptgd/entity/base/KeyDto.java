package com.xj.ptgd.entity.base;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name="out")
@XmlType(name="out",propOrder = {"Head","tout"})
@XmlAccessorType(XmlAccessType.FIELD)
public class KeyDto implements Serializable {
    @XmlElement(name="Head")
    private KeyHead Head;
    private KeyTout tout;

    public KeyHead getHead() {
        return Head;
    }

    public void setHead(KeyHead head) {
        Head = head;
    }

    public KeyTout getTout() {
        return tout;
    }

    public void setTout(KeyTout tout) {
        this.tout = tout;
    }

    @Override
    public String toString() {
        return "KeyDto{" +
                "Head=" + Head +
                ", tout=" + tout +
                '}';
    }
    static class KeyHead{
        @XmlElement(name="Version")
        private String Version;
        @XmlElement(name="InstId")
        private Integer InstId;
        @XmlElement(name="AnsTranCode")
        private String AnsTranCode;
        @XmlElement(name="TrmSeqNum")
        private Integer TrmSeqNum;

        public String getVersion() {
            return Version;
        }

        public void setVersion(String version) {
            Version = version;
        }

        public Integer getInstId() {
            return InstId;
        }

        public void setInstId(Integer instId) {
            InstId = instId;
        }

        public String getAnsTranCode() {
            return AnsTranCode;
        }

        public void setAnsTranCode(String ansTranCode) {
            AnsTranCode = ansTranCode;
        }

        public Integer getTrmSeqNum() {
            return TrmSeqNum;
        }

        public void setTrmSeqNum(Integer trmSeqNum) {
            TrmSeqNum = trmSeqNum;
        }
    }

    static class KeyTout{
        private String errorCode;
        private String errorMessage;
        private String errorDetail;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorDetail() {
            return errorDetail;
        }

        public void setErrorDetail(String errorDetail) {
            this.errorDetail = errorDetail;
        }
    }


}
