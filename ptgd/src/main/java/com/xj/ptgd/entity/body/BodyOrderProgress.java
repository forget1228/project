package com.xj.ptgd.entity.body;

import com.xj.ptgd.entity.base.BaseDto;

/**
 * 3.9 订单进度查询--应答报文
 * @author cp
 * @date 2018/8/2
 */
public class BodyOrderProgress extends BaseDto{

    private String ORDER_STATUS;             // ORDER_STATUS	订单状态	VARCHAR2(1)	Y	00-支付拒绝,01-银行已受理,02-未支付,04-已放款,06-已取消
    private String ATTRIBUTE6;              // ATTRIBUTE6	备用字段6	VARCHAR2(100)	N
    private String ATTRIBUTE7;              // ATTRIBUTE7	备用字段7	VARCHAR2(100)	N
    private String ATTRIBUTE8;              // ATTRIBUTE8	备用字段8	VARCHAR2(100)	N

    @Override
    public String toString() {
        return "BodyOrderProgress{" +
                "ORDER_STATUS='" + ORDER_STATUS + '\'' +
                ", ATTRIBUTE6='" + ATTRIBUTE6 + '\'' +
                ", ATTRIBUTE7='" + ATTRIBUTE7 + '\'' +
                ", ATTRIBUTE8='" + ATTRIBUTE8 + '\'' +
                '}';
    }

    public String getORDER_STATUS() {
        return ORDER_STATUS;
    }

    public void setORDER_STATUS(String ORDER_STATUS) {
        this.ORDER_STATUS = ORDER_STATUS;
    }

    public String getATTRIBUTE6() {
        return ATTRIBUTE6;
    }

    public void setATTRIBUTE6(String ATTRIBUTE6) {
        this.ATTRIBUTE6 = ATTRIBUTE6;
    }

    public String getATTRIBUTE7() {
        return ATTRIBUTE7;
    }

    public void setATTRIBUTE7(String ATTRIBUTE7) {
        this.ATTRIBUTE7 = ATTRIBUTE7;
    }

    public String getATTRIBUTE8() {
        return ATTRIBUTE8;
    }

    public void setATTRIBUTE8(String ATTRIBUTE8) {
        this.ATTRIBUTE8 = ATTRIBUTE8;
    }
}
