package com.xj.ptgd;

import com.xj.ptgd.common.httpXml.HttpsUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.xj.ptgd.common.util.MacUtil.addLen;
import static com.xj.ptgd.common.util.MacUtil.toMAC;

public class ZZTestClass {

    @Test
    public void G9000(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format2 =  new SimpleDateFormat("yyyyMMddHHmmss");;
        String date = format.format(new Date());
        String time = format2.format(new Date());
        String url = "https://111.205.51.104:3031";
        String xml ="<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>" +
                "<In>" +
                "<Head>" +
                "<ChnlNo>303</ChnlNo>" +
                "<FTranCode>9000</FTranCode>" +
                "<InstID>03030005</InstID>" +
                "<TrmSeqNum>99999999</TrmSeqNum>" +
                "<TranDateTime>"+time+"</TranDateTime>" +
                "<ErrCode></ErrCode>" +
                "</Head>" +
                "<Body>" +
                "<operationDate>"+date+"</operationDate>" +
                "</Body>" +
                "</In>";
        xml = addLen(xml);
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }

    @Test
    public void G372(){
        String url = "http://localhost:8080/gost";
        String xml ="<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><In><Head><ChnlNo>303</ChnlNo><FTranCode>G372</FTranCode><InstID>03030005</InstID><TrmSeqNum>914864303</TrmSeqNum><TranDateTime>20180930172220</TranDateTime><ErrCode></ErrCode></Head><Body><CHANNEL_CODE>03030005</CHANNEL_CODE><CUSTOMER_CODE>91340100085225275P</CUSTOMER_CODE><CUST_NO>1234567890</CUST_NO><CUSTOMER_NAME>厦门县卡尔文邮电办</CUSTOMER_NAME><ATTRIBUTE1></ATTRIBUTE1><ATTRIBUTE2></ATTRIBUTE2><ATTRIBUTE3></ATTRIBUTE3><ATTRIBUTE4></ATTRIBUTE4><ATTRIBUTE5></ATTRIBUTE5></Body></In>";
        //        xml = addLen(toMAC(xml));
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }

    @Test
    public void G350(){
        String url = "http://localhost:8080/gost";
        String xml ="<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><In><Head><ChnlNo>303</ChnlNo><FTranCode>G350</FTranCode><InstID>03030005</InstID><TrmSeqNum>99999999</TrmSeqNum><TranDateTime>20180930170110</TranDateTime><ErrCode></ErrCode></Head><Body><CHANNEL_CODE>03030005</CHANNEL_CODE><CUSTOMER_NAME>厦门县卡尔文邮电办</CUSTOMER_NAME><CUSTOMER_CODE>91340100085225275P</CUSTOMER_CODE><ATTRIBUTE1></ATTRIBUTE1><ATTRIBUTE2></ATTRIBUTE2><ATTRIBUTE3></ATTRIBUTE3><ATTRIBUTE4></ATTRIBUTE4><ATTRIBUTE5></ATTRIBUTE5></Body></In>";        //        xml = addLen(toMAC(xml));
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }

    @Test
    public void G351(){
        String url = "http://localhost:8080/gost";
        String xml ="<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><In><Head><ChnlNo>303</ChnlNo><FTranCode>G351</FTranCode><InstID>03030005</InstID><TrmSeqNum>914864303</TrmSeqNum><TranDateTime>20180930173105</TranDateTime><ErrCode></ErrCode></Head><Body><CHANNEL_CODE>03030005</CHANNEL_CODE><AMOUNT>1</AMOUNT><LIST_OBJ><CUSTOMER_CODE>91340100085225275P</CUSTOMER_CODE><CUSTOMER_NAME>厦门县卡尔文邮电办</CUSTOMER_NAME><PAUSE_REASON>拒绝</PAUSE_REASON><ATTRIBUTE1></ATTRIBUTE1><ATTRIBUTE2></ATTRIBUTE2><ATTRIBUTE3></ATTRIBUTE3></LIST></Body></In>";
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }

    @Test
    public void G356(){
        String url = "http://localhost:8080/gost";
        String xml ="<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><In><Head><ChnlNo>303</ChnlNo><FTranCode>G356</FTranCode><InstID>03030005</InstID><TrmSeqNum>99999999</TrmSeqNum><TranDateTime>20180930170312</TranDateTime><ErrCode></ErrCode></Head><Body><CHANNEL_CODE>03030005</CHANNEL_CODE><ORDER_NO>22212345678</ORDER_NO><CUSTOMER_NAME>厦门县卡尔文邮电办</CUSTOMER_NAME><CUSTOMER_CODE>91340100085225275P</CUSTOMER_CODE><ATTRIBUTE1></ATTRIBUTE1><ATTRIBUTE2></ATTRIBUTE2><ATTRIBUTE3></ATTRIBUTE3><ATTRIBUTE4></ATTRIBUTE4><ATTRIBUTE5></ATTRIBUTE5></Body></In>";
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }
}
